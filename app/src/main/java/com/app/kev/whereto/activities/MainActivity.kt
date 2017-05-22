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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException
import kotlin.properties.Delegates

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
            }

            override fun onAnimationCancel(animation: Animator?) {
                //Not needed
            }

            override fun onAnimationStart(animation: Animator?) {
                //Not needed
            }

        })

    }

    fun initializeData() {
        Restaurant("1117 Cafe", PriceRange.CHEAP, Transport.WALK)
        Restaurant("Bagel Boys", PriceRange.CHEAP, Transport.WALK)
        Restaurant("Tropical Smoothie", PriceRange.CHEAP, Transport.WALK)
        Restaurant("Atlanta Bread", PriceRange.CHEAP, Transport.WALK)
        Restaurant("Royal Oak Pub", PriceRange.CHEAP, Transport.WALK)
    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
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

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}
