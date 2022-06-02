package ke.co.virtual.pay.hiltdi.repository

import ke.co.virtual.pay.hiltdi.service.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getCountries() = apiService.getCountries()
}