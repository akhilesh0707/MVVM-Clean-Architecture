package com.aqube.truecaller.data.repository

import io.reactivex.Flowable

interface BlogDataSource {
    fun getBlogs(): Flowable<String>
}