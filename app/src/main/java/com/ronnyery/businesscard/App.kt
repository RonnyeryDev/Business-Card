package com.ronnyery.businesscard

import android.app.Application
import com.ronnyery.businesscard.data.AppDatabase
import com.ronnyery.businesscard.data.BusinessCardRepository

class App: Application() {
    val database by lazy{AppDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}