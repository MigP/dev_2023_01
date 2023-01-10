package bf.be.android.newsapptest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bf.be.android.newsapptest.databinding.ActivityArticleBinding
import bf.be.android.newsapptest.model.apis.Items
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("My News")

        val backButton: FloatingActionButton = binding.backFloatingActionButton
        backButton.setOnClickListener {
            finish()
        }

        //TODO ----------------------------- Gets data from intent
        val item_title = intent.getStringExtra("TITLE")
        val item_date = intent.getStringExtra("DATE")
        val item_page = intent.getStringExtra("PAGE")
        val item_place_of_publication = intent.getStringExtra("PLACE_OF_PUBLICATION")
        val item_language = intent.getStringArrayExtra("LANGUAGE")
        val item_note = intent.getStringArrayExtra("NOTE")
        val item_subject = intent.getStringArrayExtra("SUBJECT")
        val item_frequency = intent.getStringExtra("FREQUENCY")
        val item_type = intent.getStringExtra("TYPE")
        val item_edition_label = intent.getStringExtra("EDITION_LABEL")
        val item_publisher = intent.getStringExtra("PUBLISHER")
        val item_url = intent.getStringExtra("URL")
        val item_ocr_eng = intent.getStringExtra("OCR_ENG")
        //TODO ------------------------------------------
        //TODO Also get pdf (original scan) from json response on the item_url request

        println("-------------- title: " + item_title)
        println("-------------- item_place_of_publication: " + item_place_of_publication)
        println("-------------- item_frequency: " + item_frequency)
        println("-------------- item_publisher: " + item_publisher)
    }
}