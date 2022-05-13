package fr.univ.lyon1.lpiem.ratus.ui.homePage

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.GetRandomTricksUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserFundsUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.model.Trick
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getRandomTricksUseCase: GetRandomTricksUseCase,
    private val getUserFundsUseCase: GetUserFundsUseCase,
) : ViewModel() {
    private val user: MutableLiveData<User> = MutableLiveData()
    private val funds: MutableLiveData<List<Fund>> = MutableLiveData()

    val tricks: LiveData<List<Trick>> = liveData {
        emit(getRandomTricksUseCase.invoke())
    }

    fun getUser(): LiveData<User> {
        return user
    }

    fun getFunds(): LiveData<List<Fund>> {
        return funds
    }


    fun loadUserDetails() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                user.postValue(getUserUseCase.invoke(firebaseUser.uid))
                funds.postValue(getUserFundsUseCase.invoke(firebaseUser.uid))
            }
        }
    }
}