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
import br.com.arthursales.pokemonworld.view.details.DetailPokemonActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_list_pokemon.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class ListPokemonFragment : Fragment() {

    private val listPokemonViewModel: ListPokemonViewModel by viewModel()

    private val picasso: Picasso by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeListPokemonObserving()

        listPokemonViewModel.isLoading.observe(this, Observer {
            if (it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }
        })

        listPokemonViewModel.messageError.observe(this, Observer {
            if (it != "") {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        listPokemonViewModel.next.observe(this, Observer {
            btNext.isEnabled = !it.isNullOrEmpty()
        })

        listPokemonViewModel.previous.observe(this, Observer {
            btPrevious.isEnabled = !it.isNullOrEmpty()
        })

        listPokemonViewModel.listPokemon.observe(this, Observer {
            rvPokemons.adapter = ListPokemonAdapter(it, picasso) { pokemon ->
                val intent = Intent(activity, DetailPokemonActivity::class.java)
                intent.putExtra(
                    "POKEMON",
                    pokemon.url.substringAfter("pokemon/").substringBefore("/")
                )
                intent.putExtra("SCREEN", "GENERAL_LIST")
                Log.i("POKEMON", pokemon.url.substringAfter("pokemon/").substringBefore("/"))
                startActivity(intent)
            }
            rvPokemons.layoutManager = GridLayoutManager(context, 3)
        })

        btNext.setOnClickListener {
            val limit = listPokemonViewModel.next.value?.substringAfter("limit=")
            val offset = listPokemonViewModel.next.value?.substringAfter("offset=")
                ?.substringBefore('&')
            listPokemonViewModel.getAllPokemon(offset?.toInt(), limit?.toInt())
        }

        btPrevious.setOnClickListener {
            val limit = listPokemonViewModel.previous.value?.substringAfter("limit=")
            val offset = listPokemonViewModel.previous.value?.substringAfter("offset=")
                ?.substringBefore('&')

            listPokemonViewModel.getAllPokemon(offset?.toInt(), limit?.toInt())
            //TODO Tratar retorno com botão anterior no final da lista, quando limit é menor do que 100
        }
    }

    private fun initializeListPokemonObserving() {
        if (listPokemonViewModel.previous.value.isNullOrEmpty()) {
            listPokemonViewModel.getAllPokemon(OFFSET_100_POKEMON, LIMIT_100_POKEMON)
        }
    }

    companion object {
        const val OFFSET_100_POKEMON = 0
        const val LIMIT_100_POKEMON = 100
    }

}
