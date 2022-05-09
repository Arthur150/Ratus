package fr.univ.lyon1.lpiem.ratus.ui.trick_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import fr.univ.lyon1.lpiem.ratus.domain.GetTricksUseCase
import fr.univ.lyon1.lpiem.ratus.model.Trick

class TrickListViewModel(
    private val getTricksUseCase: GetTricksUseCase
) :ViewModel() {

    val tricks : LiveData<List<Trick>> = liveData {
        emit(getTricksUseCase.invoke())
    }

}