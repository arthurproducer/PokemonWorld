package br.com.arthursales.pokemonworld.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_ABILITY_1
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_ABILITY_2
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_ABILITY_3
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_SPRITE_FRONT_DEFAULT
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_STATS_ATTACK
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_STATS_DEFENSE
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_STATS_HP
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_STATS_SPATTACK
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_STATS_SPDEFENSE
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_STATS_SPEED
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_TYPE_1
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_TYPE_2
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.DATABASE_NAME
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.DATABASE_VERSION
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.TABLE_POKEMON
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_EMAIL
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_FIRST_NAME
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_LAST_NAME
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_PASSWORD
import br.com.arthursales.pokemonworld.sqlite.DBUsers.COLUMN_PHOTO_URL
import br.com.arthursales.pokemonworld.sqlite.DBUsers.TABLE_USER

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
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_POKEMON (" +
                    "${DBPokemonWorld.COLUMN_ID} INTEGER  PRIMARY KEY, " +
                    "${DBPokemonWorld.COLUMN_NAME} TEXT NOT NULL, " +
                    "$COLUMN_TYPE_1 TEXT NOT NULL, " +
                    "$COLUMN_TYPE_2 TEXT," +
                    "$COLUMN_STATS_HP INTEGER," +
                    "$COLUMN_STATS_ATTACK INTEGER, " +
                    "$COLUMN_STATS_DEFENSE INTEGER, " +
                    "$COLUMN_STATS_SPATTACK INTEGER, " +
                    "$COLUMN_STATS_SPDEFENSE INTEGER, " +
                    "$COLUMN_STATS_SPEED INTEGER, " +
                    "$COLUMN_SPRITE_FRONT_DEFAULT BLOB, " +
                    "$COLUMN_ABILITY_1 TEXT, " +
                    "$COLUMN_ABILITY_2 TEXT, " +
                    "$COLUMN_ABILITY_3 TEXT)"

        val SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE $TABLE_USER (" +
                    "${DBUsers.COLUMN_ID} INTEGER  PRIMARY KEY, " +
                    "$COLUMN_EMAIL TEXT NOT NULL, " +
                    "$COLUMN_PASSWORD TEXT NOT NULL, " +
                    "$COLUMN_FIRST_NAME TEXT NOT NULL, " +
                    "$COLUMN_LAST_NAME TEXT," +
                    "$COLUMN_PHOTO_URL INTEGER)"
    }
}