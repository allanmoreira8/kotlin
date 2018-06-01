package br.com.kotlindemo.kotlindemo

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_restaurant_form.*

class RestaurantFormActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_form)
        includeButton.setOnClickListener {
            val intent = intent
            intent.putExtra("name", textInputEditText.text.toString())
            intent.putExtra("description", editText.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}