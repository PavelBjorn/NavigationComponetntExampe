package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.Screen
import java.util.*

class ScreenStack {

    private val stack: LinkedList<Screen> = LinkedList()

    fun put(screen: Screen) {
        stack.push(screen)
    }

    fun updateScreenState(state: Screen) {
        stack.peek()?.setState(state.getState())
    }

    fun peek(): Screen = stack.peek()

    fun popBack(): Screen = stack.run {
        poll()
        peek()
    }

    fun isEmpty() = stack.isEmpty()
}
