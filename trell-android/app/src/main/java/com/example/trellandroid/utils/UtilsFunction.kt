package com.example.trellandroid.utils

import android.content.Context
import android.widget.Toast

object UtilsFunction {

    fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }
}