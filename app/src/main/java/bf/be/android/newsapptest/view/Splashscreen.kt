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

        // Makes sure the app colours are preserved during dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Sets the splashscreen window to full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Heads to new activity after 3 secondsfter 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, NewsListActivity::class.java)
            startActivity(intent)
            finish()
            }, 3000)

        // Displays splashscreen animations
        splashAnimations()
    }

    private fun splashAnimations() {
        // Globe animation
        val splashGlobe: ImageView = binding.globe
        val globeAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.globe_slide)
        globeAnimation.startOffset = 0
        splashGlobe.startAnimation(globeAnimation)

        // Globe text animation
        val splashGlobeText: TextView = binding.globeText
        val globeTextAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.text_slide)
        globeTextAnimation.startOffset = 0
        splashGlobeText.startAnimation(globeTextAnimation)

        // Welcome text animation
        val splashWelcome: TextView = binding.welcome

        val welcomeBlowUp: ValueAnimator = ValueAnimator.ofFloat(0f, 85f)
        welcomeBlowUp.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            splashWelcome.textSize = animatedValue
        }
        welcomeBlowUp.duration = 550
        welcomeBlowUp.startDelay = 800
        welcomeBlowUp.start()

        val welcomeShrinkDown: ValueAnimator = ValueAnimator.ofFloat(85f, 65f)
        welcomeShrinkDown.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            splashWelcome.textSize = animatedValue
        }
        welcomeShrinkDown.duration = 150
        welcomeShrinkDown.startDelay = 1350
        welcomeShrinkDown.start()
    }
}