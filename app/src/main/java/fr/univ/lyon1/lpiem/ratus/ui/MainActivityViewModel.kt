package fr.univ.lyon1.lpiem.ratus.ui

import androidx.lifecycle.*
import fr.univ.lyon1.lpiem.ratus.data.datasource.UserRemoteDataSource
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val useCase: GetUserUseCase
) : ViewModel() {

    var user : MutableLiveData<User?> = MutableLiveData()

    fun getUser(uid : String) {
        viewModelScope.launch {
            user.postValue(useCase(uid))
        }
    }
}