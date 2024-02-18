package com.example.crytocurrencyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoinApplication : Application()

// This is basically just to give dagger hilt the information about our application so daggerHilt also has
// access to the application context if we need that for dependencies