package com.toandv.aloalo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import com.toandv.aloalo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.sw.setOnSwipeListener { s1, s2 ->
            binding.fl1.layoutParams.width = s1
            binding.fl1.requestLayout()
            binding.fl2.layoutParams.width = s2
            binding.fl2.requestLayout()
        }
    }
}

class SwitchExt @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SwitchCompat(context, attrs) {

    private var listener: OnSwipeListener? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val s1 = thumbDrawable.bounds.centerX() - left
        val s2 = right - thumbDrawable.bounds.centerX()
        listener?.onSwipe(s1, s2)
    }

    fun setOnSwipeListener(listener: OnSwipeListener) {
        this.listener = listener
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun performClick() = false

    fun interface OnSwipeListener {
        fun onSwipe(s1: Int, s2: Int)
    }
}
