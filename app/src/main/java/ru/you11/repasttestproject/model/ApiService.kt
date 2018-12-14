package ru.you11.repasttestproject.model

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.you11.repasttestproject.App
import ru.you11.repasttestproject.R

interface ApiService {

    @GET("restoapp.php")
    fun getRestaurants(
        @Query("param") param: String
    ): Observable<ArrayList<Restaurant>>

    @GET("restoapp.php")
    fun getWorkers(
        @Query("waiters_all") isAllWaiters: Boolean
    ): Observable<ArrayList<Workers>>

    @GET("restoapp.php")
    fun getTipsForUser(
        @Query("favorite") isFavorite: Boolean,
        @Query("user_id") id: String
    ): Observable<ArrayList<Tips>>

    @GET("restoapp.php")
    fun getAllTips(
        @Query("all_tip") allTips: Boolean
    ): Observable<ArrayList<Tips>>

    @GET("restoapp.php")
    fun getWorkersInRestaurant(
        @Query("waiters") id: Int
    ): Observable<ArrayList<Workers>>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(App.applicationContext().resources.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}