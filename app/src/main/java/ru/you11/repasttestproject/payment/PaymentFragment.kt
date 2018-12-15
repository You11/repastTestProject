package ru.you11.repasttestproject.payment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import ru.yandex.money.android.sdk.*
import ru.you11.repasttestproject.R

class PaymentFragment: Fragment(), PaymentContract.View {

    override lateinit var presenter: PaymentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Checkout.attach(activity?.supportFragmentManager!!)

        Checkout.setResultCallback(object: Checkout.ResultCallback {
            override fun onResult(paymentToken: String, type: PaymentMethodType) {
                presenter.startFeedbackFragment()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_payment, container, false)
        with(root) {
            val amountET: EditText = findViewById(R.id.payment_amount_edit_text)
            val payButton: Button = findViewById(R.id.payment_pay_button)
            payButton.setOnClickListener {
                presenter.pay(amountET.text.toString().toDouble())
            }

            amountET.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    payButton.isEnabled = !editable.isNullOrBlank()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

            })
        }

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        Checkout.detach()
    }
}