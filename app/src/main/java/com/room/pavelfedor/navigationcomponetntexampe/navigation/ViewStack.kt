package com.room.pavelfedor.navigationcomponetntexampe.navigation

import android.os.Bundle
import java.util.*
import kotlin.collections.ArrayList

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

    fun saveStak() = Bundle().apply { putParcelableArrayList("stack", ArrayList(stack.map { it }.toList())) }

    fun restoreStack(bundle: Bundle) = stack.apply {
        clear()
        addAll(bundle.getParcelableArrayList("stack"))
    }
}
