package bf.be.android.newsapptest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bf.be.android.newsapptest.databinding.ActivityArticleBinding
import bf.be.android.newsapptest.model.apis.Items

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("My News")

        val item_title = intent.getStringExtra("TITLE")

        if (item_title != null) {
            println("************************ item1 title: " + item_title)
        }
    }
}