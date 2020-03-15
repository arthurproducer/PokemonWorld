package br.com.arthursales.pokemonworld.view.listFavoritePokemon

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.sqlite.*
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_ID
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_NAME
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_SPRITE_FRONT_DEFAULT
import br.com.arthursales.pokemonworld.view.details.DetailPokemonActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_favorite_pokemon.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class ListFavoritePokemonActivity : AppCompatActivity() {

    val listFavoritePokemonViewModel : ListFavoritePokemonViewModel by viewModel()
    val preferences : SharedPreferences by inject()
    val picasso: Picasso by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_favorite_pokemon)

        listFavoritePokemonViewModel.showSQLLite(preferences.getLong("User_ID",0),this)
        rvFavoritePokemons.adapter?.notifyDataSetChanged()
        //pokemonFromCursor(cursor!!)
//
//        listFavoritePokemonViewModel.isLoading.observe(this, Observer {
//            if(it == true) {
//                containerLoading.visibility = View.VISIBLE
//            } else {
//                containerLoading.visibility = View.GONE
//            }
//
//        })

//        listPokemonViewModel.messageError.observe(this, Observer {
//            if(it != "") {
//                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//            }
//        })

        listFavoritePokemonViewModel.listFavoritePokemon.observe(this, Observer {
            Log.i("FAVORITEPOKEACTIVITY",it.toString())
            rvFavoritePokemons.adapter = ListFavoritePokemonAdapter(it.toList(), picasso) {
                val intent = Intent(this, DetailPokemonActivity::class.java)
                intent.putExtra("POKEMON", it?.id.toString())
                intent.putExtra("ACTIVITY", "Favorite")
                Log.i("FAVORITEPOKEDeta",it?.id.toString())
                startActivity(intent)
                finish()
            }
            rvFavoritePokemons.layoutManager = GridLayoutManager(this, 3)
        })

    }
}

