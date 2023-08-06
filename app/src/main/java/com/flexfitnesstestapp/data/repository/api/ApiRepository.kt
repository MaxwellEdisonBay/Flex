package com.flexfitnesstestapp.data.repository.api

import javax.inject.Inject

internal class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {
//    suspend fun getCurrencyRates(): Resource<CurrencyRs> {
//        return try {
//            val result = apiService.getCurrencyRates()
//            Resource.Success(result = result)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Failure(e)
//        }
//    }
}