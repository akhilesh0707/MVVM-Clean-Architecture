package com.aqube.truecaller.domain.repository

import io.reactivex.Observable

interface BlogRepository {
    fun getBlogs(): Observable<String>
}