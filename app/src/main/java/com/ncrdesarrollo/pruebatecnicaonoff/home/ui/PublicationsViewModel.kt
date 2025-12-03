package com.ncrdesarrollo.pruebatecnicaonoff.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.pruebatecnicaonoff.core.utils.UiState
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.IPublicationsInteractor
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicationsViewModel @Inject constructor(private val interactor: IPublicationsInteractor) :
    ViewModel() {

    private var _publicationsList = MutableStateFlow<UiState<List<Publication>>>(UiState.Loading)
    val publicationsList: MutableStateFlow<UiState<List<Publication>>> = _publicationsList

    init {
        getPublicationsDatabase()
    }

    fun getPublicationsApi() {
        viewModelScope.launch {
            _publicationsList.value = UiState.Loading
            try {
                val response = interactor.getPublications()

                if (response.isEmpty()){
                    _publicationsList.value = UiState.Empty
                } else {
                    _publicationsList.value = UiState.Success(response)
                }

            } catch (e: Exception) {
                _publicationsList.value = UiState.Error(message = e.message ?: "Error desconocido")
            }
        }

    }

    private fun getPublicationsDatabase() {
        viewModelScope.launch {
            _publicationsList.value = UiState.Loading
            try {
                val response = interactor.getPublicationsDatabase()

                if (response.isEmpty()){
                    _publicationsList.value = UiState.Empty
                } else {
                    _publicationsList.value = UiState.Success(response)
                }

            } catch (e: Exception) {
                _publicationsList.value = UiState.Error(message = e.message ?: "Error desconocido")
            }
        }

    }
}