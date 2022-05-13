package fr.univ.lyon1.lpiem.ratus.ui.fund_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.univ.lyon1.lpiem.ratus.core.exception.UserNotFoundException
import fr.univ.lyon1.lpiem.ratus.domain.AddContributorUseCase
import fr.univ.lyon1.lpiem.ratus.domain.GetFundUseCase
import fr.univ.lyon1.lpiem.ratus.model.Fund
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FundViewModel(
    private val getFundUseCase: GetFundUseCase,
    private val addContributorUseCase: AddContributorUseCase
) : ViewModel() {
    val fund = MutableLiveData<Fund>()

    private val error = MutableLiveData<UserNotFoundException>()

    fun getError(): LiveData<UserNotFoundException> {
        return error
    }

    fun getFund(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fund.postValue(getFundUseCase.invoke(id))
        }
    }

    fun addContributor(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fund.postValue(addContributorUseCase.invoke(fund.value?.id ?: "", uid))
            } catch (exception: UserNotFoundException) {
                error.postValue(exception)
            }
        }
    }
}