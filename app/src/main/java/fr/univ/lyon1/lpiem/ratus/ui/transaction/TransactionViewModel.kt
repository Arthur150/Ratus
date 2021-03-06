package fr.univ.lyon1.lpiem.ratus.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.AddTransactionUseCase
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {

    var transaction = Transaction()

    private val user = MutableLiveData<User>()

    fun getUser(): LiveData<User> {
        return user
    }

    fun sendTransaction() {
        viewModelScope.launch(Dispatchers.IO) {
            FirebaseAuth.getInstance().currentUser?.uid?.let { it1 ->
                user.postValue(
                    addTransactionUseCase.invoke(
                        it1,
                        transaction
                    )
                )
            }
        }
    }
}