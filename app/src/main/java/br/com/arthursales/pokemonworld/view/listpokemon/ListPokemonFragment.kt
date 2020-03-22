package br.com.arthursales.pokemonworld.view.listpokemon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.util.Keys.GENERAL_LIST_KEY
import br.com.arthursales.pokemonworld.util.Keys.POKEMON_KEY
import br.com.arthursales.pokemonworld.util.Keys.SCREEN_KEY
import br.com.arthursales.pokemonworld.view.details.DetailPokemonActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_list_pokemon.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class ListPokemonFragment : Fragment() {

    private val listPokemonViewModel : ListPokemonsViewModel by viewModel()
    private val picasso: Picasso by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listPokemonViewModel.getPokemons()

        listPokemonViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }

        })

        listPokemonViewModel.messageError.observe(this, Observer {
            if(it != "") {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        listPokemonViewModel.pokemons.observe(this, Observer {
            rvPokemons.adapter = ListPokemonsAdapter(it, picasso) {
                val intent = Intent(activity, DetailPokemonActivity::class.java)
                intent.putExtra(POKEMON_KEY, it.url.substringAfter("pokemon/").substringBefore("/").toInt())
                intent.putExtra(SCREEN_KEY, GENERAL_LIST_KEY)
                startActivity(intent)
//                finish()
            }
            rvPokemons.layoutManager = GridLayoutManager(context, 3)
        })

    }

}
