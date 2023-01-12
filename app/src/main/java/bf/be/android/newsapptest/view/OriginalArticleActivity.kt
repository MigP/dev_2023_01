package bf.be.android.newsapptest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import bf.be.android.newsapptest.databinding.ActivityOriginalArticleBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OriginalArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOriginalArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOriginalArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Changes are made depending on which type was passed through intent (ocr or pdf)
        if (intent.getStringExtra("TYPE").equals("ocr")) {
            binding.ocrScrollView.isVisible = true
            binding.articleOcr.text = intent.getStringExtra("OCR_ENG")
            title = "My News - English OCR"
        } else if (intent.getStringExtra("TYPE").equals("pdf")) {
            binding.articlePdf.isVisible = true
            binding.articlePdf.webViewClient = WebViewClient()
            binding.articlePdf.settings.setSupportZoom(true)
            binding.articlePdf.settings.javaScriptEnabled = true
            binding.articlePdf.settings.builtInZoomControls = true

            val url = intent.getStringExtra("URL")
            binding.articlePdf.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
            title = "My News - Scan of the original"
        }

        // Sets up click listeners for the back button
        val backButton: FloatingActionButton = binding.backFloatingActionButton
        backButton.setOnClickListener {
            finish()
        }
    }
}