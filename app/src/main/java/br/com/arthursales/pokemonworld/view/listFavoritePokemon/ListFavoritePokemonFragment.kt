package br.com.arthursales.pokemonworld.view.listFavoritePokemon

import android.content.Intent
import android.content.SharedPreferences
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
import kotlinx.android.synthetic.main.fragment_list_favorite_pokemon.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ListFavoritePokemonFragment : Fragment() {

    private val listFavoritePokemonViewModel : ListFavoritePokemonViewModel by viewModel()

    private val preferences : SharedPreferences by inject()

    private val picasso: Picasso by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_favorite_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFavoritePokemonViewModel.showSQLLite(preferences.getLong("User_ID",0),requireContext())
        rvFavoritePokemons.adapter?.notifyDataSetChanged()
        //pokemonFromCursor(cursor!!)

        listFavoritePokemonViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }
        })

        listFavoritePokemonViewModel.messageError.observe(this, Observer {
            if(it != "") {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        listFavoritePokemonViewModel.listFavoritePokemon.observe(this, Observer {
            Log.i("FAVORITEPOKEACTIVITY",it.toString())
            rvFavoritePokemons.adapter = ListFavoritePokemonAdapter(it.toList(), picasso) {
                val intent = Intent(activity, DetailPokemonActivity::class.java)
                intent.putExtra("POKEMON", it?.id.toString())
                intent.putExtra("ACTIVITY", "Favorite")
                startActivity(intent)
//                finish()
            }
            rvFavoritePokemons.layoutManager = GridLayoutManager(context, 3)
        })

    }
}
