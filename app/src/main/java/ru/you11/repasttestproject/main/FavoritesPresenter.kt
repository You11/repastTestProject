package ru.you11.repasttestproject.main

class FavoritesPresenter(private val fragment: FavoritesFragment): FavoriteContract.Presenter {

    init {
        fragment.presenter = this
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}