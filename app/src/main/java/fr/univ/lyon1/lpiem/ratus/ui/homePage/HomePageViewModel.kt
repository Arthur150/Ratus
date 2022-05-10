package fr.univ.lyon1.lpiem.ratus.ui.homePage

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.GetRandomTricksUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.Trick
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getRandomTricksUseCase: GetRandomTricksUseCase
) : ViewModel() {
    val user: MutableLiveData<User> = MutableLiveData()

    val tricks : LiveData<List<Trick>> = liveData {
        emit(getRandomTricksUseCase.invoke())
    }

    fun getUserDetails() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                user.postValue(getUserUseCase.invoke(firebaseUser.uid))
            }
        }
    }
}