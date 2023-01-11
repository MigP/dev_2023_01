package bf.be.android.newsapptest.view

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import bf.be.android.newsapptest.R
import bf.be.android.newsapptest.databinding.ActivitySplashscreenBinding

class Splashscreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, NewsListActivity::class.java)
            startActivity(intent)
            finish()
            }, 3000)

        splashAnimations()
    }

    private fun splashAnimations() {
        // Splash globe animation
        val splashGlobe: ImageView = binding.globe
        val globeAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.globe_slide)
        globeAnimation.startOffset = 0
        splashGlobe.startAnimation(globeAnimation)

        // Splash globe text animation
        val splashGlobeText: TextView = binding.globeText
        val globeTextAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.text_slide)
        globeTextAnimation.startOffset = 0
        splashGlobeText.startAnimation(globeTextAnimation)

        // Splash welcome animation
        val splashWelcome: TextView = binding.welcome
        val welcomeBlowUp: ValueAnimator = ValueAnimator.ofFloat(0f, 65f)
        welcomeBlowUp.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            splashWelcome.textSize = animatedValue
        }
        welcomeBlowUp.duration = 1000
        welcomeBlowUp.startDelay = 0
        welcomeBlowUp.start()
    }
}