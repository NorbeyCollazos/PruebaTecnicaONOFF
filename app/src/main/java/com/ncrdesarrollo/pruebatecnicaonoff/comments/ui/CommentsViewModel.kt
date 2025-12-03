package com.ncrdesarrollo.pruebatecnicaonoff.comments.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity
import com.ncrdesarrollo.pruebatecnicaonoff.comments.domain.ICommentsInteractor
import com.ncrdesarrollo.pruebatecnicaonoff.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val interactor: ICommentsInteractor
) : ViewModel() {

    private val idPublication = savedStateHandle.get<Int>("idProduct") ?: 0


    private var _commentsList = MutableStateFlow<UiState<List<CommentsEntity>>>(UiState.Loading)
    val commentsList: MutableStateFlow<UiState<List<CommentsEntity>>> = _commentsList

    init {
        getListComments()
    }

    private fun getListComments(){
        viewModelScope.launch {
            _commentsList.value = UiState.Loading

            try {
                val comments = interactor.getCommentsDatabase(idPublication)
                if (comments.isEmpty()) {
                    _commentsList.value = UiState.Empty
                }else {
                    _commentsList.value = UiState.Success(comments)
                }
            } catch (e: Exception) {
                _commentsList.value = UiState.Error(message = e.message ?: "Error desconocido")
            }


        }
    }

    fun insertComment(comment: String) {
        viewModelScope.launch {
            val commentModel = CommentsEntity(0, idPublication, comment)
            val responseInsert = interactor.insertComment(commentModel)
            if (responseInsert) {
                getListComments()
            } else {
                _commentsList.value = UiState.Error(message = "No se pudo enviar el comentario")
            }
        }
    }
}