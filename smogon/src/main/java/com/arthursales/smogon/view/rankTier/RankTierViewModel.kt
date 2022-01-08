package com.arthursales.smogon.view.rankTier

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthursales.smogon.models.SmogonRankPokemonResponse
import com.arthursales.smogon.models.SmogonResponse
import com.arthursales.smogon.repository.SmogonDetailsRepository
import com.arthursales.smogon.repository.SmogonRankRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.time.Year
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.collections.ArrayList


class RankTierViewModel(
    private val smogonDetailsRepository: SmogonDetailsRepository,
    private val smogonRankRepository: SmogonRankRepository
) : ViewModel() {

    private val messageError = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val smogonData = MutableLiveData<MutableList<SmogonResponse?>>()
    private val smogonOUList = ConcurrentLinkedQueue<SmogonResponse?>()


    fun getAllOUGen4PokeData() {
        isLoading.value = true
        viewModelScope.launch {
            val jobs: List<Job> = getAllOUGen4Poke().map { poke ->
                viewModelScope.launch {
                    getSmogonData(poke.pokemon)
                    delay(8000L)
                }
            }
            jobs.joinAll()
            smogonData.value = smogonOUList.sortedBy {
                it?.rank
            }.toMutableList()
            isLoading.value = false
        }
    }

    fun getTop10RankPokeData() {
        isLoading.value = true
        Log.d("pokemontagloading", isLoading.value.toString())
        viewModelScope.launch {
            val job = viewModelScope.launch {
                Log.d("pokemontagscope", smogonData.value.toString())
                getSmogonRankData()
                delay(8000L)
            }
            job.join()
            smogonData.value = smogonOUList.toMutableList()
            isLoading.value = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllOUGen4PokeDataByMonthAndYear() {
        isLoading.value = true
        viewModelScope.launch {
            val jobs: List<Job> = getAllOUGen4Poke().map { poke ->
                viewModelScope.launch {
                    getSmogonDataByMonthAndYear(poke.pokemon,Calendar.getInstance().get(Calendar.MONTH),Year.now().value)
                    delay(8000L)
                }
            }
            jobs.joinAll()
            smogonData.value = smogonOUList.sortedBy {
                it?.rank
            }.toMutableList()
            isLoading.value = false
        }
    }

    private fun getSmogonData(pokeName: String) {
        smogonDetailsRepository.getSmogonData(
            pokeName,
            onComplete = {
                smogonOUList.add(it)
            },
            onError = {
                messageError.value = it.message
            }
        )
    }

    private fun getSmogonDataByMonthAndYear(pokeName: String, month: Int, year: Int) {
        smogonDetailsRepository.getSmogonDataByMonthAndYear(
            pokeName,
            month,
            year,
            onComplete = {
                smogonOUList.add(it)
            },
            onError = {
                messageError.value = it.message
            }
        )
    }

    fun  getSmogonRankData() {
        smogonRankRepository.getSmogonRankData(
            onComplete = { smogonData ->
                Log.i("pokemontag", smogonData.toString())
                smogonData.let { pokemon ->
                        smogonOUList.addAll(pokemon)
                }
            },
            onError = {
                messageError.value = it.localizedMessage
            }
        )
    }


    private fun getAllOUGen4Poke(): ArrayList<SmogonResponse> {
        val listOfOverusedPokeGen4 = arrayListOf<SmogonResponse>()
        listOfOverusedPokeGen4.add(SmogonResponse("", "Tyranitar", 0, "",183))
        listOfOverusedPokeGen4.add(SmogonResponse("", "Jirachi", 1, "",203))
        listOfOverusedPokeGen4.add(SmogonResponse("", "Heatran", 2, "",123))
        return listOfOverusedPokeGen4
    }

}