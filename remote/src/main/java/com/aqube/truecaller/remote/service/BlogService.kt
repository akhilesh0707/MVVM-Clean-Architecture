package com.aqube.truecaller.remote.service

import io.reactivex.Flowable
import retrofit2.http.GET

interface BlogService {
    @GET("2018/01/22/life-as-an-android-engineer/")
    fun getBlogResponse(): Flowable<String>
}
