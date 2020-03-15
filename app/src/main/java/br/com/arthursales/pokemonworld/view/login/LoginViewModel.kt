package br.com.arthursales.pokemonworld.view.login

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonDetails
import br.com.arthursales.pokemonworld.model.UserResponse
import br.com.arthursales.pokemonworld.repository.UserRepository
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_USER_ID
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.TABLE_POKEMON
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_EMAIL
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_FIRST_NAME
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_ID
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_LAST_NAME
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_PASSWORD
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_PHOTO_URL
import br.com.arthursales.pokemonworld.sqlite.DBUsers.TABLE_USER
import br.com.arthursales.pokemonworld.sqlite.PokemonSqlHelper

class LoginViewModel (val userRepository: UserRepository) : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val users = MutableLiveData<List<UserResponse>>()
    val user = MutableLiveData<UserResponse>()
    val messageError = MutableLiveData<String>()

    fun getAllUser() {
        isLoading.value = true
        userRepository.getAllUser(
            onComplete = {
                isLoading.value = false
                users.value = it
                messageError.value = ""
            },
            onError = {
                isLoading.value = false
                users.value = listOf()
                messageError.value = it.message
            }
        )
    }

    fun insertSQLLite(user: UserResponse,password: String,context: Context){
        val helper = PokemonSqlHelper(context)

        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_ID,user.id)
            put(COLUMN_EMAIL,user.email)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_FIRST_NAME,user.first_name)
            put(COLUMN_LAST_NAME,user.last_name)
            put(COLUMN_PHOTO_URL,user.avatar)

        }
        db.insert(TABLE_USER,null,cv)
        db.close()
    }

    fun validateLogin(email: String,pass: String?, context: Context): Boolean {
        val helper = PokemonSqlHelper(context)
        val args : Array<String>?
        var sql = "SELECT * FROM $TABLE_USER"
        if(pass.isNullOrEmpty()){
            sql += " WHERE $COLUMN_EMAIL LIKE ? "
            args = arrayOf(email)

        }else{
            sql += " WHERE $COLUMN_EMAIL LIKE ? " +
                    "AND $COLUMN_PASSWORD LIKE ?"
            args = arrayOf(email,pass)
        }

        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql,args)

        if(cursor.count == 1) {
            cursor.close()
            db.close()
            return true
        }
        cursor.close()
        db.close()
         return false
    }

//    fun searchByUserID(id: String, context: Context): ArrayList<PokemonDetails> {
//        val helper = PokemonSqlHelper(context)
//        val args : Array<String>?
//        var sql = "SELECT * FROM $TABLE_POKEMON"
//            sql += " WHERE $COLUMN_USER_ID LIKE ? "
//            args = arrayOf(id)
//
//        val db = helper.readableDatabase
//        val cursor = db.rawQuery(sql,args)
//        val pokemons = ArrayList<PokemonDetails>()
//        while(cursor.moveToNext()){
//            val pokemon = pokemonFromCursor(cursor)
//            pokemons.add(pokemon)
//        }
//        cursor.close()
//        db.close()
//        return pokemons
//    }
//
//    private fun pokemonFromCursor(cursor: Cursor) : PokemonDetails{
//
//        val id = cursor.getInt(
//            cursor.getColumnIndex(COLUMN_ID))
//        val name = cursor.getString(
//            cursor.getColumnIndex(COLUMN_NAME))
//        spritesResponse.front_default = cursor.getString(
//            cursor.getColumnIndex(COLUMN_SPRITE_FRONT_DEFAULT)
//        )
//
//        return PokemonDetails(id,name,spritesResponse)
//    }
}