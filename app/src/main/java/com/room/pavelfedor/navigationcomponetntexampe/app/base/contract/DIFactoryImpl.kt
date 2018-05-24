package com.room.pavelfedor.navigationcomponetntexampe.app.base.contract

import android.util.SparseArray

class DIScreenFactoryImpl private constructor(
        private val components: SparseArray<Injector<Screen>> = SparseArray()
) : DIScreenFactory {

    override fun inject(screen: Screen) {
        components[screen.destinationId].inject(screen)
    }

    fun registerComponenst(components: List<Pair<Int, Injector<Screen>>>) {
        components.asSequence().forEach {
            this.components.put(it.first, it.second)
        }
    }

    companion object {
        fun with(components: List<Pair<Int, Injector<Screen>>>) = DIScreenFactoryImpl(
                SparseArray<Injector<Screen>>().apply {
                    components.asSequence().forEach {
                        this.put(it.first, it.second)
                    }
                }
        )
    }
}

class DIGraphFactoryImpl(
        private val components: SparseArray<DIScreenFactory> = SparseArray()
) : DIGraphFactory {

    override fun get(navGraph: Int) = components[navGraph]

    fun regiterDIScreenFactories(components: List<Pair<Int, DIScreenFactory>>) {
        components.asSequence().forEach {
            this.components.put(it.first, it.second)
        }
    }

    companion object {
        fun with(components: List<Pair<Int, DIScreenFactory>>) = DIGraphFactoryImpl(
                SparseArray<DIScreenFactory>().apply {
                    components.asSequence().forEach {
                        this.put(it.first, it.second)
                    }
                }
        )
    }
}
