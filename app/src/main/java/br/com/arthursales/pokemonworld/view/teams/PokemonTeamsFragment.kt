package br.com.arthursales.pokemonworld.view.teams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.arthursales.pokemonworld.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.include_loading.*
import kotlinx.android.synthetic.main.pokemon_teams_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonTeamsFragment : Fragment() {

    private val pokemonTeamsViewModel : PokemonTeamsViewModel by viewModel()

    private val picasso: Picasso by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokemon_teams_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonTeamsViewModel.getPokemonTeams()

//        rvTeamPokemon.adapter?.notifyDataSetChanged()

//        pokemonTeamsViewModel.isLoadingTeams.observe(this, Observer {
//            if(it == true) {
//                containerLoading.visibility = View.VISIBLE
//            } else {
//                containerLoading.visibility = View.GONE
//            }
//        })

//        listFavoritePokemonViewModel.messageError.observe(this, Observer {
//            if(it != "") {
//                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//            }
//        })

        pokemonTeamsViewModel.listTeams.observe(this, Observer {
            rvPokemonTeams.adapter = PokemonTeamsAdapter(it, picasso)
            rvPokemonTeams.layoutManager = LinearLayoutManager(context)
        })
    }

}
