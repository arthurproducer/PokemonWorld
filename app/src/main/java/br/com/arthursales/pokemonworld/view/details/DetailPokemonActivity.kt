package br.com.arthursales.pokemonworld.view.details

import android.content.Context
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_ID
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_NAME
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_SPRITE_FRONT_DEFAULT
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_ATTACK
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_DEFENSE
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_HP
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPATTACK
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPDEFENSE
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPEED
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_TYPE_1
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_TYPE_2
import br.com.arthursales.pokemonworld.util.Keys.ACTIVITY_KEY
import br.com.arthursales.pokemonworld.util.Keys.FAVORITE_KEY
import br.com.arthursales.pokemonworld.util.Keys.POKEMON_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_pokemon.*
import kotlinx.android.synthetic.main.include_loading.*
import kotlinx.android.synthetic.main.pokemon_list_item.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailPokemonActivity : AppCompatActivity() {

    private val detailPokemonViewModel: DetailPokemonViewModel by viewModel()
    private val picasso: Picasso by inject()
    private var pokemonId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_pokemon)
        setValues()

        detailPokemonViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }
        })

        detailPokemonViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

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

        btSaveFavorite.setOnClickListener {
            //TODO Colocar um radio Buttion
            //TODO Tratar a troca do ícone e a remoção dos favoritos

            detailPokemonViewModel.insertSQLLite(pokemonId,this)
            btSaveFavorite.setImageResource(R.drawable.ic_favorite)
        }
    }
    private fun setValues() {
        pokemonId = intent.getIntExtra(POKEMON_KEY,0)

        if(intent.getStringExtra(ACTIVITY_KEY) != null && intent.getStringExtra(ACTIVITY_KEY) == FAVORITE_KEY){
            detailPokemonViewModel.showSQLLite(pokemonId,this)

            btSaveFavorite.visibility = View.GONE
        } else{
            detailPokemonViewModel.getPokemonDetails(pokemonId.toString())
        }
    }
}
