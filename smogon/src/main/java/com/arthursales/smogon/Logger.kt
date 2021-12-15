package com.arthursales.smogon

import android.util.Log

class Logger {
    private val TAG = "Logger"
    fun error(message: String?) {
        error(TAG, message)
    }

    fun error(tag: String?, message: String?) {
        Log.e(tag, message)
    }
}