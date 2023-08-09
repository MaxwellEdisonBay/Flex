package com.flexfitnesstestapp.data.repository.auth

import android.content.Intent
import android.content.IntentSender
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.model.auth.UserData
import com.google.firebase.auth.FirebaseUser

internal interface FirebaseAuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()

    suspend fun signInGoogle(): IntentSender?

    suspend fun signInGoogleWithIntent(intent: Intent): Resource<FirebaseUser>
    suspend fun signOutGoogle()
    fun getSignedInUserGoogle(): UserData?
}