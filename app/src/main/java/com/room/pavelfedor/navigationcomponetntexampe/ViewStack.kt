package com.room.pavelfedor.navigationcomponetntexampe

import android.os.Bundle
import java.util.*

class ViewStack {

    private val stack: LinkedList<ViewNavigator.ViewState> = LinkedList()

    fun put(viewState: ViewNavigator.ViewState) {
        stack.push(viewState)
    }

    fun updateState(args: Bundle) {
        stack.peek()?.putArgs(args)
    }

    fun get(): ViewNavigator.ViewState? = stack.run {
        poll()
        peek()
    }

    fun isEmpty() = stack.isEmpty()
}
