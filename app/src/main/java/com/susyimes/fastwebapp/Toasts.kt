package com.susyimes.fastwebapp


import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(message: Any): Toast = Toast
    .makeText(this, message.toString(), Toast.LENGTH_SHORT)
    .apply {
        setGravity(Gravity.CENTER,0,0)
        show()
    }

fun Fragment.toast(message: Any): Toast = Toast
    .makeText(this.context, message.toString(), Toast.LENGTH_SHORT)
    .apply {
        setGravity(Gravity.CENTER,0,0)
        show()
    }

