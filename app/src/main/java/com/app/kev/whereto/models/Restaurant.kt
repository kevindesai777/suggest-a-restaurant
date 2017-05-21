package com.app.kev.whereto.models

import com.app.kev.whereto.enums.PriceRange
import com.app.kev.whereto.enums.Transport

/**
 * Created by KevinDesai on 5/20/17.
 */
data class Restaurant(val name: String, val priceRange: PriceRange, val distance: Transport)