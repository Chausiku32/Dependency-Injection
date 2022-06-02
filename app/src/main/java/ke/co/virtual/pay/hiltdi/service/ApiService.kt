package ke.co.virtual.pay.hiltdi.service

import ke.co.virtual.pay.hiltdi.model.Countries
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("region/europe/")
    suspend fun getCountries(): Response<Countries>
}