package com.aqube.truecallertask.extension

import android.view.View

/**
 * Shorthand extension function to make view gone
 */
fun View.makeGone() {
    this.visibility = View.GONE
}

/**
 * Shorthand extension function to make view visible
 */
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

/**
 * Shorthand extension function to make view invisible
 */
fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}
