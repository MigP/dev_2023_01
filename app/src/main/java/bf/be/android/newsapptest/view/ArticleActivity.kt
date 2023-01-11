package bf.be.android.newsapptest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import bf.be.android.newsapptest.databinding.ActivityArticleBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "My News - Details"

        populateList()

        val backButton: FloatingActionButton = binding.backFloatingActionButton
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun populateList () {
        val itemTitle = binding.itemTitle
        itemTitle.text = intent.getStringExtra("TITLE")
        val itemDate = binding.itemDate
        itemDate.text = intent.getStringExtra("DATE")
        val itemPlaceOfPublication = binding.itemPlace
        itemPlaceOfPublication.text = intent.getStringExtra("PLACE_OF_PUBLICATION")
        val itemSubject = binding.itemSubject
        itemSubject.text = intent.getSerializableExtra("SUBJECT").toString()
        val itemLanguage = binding.itemLanguage
        itemLanguage.text = intent.getSerializableExtra("LANGUAGE").toString()
        val itemType = binding.itemType
        itemType.text = intent.getStringExtra("TYPE")
        val itemPage = binding.itemPage
        itemPage.text = intent.getStringExtra("PAGE")
        val itemFrequency = binding.itemFrequency
        itemFrequency.text = intent.getStringExtra("FREQUENCY")
        val itemEditionLabel = binding.itemLabel
        itemEditionLabel .text = intent.getStringExtra("EDITION_LABEL")
        val itemPublisher = binding.itemPublisher
        itemPublisher.text = intent.getStringExtra("PUBLISHER")
        val itemNote = binding.itemNotes
        itemNote.text = intent.getSerializableExtra("NOTE").toString()

        val itemOcrEngLink = binding.itemOcrLink
        if (intent.getStringExtra("OCR_ENG") == null) itemOcrEngLink.isVisible = false

        itemOcrEngLink.setOnClickListener {
            val originalDetailsIntent = Intent(this, OriginalArticleActivity::class.java)
            originalDetailsIntent.putExtra("TYPE", "ocr")
            originalDetailsIntent.putExtra("OCR_ENG", intent.getStringExtra("OCR_ENG"))
            startActivity(originalDetailsIntent)
        }

        val itemPdfLink = binding.itemPdfLink
        itemPdfLink.setOnClickListener {
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