package com.ncrdesarrollo.pruebatecnicaonoff.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.pruebatecnicaonoff.core.utils.UiState
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.IPublicationsInteractor
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicationsViewModel @Inject constructor(private val interactor: IPublicationsInteractor) :
    ViewModel() {

    private var _publicationsList = MutableStateFlow<UiState<List<Publication>>>(UiState.Loading)
    val publicationsList: MutableStateFlow<UiState<List<Publication>>> = _publicationsList

    fun getPublicationsApi() {
        viewModelScope.launch {
            interactor.getPublications()
        }

    }

    fun getPublicationsDatabase() {
        viewModelScope.launch {
            try {
                val response = interactor.getPublicationsDatabase()
                _publicationsList.value = UiState.Success(response)
            } catch (e: Exception) {
                _publicationsList.value = UiState.Error(message = e.message ?: "Error desconocido")
            } finally {
                Log.e("errorPublications", "finalizo")
            }
        }

    }
}