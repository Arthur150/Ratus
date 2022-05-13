package fr.univ.lyon1.lpiem.ratus.ui.fundEdit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.core.exception.UserNotFoundException
import fr.univ.lyon1.lpiem.ratus.domain.AddContributorUseCase
import fr.univ.lyon1.lpiem.ratus.domain.CreateFundUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetFundUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetUserUseCase
import fr.univ.lyon1.lpiem.ratus.model.Fund
import fr.univ.lyon1.lpiem.ratus.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFundViewModel(
    private val getFundUseCase: GetFundUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val createFundUseCase: CreateFundUseCase,
    private val addContributorUseCase: AddContributorUseCase
) : ViewModel() {
    private val fund = MutableLiveData<Fund>()

    private val contributors = MutableLiveData<List<User>>()

    private val user = MutableLiveData<User>()

    private val error = MutableLiveData<UserNotFoundException>()

    fun getError(): LiveData<UserNotFoundException> {
        return error
    }

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

    fun addUserInContributors(userId: String){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = getUserUseCase.invoke(userId,false)
                contributors.postValue(contributors.value?.plus(user) ?: listOf(user))
            } catch (exception: UserNotFoundException) {
                error.postValue(exception)
            }
        }
    }

    fun sendFund(title: String, goal: Double) {
        val newFund = contributors.value?.let { Fund(title = title, goal = goal, contributors = it) }
        viewModelScope.launch {
            newFund?.let { fund.postValue(createFundUseCase.invoke(it)) }
        }
        Log.d("EditFundViewModel", "sendFund: $fund")
    }
}