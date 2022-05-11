package fr.univ.lyon1.lpiem.ratus.ui.fund_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.univ.lyon1.lpiem.ratus.domain.GetFundUseCase
import fr.univ.lyon1.lpiem.ratus.model.Fund
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FundViewModel(
    private val getFundUseCase: GetFundUseCase
) : ViewModel() {
    val fund = MutableLiveData<Fund>()

    fun getFund(id : String){
        viewModelScope.launch(Dispatchers.IO) {
            fund.postValue(getFundUseCase.invoke(id))
        }
    }
}