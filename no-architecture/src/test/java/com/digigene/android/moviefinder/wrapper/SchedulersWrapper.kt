package com.digigene.android.moviefinder.wrapper

import io.reactivex.schedulers.Schedulers

class SchedulersWrapper {

    fun io() = Schedulers.trampoline()
    fun ui() = Schedulers.trampoline()

}