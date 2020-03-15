package br.com.arthursales.pokemonworld.sqlite

object DBPokemonWorld {
    const val DATABASE_NAME = "dbPokemonWorld"
    const val DATABASE_VERSION = 1
    const val TABLE_POKEMON = "pokemondetails"
    const val COLUMN_ID = "id"
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
