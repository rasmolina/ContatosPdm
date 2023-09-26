package br.edu.scl.ifsp.ads.contatospdm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import br.edu.scl.ifsp.ads.contatospdm.R
import br.edu.scl.ifsp.ads.contatospdm.databinding.ActivityMainBinding
import br.edu.scl.ifsp.ads.contatospdm.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.ads.contatospdm.model.Contact

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Lista mutável análogo array Java - Data Source
    private val contactList: MutableList<Contact> = mutableListOf()

    //Adapter
    private val contactAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList.map { contact -> contact.name })
    }

    private lateinit var carl:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root) //A tela vai ser renderizada pela view raiz

        setSupportActionBar(amb.toolbarIn.toolbar)

        amb.contatosLv.adapter = contactAdapter

        //instancia o carl
        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
         result -> if(result.resultCode == RESULT_OK) {
             val contact = result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
            contact?.let{ _contact ->
                contactList.add(_contact)
                contactAdapter.add(_contact.name)
                contactAdapter.notifyDataSetChanged()
            }
         }
        }
    }

    //Cria o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.addContactMi -> {
                //Abrir a tela para adicionar um novo contato (ContactActivity)
                carl.launch(Intent(this,ContactActivity::class.java))
                true
            } else -> false
        }
    }

   /* private fun fillContacts(){
        for(i in 1..50){
            contactList.add(
                Contact(i,"Nome $i","Endereço $i", "Telefone $i","Email $i")
            )
        }

    } */


}