package br.com.arthursales.pokemonworld.view.teams

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.view.details.DetailPokemonActivity
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonAdapter
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_cardview_teams.*
import kotlinx.android.synthetic.main.include_loading.*
import kotlinx.android.synthetic.main.teams_pokemon_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TeamsPokemonFragment : Fragment() {


    private val listFavoritePokemonViewModel : ListFavoritePokemonViewModel by viewModel()

    private val teamsPokemonViewModel : TeamsPokemonViewModel by viewModel()

    private val picasso: Picasso by inject()

    companion object {
        fun newInstance() = TeamsPokemonFragment()
    }

    private lateinit var viewModel: TeamsPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.teams_pokemon_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFavoritePokemonViewModel.showSQLLite(requireContext())
        rvTeamPokemon.adapter?.notifyDataSetChanged()

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

        teamsPokemonViewModel.lendingProducts.observe(this, Observer {
            rvPokemonTeams.adapter = TeamsPokemonAdapter(it, picasso)
//            {
//               if(it.situation == 1){
//                    Toast.makeText(context,"Item emprestado não pode ser alterado!",Toast.LENGTH_LONG).show()
//                }else{
//                    val action = MyProductFragmentDirections.actionItemMyProductsToUpdateMyProductFragment(it)
//                    view.findNavController().navigate(action)
//                }
//            }
            rvPokemonTeams.layoutManager = LinearLayoutManager(context)
        })

        listFavoritePokemonViewModel.listFavoritePokemon.observe(this, Observer {
            rvTeamPokemon.adapter = ListFavoritePokemonAdapter(it.toList(), picasso) {
                val intent = Intent(activity, DetailPokemonActivity::class.java)
                intent.putExtra("POKEMON", it?.id.toString())
                intent.putExtra("ACTIVITY", "Favorite")
                startActivity(intent)
            }
            rvTeamPokemon.layoutManager = GridLayoutManager(context, 3)
        })
         //TODO Tratar chamada do que está em favoritos
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamsPokemonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
