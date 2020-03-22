package br.com.arthursales.pokemonworld.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.arthursales.pokemonworld.util.DBPokemonWorld
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_ABILITY_1
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_ABILITY_2
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_ABILITY_3
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_SPRITE_FRONT_DEFAULT
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_ATTACK
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_DEFENSE
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_HP
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPATTACK
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPDEFENSE
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPEED
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_TYPE_1
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_TYPE_2
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_USER_ID
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.DATABASE_NAME
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.DATABASE_VERSION
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.TABLE_POKEMON
import br.com.arthursales.pokemonworld.util.DBUsers
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_EMAIL
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_FIRST_NAME
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_LAST_NAME
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_PASSWORD
import br.com.arthursales.pokemonworld.util.DBUsers.COLUMN_PHOTO_URL
import br.com.arthursales.pokemonworld.util.DBUsers.TABLE_USER
import br.com.arthursales.pokemonworld.util.SQL.SQL_NOT_NULL
import br.com.arthursales.pokemonworld.util.SQL.SQL_PRIMARY_KEY
import br.com.arthursales.pokemonworld.util.SQL.SQL_TYPE_BLOB
import br.com.arthursales.pokemonworld.util.SQL.SQL_TYPE_INTEGER
import br.com.arthursales.pokemonworld.util.SQL.SQL_TYPE_TEXT

class PokemonSqlHelper (context : Context):
    SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    override fun onCreate(sqliteDatabase: SQLiteDatabase) {
        sqliteDatabase.execSQL(SQL_CREATE_ENTRIES)
        sqliteDatabase.execSQL(SQL_CREATE_ENTRIES_USER)
    }

    override fun onUpgrade(sqliteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {
        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_POKEMON (" +
                    "${DBPokemonWorld.COLUMN_ID} $SQL_TYPE_INTEGER  $SQL_PRIMARY_KEY, " +
                    "$COLUMN_USER_ID $SQL_TYPE_INTEGER $SQL_NOT_NULL, " +
                    "${DBPokemonWorld.COLUMN_NAME} $SQL_TYPE_TEXT $SQL_NOT_NULL, " +
                    "$COLUMN_TYPE_1 $SQL_TYPE_TEXT $SQL_NOT_NULL, " +
                    "$COLUMN_TYPE_2 $SQL_TYPE_TEXT," +
                    "$COLUMN_STATS_HP $SQL_TYPE_INTEGER," +
                    "$COLUMN_STATS_ATTACK $SQL_TYPE_INTEGER, " +
                    "$COLUMN_STATS_DEFENSE $SQL_TYPE_INTEGER, " +
                    "$COLUMN_STATS_SPATTACK $SQL_TYPE_INTEGER, " +
                    "$COLUMN_STATS_SPDEFENSE $SQL_TYPE_INTEGER, " +
                    "$COLUMN_STATS_SPEED $SQL_TYPE_INTEGER, " +
                    "$COLUMN_SPRITE_FRONT_DEFAULT $SQL_TYPE_BLOB, " +
                    "$COLUMN_ABILITY_1 $SQL_TYPE_TEXT, " +
                    "$COLUMN_ABILITY_2 $SQL_TYPE_TEXT, " +
                    "$COLUMN_ABILITY_3 $SQL_TYPE_TEXT)"

        const val SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE $TABLE_USER (" +
                    "${DBUsers.COLUMN_ID} $SQL_TYPE_INTEGER $SQL_PRIMARY_KEY, " +
                    "$COLUMN_EMAIL $SQL_TYPE_TEXT $SQL_NOT_NULL, " +
                    "$COLUMN_PASSWORD $SQL_TYPE_TEXT $SQL_NOT_NULL, " +
                    "$COLUMN_FIRST_NAME $SQL_TYPE_TEXT $SQL_NOT_NULL, " +
                    "$COLUMN_LAST_NAME $SQL_TYPE_TEXT," +
                    "$COLUMN_PHOTO_URL $SQL_TYPE_INTEGER)"
    }
}