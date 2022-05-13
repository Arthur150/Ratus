package fr.univ.lyon1.lpiem.ratus.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val user = MutableLiveData<User>()

    fun getUser() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                user.postValue(getUserUseCase.invoke(firebaseUser.uid))
            }
        }

    }
}