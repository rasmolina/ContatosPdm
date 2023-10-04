package br.edu.scl.ifsp.ads.contatospdm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.scl.ifsp.ads.contatospdm.R
import br.edu.scl.ifsp.ads.contatospdm.databinding.ActivityContactBinding
import br.edu.scl.ifsp.ads.contatospdm.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.ads.contatospdm.model.Constant.INVALID_CONTACT_ID
import br.edu.scl.ifsp.ads.contatospdm.model.Constant.VIEW_CONTACT
import br.edu.scl.ifsp.ads.contatospdm.model.Contact
import kotlin.random.Random

class ContactActivity : AppCompatActivity() {

    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = "Contact details"

        val receivedContact = intent.getParcelableExtra<Contact>(EXTRA_CONTACT)
        receivedContact?.let {_receivedContact ->
            with(acb){
                nameEt.setText(_receivedContact.name)
                addressEt.setText(_receivedContact.adress)
                phoneEt.setText(_receivedContact.phone)
                emailEt.setText(_receivedContact.email)
            }
            val viewContact = intent.getBooleanExtra(VIEW_CONTACT, false)
            if (viewContact){
                with(acb){
                    nameEt.isEnabled = false
                    addressEt.isEnabled = false
                    phoneEt.isEnabled = false
                    emailEt.isEnabled = false
                    saveBt.visibility= View.GONE
                }
            }

        }

        with(acb){
            saveBt.setOnClickListener {
                val contact: Contact = Contact(
                    id = receivedContact?.id?: INVALID_CONTACT_ID,
                    nameEt.text.toString(),
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

    //private fun generateId() = Random(System.currentTimeMillis()).nextInt()
}