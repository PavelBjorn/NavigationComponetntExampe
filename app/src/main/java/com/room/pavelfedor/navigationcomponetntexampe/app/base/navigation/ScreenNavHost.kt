package com.room.pavelfedor.navigationcomponetntexampe.app.base.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.room.pavelfedor.navigationcomponetntexampe.R
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.Screen
import com.room.pavelfedor.navigationcomponetntexampe.app.base.contract.DIScreenFactory

class ScreenNavHost : FrameLayout, NavHost {

    private var screen: Screen? = null

    private var navController: NavController? = null

    private var factory: DIScreenFactory? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs ?: return).recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs ?: return).recycle()
    }

    private fun init(context: Context, attrs: AttributeSet) = context.resources.obtainAttributes(
            attrs,
            R.styleable.ScreenNavHost
    ).apply{
        navController = NavController(context).apply {
            navigatorProvider.addNavigator(ScreenNavigator(this@ScreenNavHost))
            getResourceId(R.styleable.ScreenNavHost_navigation_graph, -1).run {
                if (this != -1) {
                    setGraph(this)
                }
            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun getScreenState(): Screen.State? = screen?.saveState()

    override fun onDetachedFromWindow() {
        screen?.apply { (context as AppCompatActivity).lifecycle.removeObserver(this) }
        super.onDetachedFromWindow()
    }

    fun setScreen(screen: Screen) {
        factory?.inject(screen)
        this.screen = screen
        removeAllViews()
        addView(screen.view as View)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        screen?.onActivityResult(requestCode, resultCode, data)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        screen?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun getNavController(): NavController = navController!!
}
