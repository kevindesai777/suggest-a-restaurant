package com.app.kev.whereto

import android.app.Application
import io.realm.Realm

/**
 * Created by KevinDesai on 5/21/17.
 */
class WhereToApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //Initialize Realm
        Realm.init(this)
    }
}