package bf.be.android.newsapptest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.isVisible
import bf.be.android.newsapptest.databinding.ActivityArticleBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("My News - Details")

        populateList()

        val backButton: FloatingActionButton = binding.backFloatingActionButton
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun populateList () {
        val item_title = binding.itemTitle
        item_title.text = intent.getStringExtra("TITLE")
        val item_date = binding.itemDate
        item_date.text = intent.getStringExtra("DATE")
        val item_place_of_publication = binding.itemPlace
        item_place_of_publication.text = intent.getStringExtra("PLACE_OF_PUBLICATION")
        val item_subject = binding.itemSubject
        item_subject.text = intent.getSerializableExtra("SUBJECT").toString()
        val item_language = binding.itemLanguage
        item_language.text = intent.getSerializableExtra("LANGUAGE").toString()
        val item_type = binding.itemType
        item_type.text = intent.getStringExtra("TYPE")
        val item_page = binding.itemPage
        item_page.text = intent.getStringExtra("PAGE")
        val item_frequency = binding.itemFrequency
        item_frequency.text = intent.getStringExtra("FREQUENCY")
        val item_edition_label = binding.itemLabel
        item_edition_label .text = intent.getStringExtra("EDITION_LABEL")
        val item_publisher = binding.itemPublisher
        item_publisher.text = intent.getStringExtra("PUBLISHER")
        val item_note = binding.itemNotes
        item_note.text = intent.getSerializableExtra("NOTE").toString()

        val item_ocr_engLink = binding.itemOcrLink
        if (intent.getStringExtra("OCR_ENG") == null) item_ocr_engLink.isVisible = false
        
        item_ocr_engLink.setOnClickListener {
            val originalDetailsIntent = Intent(this, OriginalArticleActivity::class.java)
            originalDetailsIntent.putExtra("TYPE", "ocr")
            originalDetailsIntent.putExtra("OCR_ENG", intent.getStringExtra("OCR_ENG"))
            startActivity(originalDetailsIntent)
        }

        val item_pdfLink = binding.itemPdfLink
        item_pdfLink.setOnClickListener {
            val originalDetailsIntent = Intent(this, OriginalArticleActivity::class.java)
            originalDetailsIntent.putExtra("TYPE", "pdf")
            originalDetailsIntent.putExtra(
                "URL",
                intent.getStringExtra("URL")?.replace(".json", ".pdf")
            )
            startActivity(originalDetailsIntent)
        }
    }
}