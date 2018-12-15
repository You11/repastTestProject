package ru.you11.repasttestproject.payment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.you11.repasttestproject.R

class PaymentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val bundle = intent.getBundleExtra("data")

        val tipsFragment = TipsFragment()
        tipsFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, tipsFragment)
            .commit()
        TipsPresenter(tipsFragment)
    }
}