package com.flexfitnesstestapp.data.repository.api

import javax.inject.Inject

internal class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {}