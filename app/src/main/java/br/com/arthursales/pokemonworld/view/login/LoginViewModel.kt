package br.com.arthursales.pokemonworld.view.login

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.UserResponse
import br.com.arthursales.pokemonworld.repository.UserRepository
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_EMAIL
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_FIRST_NAME
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_ID
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_LAST_NAME
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_PASSWORD
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_PHOTO_URL
import br.com.arthursales.pokemonworld.util.DBUsers.TABLE_USER
import br.com.arthursales.pokemonworld.sqlite.PokemonSqlHelper
import br.com.arthursales.pokemonworld.util.SQL.SQL_AND
import br.com.arthursales.pokemonworld.util.SQL.SQL_LIKE
import br.com.arthursales.pokemonworld.util.SQL.SQL_WHERE

class LoginViewModel (private val userRepository: UserRepository) : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val users = MutableLiveData<List<UserResponse>>()
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
            sql += " $SQL_WHERE $COLUMN_EMAIL $SQL_LIKE ? "
            args = arrayOf(email)

        }else{
            sql += " $SQL_WHERE $COLUMN_EMAIL $SQL_LIKE ? " +
                    "$SQL_AND $COLUMN_PASSWORD $SQL_LIKE ?"
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
}