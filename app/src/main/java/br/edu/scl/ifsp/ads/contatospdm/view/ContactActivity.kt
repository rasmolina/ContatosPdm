package br.edu.scl.ifsp.ads.contatospdm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.scl.ifsp.ads.contatospdm.R
import br.edu.scl.ifsp.ads.contatospdm.databinding.ActivityContactBinding
import br.edu.scl.ifsp.ads.contatospdm.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.ads.contatospdm.model.Contact
import kotlin.random.Random

class ContactActivity : AppCompatActivity() {

    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)
        with(acb){
            saveBt.setOnClickListener {
                val contact: Contact = Contact(
                    id=generateId(),nameEt.text.toString(),
                    addressEt.text.toString(),
                    phoneEt.text.toString(),
                    emailEt.text.toString()
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_CONTACT,contact)
                setResult(RESULT_OK,resultIntent)
                finish()

            }

        }
    }

    private fun generateId() = Random(System.currentTimeMillis()).nextInt()
}