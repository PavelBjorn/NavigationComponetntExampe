package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.os.Bundle
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.BaseScreen
import java.util.*
import kotlin.collections.ArrayList

class ScreenStack {

    private val stack: LinkedList<BaseScreen.State> = LinkedList()

    fun put(screen: BaseScreen.State) {
        stack.push(screen)
    }

    fun updateScreenState(state: BaseScreen.State) {
        stack.peek()?.args = state.args
    }

    fun peek(): BaseScreen.State? = stack.peek()

    fun popBack(): BaseScreen.State? = stack.run {
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
