package fr.univ.lyon1.lpiem.ratus.ui.trick_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import fr.univ.lyon1.lpiem.ratus.domain.GetTrickUseCase
import fr.univ.lyon1.lpiem.ratus.model.Trick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrickDetailViewModel(
    private val getTrickUseCase: GetTrickUseCase
) : ViewModel() {
    val trick = MutableLiveData<Trick>()

    fun getTrick(id : String){
        viewModelScope.launch(Dispatchers.IO) {
            trick.postValue(getTrickUseCase.invoke(id))
        }
    }
}