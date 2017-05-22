package com.app.kev.whereto.models

import com.app.kev.whereto.enums.PriceRange
import com.app.kev.whereto.enums.Transport
import io.realm.RealmObject
import io.realm.annotations.Ignore

/**
 * Created by KevinDesai on 5/20/17.
 */
open class Restaurant() : RealmObject() {
    private var name: String = ""
    private var priceRange: String = ""
    private var dist: String = ""

    @Ignore
    var pricerange: PriceRange = PriceRange.CHEAP
    @Ignore
    var distance: Transport = Transport.WALK

    constructor(name : String, priceRange: PriceRange, distance : Transport) : this() {
        this.name = name
        this.pricerange = priceRange
        this.distance = distance

        setPriceRange(priceRange)
        setTransport(distance)
    }

    fun setPriceRange(`val`: PriceRange) {
        this.priceRange = `val`.toString()
    }

    fun getPriceRange(): PriceRange {
        return PriceRange.valueOf(priceRange)
    }

    fun setTransport(`val`: Transport) {
        this.dist = `val`.toString()
    }

    fun getTransport(): Transport {
        return Transport.valueOf(dist)
    }
}