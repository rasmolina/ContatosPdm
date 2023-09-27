package br.edu.scl.ifsp.ads.contatospdm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import br.edu.scl.ifsp.ads.contatospdm.R
import br.edu.scl.ifsp.ads.contatospdm.adapter.ContactAdapter
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
    /*
    private val contactAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList.map { contact -> contact.name})
    } */

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(
            this,
            contactList)
    }

    private lateinit var carl:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root) //A tela vai ser renderizada pela view raiz

        setSupportActionBar(amb.toolbarIn.toolbar)

        amb.contatosLv.adapter = contactAdapter
        fillContacts()
        //instancia o carl
        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
         result -> if(result.resultCode == RESULT_OK) {
             val contact = result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
            contact?.let{ _contact ->
                if(contactList.any {it.id == _contact.id}){
                    val position = contactList.indexOfFirst { it.id == _contact.id}
                    contactList[position] = _contact
                }else {
                    contactList.add(_contact)
                }
                contactAdapter.notifyDataSetChanged()
            }
         }
        }

        registerForContextMenu(amb.contatosLv)
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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterContextMenuInfo).position
        return when(item.itemId){
            R.id.removeContactMi -> {
                contactList.removeAt(position)
                contactAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Contact removed!", Toast.LENGTH_SHORT).show()
                true}
            R.id.editContactMi -> {
                val contact = contactList[position]
                val editContactIntent = Intent(this, ContactActivity::class.java)
                editContactIntent.putExtra(EXTRA_CONTACT,contact)
                carl.launch(editContactIntent)
                true}
            else -> {false}
        }
    }

   private fun fillContacts(){
        for(i in 1..50){
            contactList.add(
                Contact(i,"Nome $i","Endereço $i", "Telefone $i","Email $i")
            )
        }

    }


}