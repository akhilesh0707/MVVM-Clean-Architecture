package com.aqube.truecaller.data.repository

import io.reactivex.Observable

interface BlogRemote {
    fun getBlog(): Observable<String>
}
