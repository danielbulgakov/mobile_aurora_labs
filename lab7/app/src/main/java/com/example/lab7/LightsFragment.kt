package com.example.lab7

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [LightsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LightsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_lights, container, false)

        lightsAnimation(view)

        return view
    }

    private fun lightsAnimation(view : View) {
        val red = view.findViewById<ImageView>(R.id.imageViewRed)
        val yellow = view.findViewById<ImageView>(R.id.imageViewYellow)
        val green = view.findViewById<ImageView>(R.id.imageViewGreen)

        val stickman = view.findViewById<ImageView>(R.id.imageViewStickMan)

        // Starting color of all circles
        yellow.setImageResource(R.drawable.circle_transparent)
        green.setImageResource(R.drawable.circle_transparent)

        // Animation will start from traffic light being red
        red.setImageResource(R.drawable.circle_red)
        val defaultDelay = 500L
        var posXStart = 0f
        var posXEnd = 1000f

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            repeat(Int.MAX_VALUE) {
                delay(defaultDelay)
                red.setImageResource(R.drawable.circle_transparent)
                green.setImageResource(R.drawable.circle_green)
                // StickMan animation
                val anim = ObjectAnimator.ofFloat(stickman, "translationX", posXStart, posXEnd)
                anim.duration = 5 * defaultDelay
                anim.doOnEnd { stickman.scaleX = stickman.scaleX * -1 }
                anim.start()
                posXStart = posXEnd.also { posXEnd = posXStart }
                // End of StickMan animation
                delay(5 * defaultDelay)
                repeat(4) {
                    green.setImageResource(R.drawable.circle_green)
                    delay(defaultDelay)
                    green.setImageResource(R.drawable.circle_transparent)
                    delay(defaultDelay)
                }
                yellow.setImageResource(R.drawable.circle_yellow)
                delay(2 * defaultDelay)
                yellow.setImageResource(R.drawable.circle_transparent)
                delay(defaultDelay)
                red.setImageResource(R.drawable.circle_red)
                delay(5 * defaultDelay)
            }
        }




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LightsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LightsFragment().apply {

            }
    }
}