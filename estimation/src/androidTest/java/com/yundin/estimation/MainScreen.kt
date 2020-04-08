package com.yundin.estimation

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = MainActivity::class.java
}