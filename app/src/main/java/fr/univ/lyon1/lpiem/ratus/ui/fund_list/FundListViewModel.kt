package fr.univ.lyon1.lpiem.ratus.ui.fund_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.GetUserFundsUseCase
import fr.univ.lyon1.lpiem.ratus.model.Fund
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FundListViewModel(
    private val getUserFundsUseCase: GetUserFundsUseCase
) : ViewModel(){

    val funds: MutableLiveData<List<Fund>> = MutableLiveData()

    fun getFunds() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                funds.postValue(getUserFundsUseCase.invoke(firebaseUser.uid))
            }
        }
    }
}