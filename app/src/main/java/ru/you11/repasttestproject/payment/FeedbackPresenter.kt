package ru.you11.repasttestproject.payment

import ru.you11.repasttestproject.model.Restaurant
import ru.you11.repasttestproject.model.Worker

class FeedbackPresenter(private val fragment: FeedbackFragment): FeedbackContract.Presenter {

    private var worker: Worker? = null
    private var restaurant: Restaurant? = null

    init {
        fragment.presenter = this
        restaurant = fragment.arguments?.getParcelable("restaurant")!!
        worker = fragment.arguments?.getParcelable("worker")!!
    }

    override fun start() {

    }

    override fun getRestaurantDescription(): String {
        return restaurant?.description ?: ""
    }

    override fun getWorkerName(): String {
        return worker?.name ?: ""
    }

    override fun getWorkerPhotoUrl(): String {
        return worker?.photo ?: ""
    }

    override fun getWorkerPosition(): String {
        return worker?.position ?: ""
    }
}