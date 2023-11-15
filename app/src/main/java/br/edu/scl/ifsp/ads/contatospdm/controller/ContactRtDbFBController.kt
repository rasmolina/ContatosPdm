package br.edu.scl.ifsp.ads.contatospdm.controller

import br.edu.scl.ifsp.ads.contatospdm.model.Constant
import br.edu.scl.ifsp.ads.contatospdm.model.Contact
import br.edu.scl.ifsp.ads.contatospdm.model.ContactDao
import br.edu.scl.ifsp.ads.contatospdm.model.ContactDaoRtDbFb
import br.edu.scl.ifsp.ads.contatospdm.view.MainActivity

class ContactRtDbFBController (private val mainActivity: MainActivity) {
    private val contactDaoImpl: ContactDao = ContactDaoRtDbFb()

    fun insertContact(contact: Contact) {
        Thread {
            contactDaoImpl.createContact(contact)
        }.start()
    }

    fun getContact(id: Int) = contactDaoImpl.retrieveContact(id)

    fun getContacts() {
        Thread{
            mainActivity.updateContactListHandler.apply {
                sendMessage(
                    obtainMessage().apply {
                        data.putParcelableArray(
                            Constant.CONTACT_ARRAY,
                            contactDaoImpl.retrieveContacts().toTypedArray()
                        )
                    }
                )
            }
        }.start()
    }


    fun editContact(contact: Contact){
        Thread {
            contactDaoImpl.updateContact(contact)
            getContacts()
        }.start()
    }

    fun removeContact(contact: Contact){
        Thread {
            contactDaoImpl.deleteContact(contact.id!!)
            getContacts()
        }.start()
    }
}