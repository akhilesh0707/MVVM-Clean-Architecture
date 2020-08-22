package com.aqube.truecaller.data.repository

import io.reactivex.Observable

interface BlogRemote {
    fun getBlogs(): Observable<String>
}
