package com.aqube.truecaller.presentation.state

import androidx.lifecycle.LiveData
import io.reactivex.functions.Consumer

open class ConsumerLiveData<T> : LiveData<T>(), Consumer<T> {
    override fun accept(t: T) = postValue(t)

    fun acceptAsSet(t: T) {
        value = t
    }
}