package fr.univ.lyon1.lpiem.ratus.ui.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.GetBudgetUseCase
import fr.univ.lyon1.lpiem.ratus.model.Budget

class BudgetViewModel(private val getBudgetUseCase: GetBudgetUseCase) : ViewModel() {
    val budget: LiveData<Budget> = liveData {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            emit(getBudgetUseCase.invoke(user.uid))
        }
    }
}