package com.example.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class AuthRepository {
     suspend fun loginToAccount(email:String, password: String): Result<String>{
          lateinit var auth : FirebaseAuth
          try {
               auth = Firebase.auth
          }
          catch (e : Exception){
               Log.i("coord", e.toString())
          }
          return try{
               auth.signInWithEmailAndPassword(email, password).await()
               Result.success("Login successful")
          }
          catch (e: Exception){
               Result.failure(e)
          }
     }
}