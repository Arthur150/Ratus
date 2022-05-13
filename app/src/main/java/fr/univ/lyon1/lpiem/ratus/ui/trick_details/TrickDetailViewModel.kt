package fr.univ.lyon1.lpiem.ratus.ui.trick_details

import androidx.lifecycle.*
import fr.univ.lyon1.lpiem.ratus.domain.GetTrickUseCase
import fr.univ.lyon1.lpiem.ratus.model.Trick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrickDetailViewModel(
    private val getTrickUseCase: GetTrickUseCase
) : ViewModel() {
    private val trick = MutableLiveData<Trick>()

    fun getTrick(): LiveData<Trick> {
        return trick
    }

    fun loadTrick(id : String){
        viewModelScope.launch(Dispatchers.IO) {
            trick.postValue(getTrickUseCase.invoke(id))
        }
    }
}