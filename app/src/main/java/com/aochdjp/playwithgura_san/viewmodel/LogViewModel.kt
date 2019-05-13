package com.aochdjp.playwithgura_san.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.aochdjp.playwithgura_san.model.Log
import com.aochdjp.playwithgura_san.model.repositories.logApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class LogViewModel : ViewModel(), LifecycleObserver {
    val logs = MutableLiveData<Log>()
    val isLoading = ObservableField<Boolean>(false)
    private val disposeBug = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getLogList(date: String) {
        logApi.log(date)
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribeBy(
                onNext = {
                    logs.postValue(it)
                }
            ).addTo(disposeBug)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBug.clear()
    }
}