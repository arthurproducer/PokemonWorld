package br.com.arthursales.pokemonworld.util

object SQL{
    const val SQL_WHERE = "WHERE"
    const val SQL_LIKE = "LIKE"
    const val SQL_AND = "AND"
    const val SQL_TYPE_INTEGER = "INTEGER"
    const val SQL_TYPE_TEXT = "TEXT"
    const val SQL_TYPE_BLOB = "BLOB"
    const val SQL_NOT_NULL = "NOT NULL"
    const val SQL_PRIMARY_KEY = "PRIMARY KEY"
}

object DBPokemonWorld {
    const val DATABASE_NAME = "dbPokemonWorld"
    const val DATABASE_VERSION = 1
    const val TABLE_POKEMON = "pokemondetails"
    const val COLUMN_ID = "id"
    const val COLUMN_USER_ID = "user"
    const val COLUMN_NAME = "name"
    const val COLUMN_TYPE_1 = "type1"
    const val COLUMN_TYPE_2 = "type2"
    const val COLUMN_SPRITE_FRONT_DEFAULT = "sprite"
    const val COLUMN_STATS_HP = "hp"
    const val COLUMN_STATS_ATTACK = "attack"
    const val COLUMN_STATS_DEFENSE = "defense"
    const val COLUMN_STATS_SPATTACK = "spattack"
    const val COLUMN_STATS_SPDEFENSE = "spdefense"
    const val COLUMN_STATS_SPEED = "speed"
    const val COLUMN_ABILITY_1 = "ability1"
    const val COLUMN_ABILITY_2 = "ability2"
    const val COLUMN_ABILITY_3 = "ability3"
}

object DBUsers{
    const val TABLE_USER = "users"
    const val COLUMN_ID = "id"
    const val COLUMN_EMAIL = "email"
    const val COLUMN_PASSWORD = "password"
    const val COLUMN_FIRST_NAME = "firstname"
    const val COLUMN_LAST_NAME = "lastname"
    const val COLUMN_PHOTO_URL = "photo"
}
