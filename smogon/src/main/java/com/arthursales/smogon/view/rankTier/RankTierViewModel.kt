package com.arthursales.smogon.view.rankTier

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthursales.smogon.models.SmogonResponse
import com.arthursales.smogon.repository.SmogonRepository
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentLinkedQueue


class RankTierViewModel(private val smogonRepository: SmogonRepository) : ViewModel() {

    private val messageError = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val smogonData = MutableLiveData<MutableList<SmogonResponse?>>()
    private val smogonOUList = ConcurrentLinkedQueue<SmogonResponse?>()

    fun getAllOUGen4PokeData() {
        //TODO fazer chamadas com todos os nomes do array
        //TODO colocar um for para que passe na chamada até que todos os nomes da lista do array tenham sido verificados
        isLoading.value = true
        viewModelScope.launch {
            val jobs: List<Job> = getAllOUGen4Poke().map { poke ->
                viewModelScope.launch  {
                    Log.i("POKEMON_FOR_GETSMOGON","${this.coroutineContext} + ${Thread.currentThread().name} + ${poke.pokemon}")
                    getSmogonData(poke.rank,poke.pokemon)
                    delay(6000L)
                }
            }
            jobs.joinAll()
            Log.i("POKEMON_AFTER_JOINALL","$smogonOUList")
            smogonData.value = smogonOUList.toMutableList()
            isLoading.value = false
        }

//            val job = viewModelScope.launch {
//                getAllOUGen4Poke().forEachIndexed { index, poke ->
//                    isLoading.value = true
//                    Log.i("POKEMON_FOR_GETSMOGON","$index + ${poke.pokemon}")
//                    val res = async {getSmogonData(index,poke.pokemon)}
//                    val list = res.await()
//                    Log.i("POKEMON_LIST_ASYNC","$list")
//
//                    withContext(Dispatchers.Main){
//                        list.let {
//                            smogonOUList.value?.add(it)
//                            Log.i("POKEMON_SAVE_SmogonMAIN", "$it")
//                        }
//                    }
//                }
//            }

        }

    fun getSmogonData(id: Int, pokeName : String) {
        smogonRepository.getSmogonData(
             pokeName,
            onComplete = {
                smogonOUList.add(it)
                Log.i("POKEMON_JOB", "${Thread.currentThread().name}+ $id + $it")
                Log.i("POKEMON_COMPLETE_SMOGON","$smogonOUList")
            },
            onError = {
                    messageError.value = it.message
                Log.i("POKEMON_ERROR_SMOGON", "${it.message}")
            }
        )
    }

    private fun getAllOUGen4Poke() : ArrayList<SmogonResponse>{
        val listOfOverusedPokeGen4 = arrayListOf<SmogonResponse>()
        listOfOverusedPokeGen4.add(SmogonResponse("","Tyranitar",0,""))
        listOfOverusedPokeGen4.add(SmogonResponse("","Jirachi",1,""))
        listOfOverusedPokeGen4.add(SmogonResponse("","Heatran",2,""))
        return listOfOverusedPokeGen4
    }

}