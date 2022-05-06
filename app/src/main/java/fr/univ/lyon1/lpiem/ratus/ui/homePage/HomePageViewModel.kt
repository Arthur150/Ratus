package fr.univ.lyon1.lpiem.ratus.ui.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.User

class HomePageViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val user: LiveData<User> = liveData {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            emit(getUserUseCase.invoke(user.uid))
        }
    }
}