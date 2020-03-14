package br.com.arthursales.pokemonworld.view.details

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.Pokemon
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse
import br.com.arthursales.pokemonworld.sqlite.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_pokemon.*
import kotlinx.android.synthetic.main.pokemon_list_item.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailPokemonActivity : AppCompatActivity() {

    val detailPokemonViewModel: DetailPokemonViewModel by viewModel()
    val picasso: Picasso by inject()

    lateinit var pokemon : Pokemon //TODO OLD
    lateinit var pokemonId : String
    lateinit var pokemonType : PokemonGenericResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_pokemon)
        setValues()

        detailPokemonViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        btSaveFavorite.setOnClickListener {
            //TODO Colocar um radio Buttion
            //TODO Tratar a troca do ícone e a remoção dos favoritos

            detailPokemonViewModel.insertSQLLite(pokemonId,this)
            btSaveFavorite.setImageResource(R.drawable.ic_favorite)
        }
//        btSaveForm.setOnClickListener {
//            pokemon.attack = sbAttack.progress
//            pokemon.defense = sbDefense.progress
//            pokemon.velocity = sbVelocity.progress
//            pokemon.ps = sbPS.progress
//
//            detailPokemonViewModel.updatePokemon(pokemon)
//        }
    }

    private fun pokemonFromCursor(cursor: Cursor){
        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
        tvTitle.text = id.toString()
        val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
        tvPokemonNameForm.text = name
        val type1 = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_1))
        tvFirstType.text = type1
        val type2 = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_2))
        if(!type2.isNullOrEmpty()){
            tvSecondType.visibility = View.VISIBLE
            tvSecondType.text = type2
        }
        val hp = cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_HP))
        tvHPValue.text = hp.toString()
        sbHP.progress = hp
        val attack = cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_ATTACK))
        tvAttackValue.text = attack.toString()
        sbAttack.progress = attack
        val defense = cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_DEFENSE))
        tvDefenseValue.text = defense.toString()
        sbDefense.progress = defense
        val spattack = cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_SPATTACK))
        tvSPAttackValue.text = spattack.toString()
        sbSPAttack.progress = spattack
        val spdefense = cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_SPDEFENSE))
        tvSPDefenseValue.text = spdefense.toString()
        sbSPDefense.progress = spdefense
        val speed = cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_SPEED))
        tvSpeedValue.text = speed.toString()
        sbSpeed.progress = speed
        val sprite = cursor.getString(cursor.getColumnIndex(COLUMN_SPRITE_FRONT_DEFAULT))
        picasso.load(sprite).into(ivPokemon)
    }

    private fun setValues() {
        pokemonId = intent.getStringExtra("POKEMON")

        if(intent.getStringExtra("Activity") != null && intent.getStringExtra("ACTIVITY").equals("Favorite")){
            //TODO
            val cursor = detailPokemonViewModel.showSQLLite(pokemonId,this)
            while(cursor.moveToNext()) {
                pokemonFromCursor(cursor)
            }
            cursor.close()
            btSaveFavorite.visibility = View.GONE
        }else{
            detailPokemonViewModel.getPokemonDetails(pokemonId)
        }

        detailPokemonViewModel.pokemonDetails.observe(this, Observer { pokemon ->
            tvTitle.text = pokemon.id.toString() //TODO Colocar um formato #000
            tvPokemonNameForm.text = pokemon.name

            //Tratar os tipos
            detailPokemonViewModel.pokemonTypes.observe(this, Observer {
                it.forEach {typeResponse ->
                    when(typeResponse.slot){
                        1 ->
                            tvFirstType.text = typeResponse.type.name
                        2 -> {
                            tvSecondType.visibility = View.VISIBLE
                            tvSecondType.text = typeResponse.type.name
                        }
                    }
                } })

            picasso.load(pokemon.sprites.front_default).into(ivPokemonForm)

            //Tratar os Status
            detailPokemonViewModel.pokemonStats.observe(this, Observer {
                it.forEach {statsPokemon ->
                 when(statsPokemon.stat.name){
                     "hp" -> {
                         tvHPValue.text = statsPokemon.base_stat.toString()
                         sbHP.progress = statsPokemon.base_stat
                     }
                     "attack" -> {
                         tvAttackValue.text = statsPokemon.base_stat.toString()
                         sbAttack.progress = statsPokemon.base_stat
                     }
                     "defense" -> {
                         tvDefenseValue.text = statsPokemon.base_stat.toString()
                         sbDefense.progress = statsPokemon.base_stat
                     }
                     "special-attack" ->{
                         tvSPAttackValue.text = statsPokemon.base_stat.toString()
                         sbSPAttack.progress = statsPokemon.base_stat
                     }
                     "special-defense" -> {
                         tvSPDefenseValue.text = statsPokemon.base_stat.toString()
                         sbSPDefense.progress = statsPokemon.base_stat
                     }
                     "speed" -> {
                         tvSpeedValue.text = statsPokemon.base_stat.toString()
                         sbSpeed.progress = statsPokemon.base_stat
                     }
                 }
                } })
        })
    }
}
