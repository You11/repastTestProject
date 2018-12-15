package ru.you11.repasttestproject.main

import android.content.Intent
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.you11.repasttestproject.R
import ru.you11.repasttestproject.model.ApiService
import ru.you11.repasttestproject.model.Restaurant
import ru.you11.repasttestproject.payment.PaymentActivity

class NearbyPresenter(private val fragment: NearbyFragment) : NearbyContract.Presenter {

    private var compDisposable = CompositeDisposable()

    init {
        fragment.presenter = this
    }

    override fun start() {
        loadRestaurants()
    }

    override fun loadRestaurants() {
        val apiService = ApiService.create()
        //TODO: how the hell API has typos
        compDisposable.add(apiService.getRestaurants("restaraunts")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                result.forEach {
                    it.photo = fragment.resources.getString(R.string.base_url) + it.photo
                }
                fragment.updateRVWithRestaurants(result)
            })
    }

    override fun startPaymentActivity(restaurant: Restaurant) {
        val intent = Intent(fragment.activity, PaymentActivity::class.java)
        intent.putExtra("restaurant", restaurant)
        fragment.startActivity(intent)
    }
}