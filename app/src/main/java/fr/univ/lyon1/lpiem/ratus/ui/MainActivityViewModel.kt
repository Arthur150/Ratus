package fr.univ.lyon1.lpiem.ratus.ui

import android.util.Log
import androidx.lifecycle.*
import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.domain.*
import fr.univ.lyon1.lpiem.ratus.model.*
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val useCase: GetUserUseCase,
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "MainActivityViewModel"
    }


    // GetUserUseCase
    var user : MutableLiveData<User?> = MutableLiveData()

    fun getUser(uid : String) {
        viewModelScope.launch {
            user.postValue(useCase(uid))
        }
    }

    fun addTransaction(uid: String){
        viewModelScope.launch {
            user.postValue(addTransactionUseCase(
                uid,
                Transaction(title = "Creation depuis l'app")
            ))
        }
    }


    /*
    //GetUserFundsUseCase
    var funds : MutableLiveData<List<Fund>?> = MutableLiveData()

    fun getFunds(uid : String) {
        viewModelScope.launch {
            funds.postValue(useCase(uid))
        }
    }

    */

    /*
    //GetFundUseCase
    var fund : MutableLiveData<Fund?> = MutableLiveData()

    fun getFunds(id : String) {
        viewModelScope.launch {
            fund.postValue(useCase(id))
        }
    }

    */

    /*
    //GetTricksUseCase
    var tricks : MutableLiveData<List<Trick>?> = MutableLiveData()

    fun getTricks() {
        viewModelScope.launch {
            tricks.postValue(useCase())
        }
    }

     */

}