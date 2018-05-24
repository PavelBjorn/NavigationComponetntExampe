package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.os.Bundle
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.Screen
import java.util.*
import kotlin.collections.ArrayList

class ScreenStack {

    private val stack: LinkedList<Screen.State> = LinkedList()

    fun put(screen: Screen.State) {
        stack.push(screen)
    }

    fun updateScreenState(state: Screen.State) {
        stack.peek()?.args = state.args
    }

    fun peek(): Screen.State? = stack.peek()

    fun popBack(): Screen.State? = stack.run {
        poll()
        peek()
    }

    fun isEmpty() = stack.isEmpty()

    fun saveStack() = Bundle().apply { putParcelableArrayList("stack", ArrayList(stack.map { it }.toList())) }

    fun restoreStack(bundle: Bundle) = stack.apply {
        clear()
        addAll(bundle.getParcelableArrayList("stack"))
    }
}
