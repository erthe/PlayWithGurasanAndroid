package com.aochdjp.playwithgura_san.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.aochdjp.playwithgura_san.model.Menu
import com.aochdjp.playwithgura_san.model.repositories.abstracts.ApiFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MenuViewModel : ViewModel(), LifecycleObserver {
    val menus = MutableLiveData<Menu>()
    val isLoading = ObservableField<Boolean>(false)
    private val disposeBug = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getMenuList() {
        ApiFactory.menuApi.menu()
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribeBy(
                onNext = {
                    menus.postValue(it)
                }
            ).addTo(disposeBug)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBug.clear()
    }
}