package br.com.arthursales.pokemonworld.view.listFavoritePokemon

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.sqlite.*
import br.com.arthursales.pokemonworld.view.details.DetailPokemonActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_favorite_pokemon.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class ListFavoritePokemonActivity : AppCompatActivity() {

    val listFavoritePokemonViewModel : ListFavoritePokemonViewModel by viewModel()

    val picasso: Picasso by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_favorite_pokemon)

        listFavoritePokemonViewModel.showSQLLite(this)
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

    private fun pokemonFromCursor(cursor: Cursor){
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            val sprite = cursor.getString(cursor.getColumnIndex(COLUMN_SPRITE_FRONT_DEFAULT))
        }
    }
}

