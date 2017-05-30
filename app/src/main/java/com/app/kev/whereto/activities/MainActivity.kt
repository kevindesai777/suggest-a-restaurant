package com.app.kev.whereto.activities

import android.animation.Animator
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

import com.app.kev.whereto.R
import com.app.kev.whereto.enums.PriceRange
import com.app.kev.whereto.enums.Transport
import com.app.kev.whereto.models.Restaurant

import io.realm.Realm

import org.json.JSONObject
import java.io.IOException
import kotlin.properties.Delegates
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var realm: Realm by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Add place feature to be added soon. ", Snackbar.LENGTH_SHORT)
                    .show()
        }

        realm = Realm.getDefaultInstance()

        initializeData()

        val button = findViewById(R.id.suggest_place) as Button
        button.setOnClickListener { view ->
            initializeAnimation()
        }
    }

    fun initializeAnimation() {
        val obj = JSONObject(loadJSONFromAsset())
        animation_view.setAnimation(obj)
        animation_view.loop(false)

        animation_view.visibility = View.VISIBLE

        animation_view.playAnimation()

        animation_view.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                //Not needed
            }

            override fun onAnimationEnd(animation: Animator?) {
                hello_world.setText("Anim complete")
                animation_view.visibility = View.INVISIBLE
                suggest_place.isClickable = true

                val random = Random()
                val listResto = realm.where(Restaurant::class.java).findAll()
                val restaurant = listResto.get(random.nextInt(listResto.size))

                hello_world.setText("Today you should go to: " + restaurant.getName())
            }

            override fun onAnimationCancel(animation: Animator?) {
                //Not needed
            }

            override fun onAnimationStart(animation: Animator?) {
                suggest_place.isClickable = false
            }

        })

    }

    fun initializeData() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val dataInitialized = preferences.getBoolean("dataInitDone", false)
        if(!dataInitialized) {

            deleteDatabase()

            createRealmDatabase(Restaurant("1117 Cafe", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Bagel Boys", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Tropical Smoothie", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Atlanta Bread", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Royal Oak Pub", PriceRange.CHEAP, Transport.WALK, false))
            createRealmDatabase(Restaurant("Tindrum Asian Kitchen", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Carraba's Italian Grill", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Mimi's Cafe", PriceRange.MODERATE, Transport.WALK, true))
            createRealmDatabase(Restaurant("Zoes Kitchen", PriceRange.CHEAP, Transport.WALK, false))
            createRealmDatabase(Restaurant("la Madeleine French Bakery and Café", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Bawarchi Biryani", PriceRange.MODERATE, Transport.DRIVE, true))
            createRealmDatabase(Restaurant("Perimeter Sweet Tomatoes", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Rumi's Kitchen", PriceRange.EXPENSIVE, Transport.DRIVE, true))
            createRealmDatabase(Restaurant("Happy Sumo", PriceRange.CHEAP, Transport.WALK, false))
            createRealmDatabase(Restaurant("Chick-fil-A", PriceRange.CHEAP, Transport.WALK, false))
            createRealmDatabase(Restaurant("Galla's Pizza", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("JoeyD's Oak Room", PriceRange.MODERATE, Transport.WALK, false))
            createRealmDatabase(Restaurant("Taco Mac", PriceRange.MODERATE, Transport.WALK, true))
            createRealmDatabase(Restaurant("Outback Steakhouse", PriceRange.MODERATE, Transport.WALK, false))
            createRealmDatabase(Restaurant("Panera Bread", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Chipotle Mexican Grill", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Your Pie", PriceRange.CHEAP, Transport.WALK, true))
            createRealmDatabase(Restaurant("Fleming's Prime Steakhouse", PriceRange.EXPENSIVE, Transport.DRIVE, false))
            createRealmDatabase(Restaurant("Tin Lizzy's Catina", PriceRange.MODERATE, Transport.DRIVE, false))
            createRealmDatabase(Restaurant("Shane's Rib Shack", PriceRange.MODERATE, Transport.WALK, false))
            createRealmDatabase(Restaurant("Boneheads", PriceRange.CHEAP, Transport.DRIVE, false))


            val editor = preferences.edit()
            editor.putBoolean("dataInitDone", true)
            editor.apply()
        }
    }

    fun loadJSONFromAsset(): String? {
        var json: String
        try {
            val `is` = assets.open("cooking_app.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    fun createRealmDatabase(restaurant: Restaurant) {
        realm.beginTransaction()
        realm.copyToRealm(restaurant)
        realm.commitTransaction()
    }

    fun deleteDatabase(){
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}
