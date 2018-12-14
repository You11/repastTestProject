package ru.you11.repasttestproject.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.you11.repasttestproject.R

class FavoritesFragment: Fragment(), FavoriteContract.View {

    override lateinit var presenter: FavoriteContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
}