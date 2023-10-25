package br.edu.scl.ifsp.ads.contatospdm.model

import androidx.room.Database
import androidx.room.RoomDatabase

//Equivalente a ContactSqlite
@Database(entities = [Contact::class], version = 1)
//Devolve uma instância de Room DAO que é gerada por um factory
abstract class ContactRoomDaoDatabase : RoomDatabase() {
    abstract fun getContactRoomDao(): ContactRoomDao

}