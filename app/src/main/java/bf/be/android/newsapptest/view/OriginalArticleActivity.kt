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

        if (intent.getStringExtra("TYPE").equals("ocr")) {
            binding.ocrScrollView.isVisible = true
            binding.articleOcr.text = intent.getStringExtra("OCR_ENG")
            setTitle("My News - OCR of the original")
        } else if (intent.getStringExtra("TYPE").equals("pdf")) {
            binding.articlePdf.isVisible = true

            binding.articlePdf.webViewClient = WebViewClient()
            binding.articlePdf.settings.setSupportZoom(true)
            binding.articlePdf.getSettings().setBuiltInZoomControls(true);

            val url = intent.getStringExtra("URL")
            binding.articlePdf.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
            setTitle("My News - Scan of the original")
        }

        val backButton: FloatingActionButton = binding.backFloatingActionButton
        backButton.setOnClickListener {
            finish()
        }
    }
}