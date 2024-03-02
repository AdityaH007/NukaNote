package com.example.nukanote.com.example.nukanote.ui

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nukanote.R

class LandingPage : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing_page)
        mediaPlayer = MediaPlayer.create(this, R.raw.fallout)

        val imageView: ImageView = findViewById(R.id.standby)
        val Button:Button=findViewById(R.id.terminal)



        val animation = TranslateAnimation(0f, 0f, 0f, -1400f)
        animation.duration = 2500
        animation.fillAfter = true


        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                mediaPlayer?.start()
                Button.visibility= View.VISIBLE
                // Code to run when the animation starts
            }

            override fun onAnimationEnd(animation: Animation?) {


                // Code to run when the animation ends
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Code to run when the animation repeats
            }
        })

        // Start the animation on your image view
        imageView.startAnimation(animation)

        Button.setOnClickListener {
            mediaPlayer?.start()
            val intent =Intent(this, Homepage::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
