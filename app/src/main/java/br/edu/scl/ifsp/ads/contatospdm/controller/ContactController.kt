package br.edu.scl.ifsp.ads.contatospdm.controller

import br.edu.scl.ifsp.ads.contatospdm.model.Contact
import br.edu.scl.ifsp.ads.contatospdm.model.ContactDao
import br.edu.scl.ifsp.ads.contatospdm.model.ContactDaoSlite
import br.edu.scl.ifsp.ads.contatospdm.view.MainActivity

class ContactController(mainActivity: MainActivity) {
    private val contactDaoImpl : ContactDao = ContactDaoSlite(mainActivity)

    fun insertContact(contact: Contact) = contactDaoImpl.createContact(contact)
    fun getContact(id: Int) = contactDaoImpl.retrieveContact(id)
    fun getContacts() = contactDaoImpl.retrieveContacts()
    fun editContact(contact: Contact) = contactDaoImpl.updateContact(contact)
    fun removeContact(id: Int) = contactDaoImpl.deleteContact(id)
}