package com.app.kev.whereto.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.app.kev.whereto.R
import com.app.kev.whereto.enums.PriceRange
import com.app.kev.whereto.enums.Transport
import com.app.kev.whereto.models.Restaurant

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Add place feature to be added soon. ", Snackbar.LENGTH_SHORT)
                    .show()
        }

        initializeData()

    }

    private fun initializeData() {
        Restaurant("1117 Cafe", PriceRange.CHEAP, Transport.WALK)
        Restaurant("Bagel Boys", PriceRange.CHEAP, Transport.WALK)
        Restaurant("1117 Cafe", PriceRange.CHEAP, Transport.WALK)
        Restaurant("1117 Cafe", PriceRange.CHEAP, Transport.WALK)
        Restaurant("1117 Cafe", PriceRange.CHEAP, Transport.WALK)
    }

}
