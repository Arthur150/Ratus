package fr.univ.lyon1.lpiem.ratus.ui

import androidx.lifecycle.*
import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.domain.GetFundUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetTricksUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserFundsUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.model.Trick
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val useCase: GetTricksUseCase
) : ViewModel() {

    /*
    // GetUserUseCase
    var user : MutableLiveData<User?> = MutableLiveData()

    fun getUser(uid : String) {
        viewModelScope.launch {
            user.postValue(useCase(uid))
        }
    }
    */

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

    var tricks : MutableLiveData<List<Trick>?> = MutableLiveData()

    fun getTricks() {
        viewModelScope.launch {
            tricks.postValue(useCase())
        }
    }

}