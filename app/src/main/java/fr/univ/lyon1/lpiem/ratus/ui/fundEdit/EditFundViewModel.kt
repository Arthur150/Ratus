package fr.univ.lyon1.lpiem.ratus.ui.fundEdit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.domain.GetFundUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFundViewModel(
    private val getFundUseCase: GetFundUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val fund = MutableLiveData<Fund>()

    private val contributors = MutableLiveData<List<User>>().also {

    }

    private val user = MutableLiveData<User>()

    fun getFund(): LiveData<Fund> {
        return fund
    }

    fun getContributors(): LiveData<List<User>> {
        return contributors
    }

    fun getUser(): LiveData<User> {
        return user
    }

    fun loadFund(id : String){
        viewModelScope.launch(Dispatchers.IO) {
            val tmpFund = getFundUseCase.invoke(id)
            fund.postValue(tmpFund)
            contributors.postValue(tmpFund.contributors)
        }
    }

    fun loadUser(){
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                user.postValue(getUserUseCase.invoke(firebaseUser.uid))
            }
        }

    }

    fun addUserInContributors(user: User){
        contributors.postValue(contributors.value?.plus(user) ?: listOf(user))
    }

    fun sendFund(title: String, goal: Double) {
        val fund = contributors.value?.let { Fund(title = title, goal = goal, contributors = it) }
        Log.d("EditFundViewModel", "sendFund: $fund")
    }
}