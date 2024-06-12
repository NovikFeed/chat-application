package com.example.data

import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import java.io.File
import java.lang.Exception

class AuthRepository {
     private lateinit var auth : FirebaseAuth
     suspend fun loginToAccount(email:String, password: String): Result<String>{
          return try{
               auth = Firebase.auth
               auth.signInWithEmailAndPassword(email, password).await()
               Result.success("Login successful")
          }
          catch (e: Exception){
               Result.failure(e)
          }
     }
     suspend fun registerAccount(email: String, password: String, nickname: String, imgUri : Uri): Result<String>{
          auth = Firebase.auth
          val db = Firebase.database("https://chat-ab91b-default-rtdb.firebaseio.com/").reference.child("users")
          val dbStorage = Firebase.storage("gs://chat-ab91b.appspot.com").reference
          return try{
               auth.createUserWithEmailAndPassword(email, password).await()
               val currentUserUID = auth.currentUser?.uid
               val imgInStorage = dbStorage.child("image:$currentUserUID")
               imgInStorage.putFile(imgUri).await()
               val urlInStorage = imgInStorage.downloadUrl.await()
               db.child(currentUserUID!!).setValue(User(nickname, email, urlInStorage.toString())).await()
               Result.success("Register successful")

          }
          catch(e : Exception){
               Result.failure(e)
          }
     }

     suspend fun getUser() : Result<String>{
          auth = Firebase.auth
          return try {
               if(auth.currentUser != null){
                    Result.success("User logined")
               }
               else{
                    Result.failure(Exception("Error"))
               }
          }
          catch (e : Exception){
               Result.failure(e)
          }
     }

}