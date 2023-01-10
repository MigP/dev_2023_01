package bf.be.android.newsapptest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bf.be.android.newsapptest.databinding.ActivityArticleBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("My News")

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
        val item_ocr_eng = binding.itemOcr
        item_ocr_eng.text = intent.getStringExtra("OCR_ENG")

        //TODO Also get pdf (original scan) from json response on the item_url request
        //TODO Instead of displaying the Ocr add a link to it like on the pdf
    }
}