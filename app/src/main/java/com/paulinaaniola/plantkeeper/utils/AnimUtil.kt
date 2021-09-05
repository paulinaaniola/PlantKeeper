package com.paulinaaniola.plantkeeper.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

const val FADE_OUT_IN_ANIM_DURATION = 500L
fun View.fadeIn() {
    this.apply {
        alpha = 0f
        visibility = View.VISIBLE
        animate()
            .alpha(1f).duration = FADE_OUT_IN_ANIM_DURATION
    }
}

fun View.fadeOut() {
    this.apply {
        alpha = 1f
        animate()
            .alpha(0f)
            .setDuration(FADE_OUT_IN_ANIM_DURATION)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    this@apply.visibility = View.GONE
                }
            }
            )
    }
}