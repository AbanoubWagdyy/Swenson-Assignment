package com.swenson.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message:String){
    Toast.makeText(requireActivity(),message,Toast.LENGTH_SHORT).show()
}