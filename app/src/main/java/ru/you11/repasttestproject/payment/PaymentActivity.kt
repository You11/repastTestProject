package ru.you11.repasttestproject.payment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.you11.repasttestproject.R
import java.lang.Exception

class PaymentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val restaurantId = intent.getIntExtra("restaurantId", -1)
        if (restaurantId == -1) throw Exception("Invalid restaurant id")

        val tipsFragment = TipsFragment()
        val args = Bundle()
        args.putInt("restaurantId", restaurantId)
        tipsFragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, tipsFragment)
            .commit()
        TipsPresenter(tipsFragment)
    }
}