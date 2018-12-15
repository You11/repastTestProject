package ru.you11.repasttestproject.payment

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.you11.repasttestproject.R
import ru.you11.repasttestproject.model.ApiService
import ru.you11.repasttestproject.model.Worker
import java.lang.Exception

class TipsPresenter(private val fragment: TipsFragment): TipsContract.Presenter {

    private var compDisposable = CompositeDisposable()

    init {
        fragment.presenter = this
    }


    override fun start() {
        loadWorkers(getRestaurantId())
    }

    override fun loadWorkers(restaurantId: Int) {
        val apiService = ApiService.create()
        compDisposable.add(apiService.getWorkersInRestaurant(restaurantId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                result.forEach {
                    it.photo = fragment.resources.getString(R.string.base_url) + it.photo
                }
                fragment.updateRVWithWorkers(result)
            })
    }

    override fun getRestaurantId(): Int {
        val id = fragment.arguments?.getInt("restaurantId")
        if (id == null || id == -1) throw Exception("Restaurant id not found")
        return id
    }

    override fun startPaymentFragment(worker: Worker) {
        val newFragment = PaymentFragment()
        val bundle = fragment.arguments
        bundle?.putInt("workerId", worker.id)
        bundle?.putString("workerName", worker.name)
        newFragment.arguments = bundle
        fragment.activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, newFragment)
            ?.addToBackStack(null)
            ?.commit()
        PaymentPresenter(newFragment)
    }

    override fun clearDisposable() {
        compDisposable.clear()
    }
}