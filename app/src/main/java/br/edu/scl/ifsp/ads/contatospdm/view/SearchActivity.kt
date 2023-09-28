package br.edu.scl.ifsp.ads.contatospdm.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.scl.ifsp.ads.contatospdm.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private val asb: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(asb.root)

        supportActionBar?.setTitle("Search Contact")

        asb.searchButton.setOnClickListener {
            val query = asb.searchEditText.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("query", query)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
