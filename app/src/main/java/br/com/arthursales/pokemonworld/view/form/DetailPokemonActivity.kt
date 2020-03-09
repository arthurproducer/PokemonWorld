package br.com.arthursales.pokemonworld.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.Pokemon
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse
import br.com.arthursales.pokemonworld.model.TypesResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_pokemon.*
import kotlinx.android.synthetic.main.pokemon_list_item.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.reflect.Type

class DetailPokemonActivity : AppCompatActivity() {

    val detailPokemonViewModel: DetailPokemonViewModel by viewModel()
    val picasso: Picasso by inject()

    lateinit var pokemon : Pokemon
    lateinit var pokemonId : String
    lateinit var pokemonType : PokemonGenericResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_pokemon)
        setValues()

        detailPokemonViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

//        btSaveForm.setOnClickListener {
//            pokemon.attack = sbAttack.progress
//            pokemon.defense = sbDefense.progress
//            pokemon.velocity = sbVelocity.progress
//            pokemon.ps = sbPS.progress
//
//            detailPokemonViewModel.updatePokemon(pokemon)
//        }
    }

    private fun setValues() {
        pokemonId = intent.getStringExtra("POKEMON")

        detailPokemonViewModel.getPokemonDetails(pokemonId)

        detailPokemonViewModel.pokemonDetails.observe(this, Observer { pokemon ->
            tvTitle.text = pokemon.id //TODO Colocar um formato #000
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

            picasso.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png").into(ivPokemonForm)

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
//
//        tvPokemonNameForm.text = pokemon.name
//
//        picasso.load("https://pokedexdx.herokuapp.com${pokemon.imageURL}").into(ivPokemonForm)
//
//        sbAttack.progress = pokemon.attack
//        sbDefense.progress = pokemon.defense
//        sbPS.progress = pokemon.ps
//        sbVelocity.progress = pokemon.velocity
//
//        tvAttackValue.text = pokemon.attack.toString()
//        tvDefenseValue.text = pokemon.defense.toString()
//        tvPSValue.text = pokemon.ps.toString()
//        tvVelocityValue.text = pokemon.velocity.toString()
//
//        setListener(sbAttack, tvAttackValue)
//        setListener(sbDefense, tvDefenseValue)
//        setListener(sbVelocity, tvVelocityValue)
//        setListener(sbPS, tvPSValue)
    }

    private fun setListener(seekBar: SeekBar, textView: TextView) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
