package br.com.kotlindemo.kotlindemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.kotlindemo.kotlindemo.model.FactoryDAO
import br.com.kotlindemo.kotlindemo.model.Restaurant
import br.com.kotlindemo.kotlindemo.model.RestaurantDAO
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var restaurantDAO: RestaurantDAO
    val lista: MutableList<Restaurant> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restaurantDAO = FactoryDAO.getPlaceDatabase(applicationContext)

        addButton.setOnClickListener {
            val intent = Intent(this, RestaurantFormActivity::class.java)
            startActivityForResult(intent, 200)
        }
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = RestaurantAdapter(lista)

        loadPlaces()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val restaurant = Restaurant(data?.getStringExtra("name")?:"", data?.getStringExtra("description")?:"");
            insertPlace(restaurant)
        }
    }

    private fun loadPlaces() {
        FactoryDAO.getPlaceDatabase(applicationContext).getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    lista.clear()
                    lista.addAll(it)
                    recycleView.adapter.notifyDataSetChanged()
                }
    }

    private fun insertPlace(place: Restaurant) {
        Completable.fromAction { restaurantDAO.insert(place) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loadPlaces()
                    Snackbar.make(mainContainer, "Restaurante adicionado com sucesso: " + place.name, Toast.LENGTH_SHORT).show()
                }
    }


}
