package br.edu.scl.ifsp.ads.contatospdm.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.contatospdm.R
import br.edu.scl.ifsp.ads.contatospdm.databinding.TileContactBinding
import br.edu.scl.ifsp.ads.contatospdm.model.Contact

class ContactAdapter(context: Context,
                     private val contactList: MutableList<Contact>
):ArrayAdapter<Contact>(context, R.layout.tile_contact,contactList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //Primeiro passo: pegar o contato
        val contact = contactList[position]
        var tcb: TileContactBinding? = null

        var contactTileView = convertView
        //se for nula, infla a célula
        if(contactTileView == null){
            tcb = TileContactBinding.inflate(
            context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            parent,
            false
            )
            contactTileView = tcb.root

            val tileContactHolder = TileContactHolder(tcb.nameTv,tcb.emailTv)
            contactTileView.tag = tileContactHolder
        }

        (contactTileView.tag as TileContactHolder).nameTv.setText(contact.name)
        (contactTileView.tag as TileContactHolder).emailTv.setText(contact.emails)

        //preenche com os dados na célula
        //!! força a chamada porque não precisa verificar se é nulo
        tcb?.nameTv?.setText(contact.name)
        tcb?.emailTv?.setText(contact.email)
        //contactTileView!!.findViewById<TextView>(R.id.nameTv).setText(contact.name)
        //contactTileView!!.findViewById<TextView>(R.id.emailTv).setText(contact.email)

        //retorna a célula inflada com os dados
        return contactTileView
    }

    private data class TileContactHolder(val nameTv:TextView, val emailTv:TextView){

    }

}