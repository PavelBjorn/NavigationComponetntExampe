package com.room.pavelfedor.navigationcomponetntexampe.navigation

import android.os.Bundle

interface BaseView {

    fun setArgs(args: Bundle?)

    fun getArgs(): Bundle
}
