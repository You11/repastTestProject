package ru.you11.repasttestproject.payment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.you11.repasttestproject.R

class TipsFragment: Fragment(), TipsContract.View {

    override lateinit var presenter: TipsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_tips, container, false)
        with(root) {

        }

        return root
    }
}