package com.ncrdesarrollo.pruebatecnicaonoff.home.domain

import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication

interface IPublicationsRepository {

    suspend fun getPublications(): List<Publication>
}


