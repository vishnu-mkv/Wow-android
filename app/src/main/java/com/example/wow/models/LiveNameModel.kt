package com.example.wow.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.fixedRateTimer

class LiveNameModel:ViewModel() {

    val nameData = MutableLiveData("")

    init {
        fixedRateTimer("countReseter", false, 0L, 5 * 1000) {
            Log.d("Counter", "Resetting")
            nameData.postValue("")
        }
    }
    fun updateValue(newValue: String) {
        nameData.value = newValue
    }
}