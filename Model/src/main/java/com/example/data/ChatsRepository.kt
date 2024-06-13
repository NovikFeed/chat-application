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
import kotlinx.coroutines.tasks.await
import kotlin.Exception

class ChatsRepository {
    private val db = Firebase.database("https://chat-ab91b-default-rtdb.firebaseio.com/").reference

   suspend fun sendRequest(email : String):Result<String>{
       val uid = getUserUid(email)
       return try {
           if (uid != null) {
               val list = checkEmptyRequestList(uid)
               if (list.isEmpty()) {
                   Result.failure(Exception("you try send request for yourself or  error when receiving the list from the server"))
               }
               else if(list.contains("you are sending a request to yourself")){
                   Result.failure(Exception("you are sending a request to yourself"))
               }
               else if(list.contains("you are sending a request to yourself or this people have your request")){
                   Result.failure(Exception("you are sending a request to yourself or this people have your request"))
               }
               else {
                       db.child("users").child(uid).child("request").setValue(list).await()
                       Result.success("Successful send")
               }
           } else {
               Result.failure(Exception("user with such email does not exist"))
           }
       }
       catch (e : Exception){
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
            Log.e("DB error", e.toString())
            null
        }
    }
    private suspend fun checkEmptyRequestList(uid: String) : List<String>{
        val requestRef = db.child("users").child(uid).child("request")
        return try {
            val snapshot = requestRef.get().await()
            if(snapshot.exists()){
                val list = snapshot.getValue<List<String>>()
                if(list == null){
                    if(uid != Firebase.auth.uid){
                        listOf(uid)
                    }
                    else{
                        listOf("you are sending a request to yourself")
                    }
                }
                else{
                    if(!list.contains(uid) && uid != Firebase.auth.uid){
                        list + uid
                    }
                    else{
                        listOf("you are sending a request to yourself or this people have your request")
                    }
                }
            }
            else{
                if(uid != Firebase.auth.uid){
                    listOf(uid)
                }
                else{
                    listOf("you are sending a request to yourself")
                }
            }
        }
        catch (e : Exception){
            emptyList<String>()
        }
    }
}