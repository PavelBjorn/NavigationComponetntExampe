package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.navigation.NavController
import com.room.pavelfedor.navigationcomponetntexampe.R
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.Screen
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.ScreenNavHost

class ScreenNavHostLayout : FrameLayout, ScreenNavHost {

    private var navController: NavController? = null
    var screen: Screen? = null; private set

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs ?: return).recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs ?: return).recycle()
    }

    private fun init(context: Context, attrs: AttributeSet) = context.resources.obtainAttributes(
            attrs, R.styleable.ScreenNavHostLayout
    ).apply {
        init(context, getResourceId(R.styleable.ScreenNavHostLayout_graph, -1))
    }

    override fun attachScreen(screen: Screen) {
        detachScreen()
        this.screen = screen
        this.screen?.onAttach()
        (context as? LifecycleOwner)?.lifecycle?.addObserver(screen)
        addView(screen.inflateView(context, this))
    }

    override fun detachScreen() {
        (context as? LifecycleOwner)?.lifecycle?.removeObserver(screen ?: return)
        screen?.onDetach()
        screen = null
        removeAllViews()
    }

    override fun init(context: Context, navGraphId: Int) {
        navController = NavController(context).apply {
            navigatorProvider.addNavigator(ScreenNavigator(this@ScreenNavHostLayout))
            if (navGraphId != -1) setGraph(navGraphId)
        }
    }

    override fun getNavController(): NavController = navController!!
}
