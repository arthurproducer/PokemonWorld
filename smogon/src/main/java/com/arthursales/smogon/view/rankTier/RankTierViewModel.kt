package com.arthursales.smogon.view.rankTier

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthursales.smogon.models.SmogonResponse
import com.arthursales.smogon.repository.SmogonRepository
import kotlinx.coroutines.*
import java.time.Year
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.collections.ArrayList


class RankTierViewModel(private val smogonRepository: SmogonRepository) : ViewModel() {

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
        smogonRepository.getSmogonData(
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
        smogonRepository.getSmogonDataByMonthAndYear(
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

    private fun getAllOUGen4Poke(): ArrayList<SmogonResponse> {
        val listOfOverusedPokeGen4 = arrayListOf<SmogonResponse>()
        listOfOverusedPokeGen4.add(SmogonResponse("", "Tyranitar", 0, ""))
        listOfOverusedPokeGen4.add(SmogonResponse("", "Jirachi", 1, ""))
        listOfOverusedPokeGen4.add(SmogonResponse("", "Heatran", 2, ""))
        return listOfOverusedPokeGen4
    }

}