package br.com.arthursales.pokemonworld.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PokemonSqlHelper (context : Context):
    SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    override fun onCreate(sqliteDatabase: SQLiteDatabase) {
        sqliteDatabase.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(sqliteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_POKEMON (" +
                    "$COLUMN_ID INTEGER  PRIMARY KEY, " +
                    "$COLUMN_NAME TEXT NOT NULL, " +
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
    }
}