package fr.univ.lyon1.lpiem.ratus.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.AddTransactionUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.Transaction
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {

    var transaction = Transaction()

    fun sendTransaction(){
        viewModelScope.launch {
            FirebaseAuth.getInstance().currentUser?.uid?.let { it1 ->
                addTransactionUseCase.invoke(
                    it1,
                    transaction
                )
            }
        }
    }
}