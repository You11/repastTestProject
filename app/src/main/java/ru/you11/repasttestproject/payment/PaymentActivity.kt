package ru.you11.repasttestproject.payment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.you11.repasttestproject.R
import ru.you11.repasttestproject.model.Restaurant

class PaymentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val restaurant = intent.getParcelableExtra<Restaurant>("restaurant")
        val bundle = Bundle()
        bundle.putParcelable("restaurant", restaurant)

        val tipsFragment = TipsFragment()
        tipsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, tipsFragment)
            .commit()
        TipsPresenter(tipsFragment)
    }
}