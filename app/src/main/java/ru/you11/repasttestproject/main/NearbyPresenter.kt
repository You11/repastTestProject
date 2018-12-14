package ru.you11.repasttestproject.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.you11.repasttestproject.R
import ru.you11.repasttestproject.model.ApiService

class NearbyPresenter(private val fragment: NearbyFragment) : NearbyContract.Presenter {

    private var compDisposable = CompositeDisposable()

    init {
        fragment.presenter = this
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadRestaurants() {
        val apiService = ApiService.create()
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
}