package com.aqube.truecaller.data.repository

import io.reactivex.Flowable

interface BlogRemote {
    fun getBlogs(): Flowable<String>
}
