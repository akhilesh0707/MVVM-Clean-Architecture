package com.aqube.truecaller.presentation.state

interface UiModel

open class  TransientAwareUiModel: UiModel {
    var isRedelivered: Boolean = false
}