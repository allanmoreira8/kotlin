package br.com.kotlindemo.kotlindemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val lista = mutableListOf<Place>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButton.setOnClickListener {
            val intent = Intent(this, RestaurantFormActivity::class.java)
            startActivityForResult(intent, 200)
        }
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = RestaurantAdapter(lista)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val place = Place(data?.getStringExtra("name")?:"", data?.getStringExtra("description")?:"");
            lista.add(place)
            recycleView.adapter.notifyDataSetChanged()
        }
    }
}
