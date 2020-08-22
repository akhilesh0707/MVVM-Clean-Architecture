package com.aqube.truecaller.data.repository

import io.reactivex.Observable

interface BlogDataSource {
    fun getBlogs(): Observable<String>
}