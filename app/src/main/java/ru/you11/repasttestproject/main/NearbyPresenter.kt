package ru.you11.repasttestproject.main

class NearbyPresenter(private val fragment: NearbyFragment): NearbyContract.Presenter {

    init {
        fragment.presenter = this
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}