package com.digigene.android.moviefinder.wrapper

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersWrapper {

    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()

}