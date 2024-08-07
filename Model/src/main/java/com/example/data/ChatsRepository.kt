package com.example.data

import android.net.ConnectivityManager.NetworkCallback
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.Exception

class ChatsRepository {
    private val db = Firebase.database("https://chat-ab91b-default-rtdb.firebaseio.com/").reference
    private val currentUid = Firebase.auth.uid!!

   suspend fun sendRequest(email : String):Result<String>{
       val uid = getUserUid(email)
       return try {
           if (uid != null) {
               val notInFriends = checkInFriends(uid)
               val list = checkEmptyRequestList(uid)
               if (list.isEmpty()) {
                   Result.failure(Exception("you try send request for yourself or this user have your request"))
               }
               else {
                    if(notInFriends){
                       db.child("users").child(uid).child("request").setValue(list).await()
                       Result.success("Successful send")}
                   else{
                       Result.failure(Exception("this user is already among your friends"))
                   }
               }
           } else {
               Result.failure(Exception("user with such email does not exist"))
           }
       }
       catch (e : Exception){
           Log.i("coord", e.toString())
           Result.failure(e)
       }
    }

    private suspend fun getUserUid(email: String) : String?{
        return try {
            val snapshot =
                db.child("users").orderByChild("email").equalTo(email).limitToFirst(1).get().await()
            if(snapshot.exists()){
                snapshot.children.first().key
            }
            else{
                null
            }
        }
        catch (e : Exception){
            Log.e("Error", e.toString())
            null
        }
    }
     private suspend fun checkInFriends(uid : String) : Boolean{
         try {
             val currentUser = getUser(currentUid)!!
             val listFriends = currentUser.friends
             if(listFriends.isNotEmpty()){
             listFriends.forEach { friend ->
                 if (friend.uid == uid) {
                     return false
                     return@forEach
                 } else {
                     return true
                 }
             }
             }
             else{
                 return true
             }
         }
         catch (e : Exception){
             Log.i("Error check", e.toString())
         }
         return false
    }
    private suspend fun checkEmptyRequestList(uid: String) : List<RequestToFriend>{
        val requestRef = db.child("users").child(uid).child("request")
        val myUid = currentUid.toString()
        val user = getUser(myUid)
        val request = RequestToFriend(myUid, user!!.nickname,user!!.imgUrl)
        return try {
            val snapshot = requestRef.get().await()
            if(snapshot.exists()){
                val list = snapshot.getValue<List<RequestToFriend>>()
                if(list == null){
                    if(uid != myUid){
                        listOf(request)
                    }
                    else{
                        emptyList<RequestToFriend>()
                    }
                }
                else{
                    if(!list.contains(request) && uid != myUid){
                        list + request
                    }
                    else{
                        emptyList<RequestToFriend>()
                    }
                }
            }
            else{
                if(uid != myUid){
                    listOf(request)
                }
                else{
                    emptyList<RequestToFriend>()
                }
            }
        }
        catch (e : Exception){
            emptyList<RequestToFriend>()
        }
    }
   suspend fun setListenerForRequestList() = callbackFlow<List<RequestToFriend>> {
           val dbRef = db.child("users").child(currentUid).child("request")
           val listener = object : ValueEventListener {
               override fun onDataChange(snapshot: DataSnapshot) {
                   if (snapshot.exists()) {
                       val list = snapshot.getValue<List<RequestToFriend>>()
                       val resList = list!!.filter { it.status }
                       trySend(resList)
                   } else {
                       trySend(emptyList()).isSuccess
                   }
               }

               override fun onCancelled(error: DatabaseError) {
                   close(error.toException())
               }
           }
           dbRef.addValueEventListener(listener)
           awaitClose { dbRef.removeEventListener(listener) }
   }

    private suspend fun getUser(uid : String): User?{
       val snapshot =  db.child("users").child(uid).get().await()
        return snapshot.getValue<User>()
    }

    suspend fun refuseRequest(refuseUid : String){
        val reference = db.child("users").child(currentUid).child("request")
        val snapshot = reference.get().await()
        if(snapshot.exists()){
            snapshot.children.forEach { request ->
                val res = request.getValue<RequestToFriend>()
                if(res != null && res.uid == refuseUid){
                    res.status = false
                    request.ref.setValue(res).await()
                    return@forEach
                }
            }
        }

    }
    suspend fun acceptRequest(acceptUid: String) : Result<String>{
        val acceptedUser = getUser(acceptUid)!!
        val reference = db.child("users").child(Firebase.auth.uid!!).child("friends")
        val snapshot = reference.get().await()
        return try {
            if (snapshot.exists()) {
                var list = snapshot.getValue<List<Friend>>()
                if (list != null) {
                    list = list + Friend(acceptUid, acceptedUser.imgUrl, acceptedUser.nickname)
                    reference.setValue(list).await()
                    Result.success("Ok")
                } else {
                    val list = listOf<Friend>(
                        Friend(
                            acceptUid,
                            acceptedUser.imgUrl,
                            acceptedUser.nickname
                        )
                    )
                    reference.setValue(list).await()
                    Result.success("Ok")
                }
            } else {
                val list =
                    listOf<Friend>(Friend(acceptUid, acceptedUser.imgUrl, acceptedUser.nickname))
                reference.setValue(list).await()
                Result.success("Ok")

            }
        }
        catch (e : Exception){
            Log.e("Accepted Error", e.toString())
            Result.failure(e)
        }
    }
    suspend fun addMeToFriend(acceptedUid : String){
        val currentUser = getUser(currentUid)!!
        val newFriend = getUser(acceptedUid)!!
        val listFriendsMyFriend = newFriend.friends
        var sendList = emptyList<Friend>()
        if(listFriendsMyFriend.isNotEmpty()){
            sendList = listFriendsMyFriend + Friend(currentUid,currentUser.imgUrl,currentUser.nickname)
        }
        else{
            sendList = listOf(Friend(currentUid,currentUser.imgUrl,currentUser.nickname))
        }
        db.child("users").child(acceptedUid).child("friends").setValue(sendList).await()
    }
}
