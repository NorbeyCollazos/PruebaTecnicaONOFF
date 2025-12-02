package com.ncrdesarrollo.pruebatecnicaonoff.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.IPublicationsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicationsViewModel @Inject constructor(private val interactor: IPublicationsInteractor): ViewModel() {

    fun getPublications() {
        viewModelScope.launch {
            interactor.getPublications().forEach {
                Log.i("publications", it.toString())
            }
        }

    }

}