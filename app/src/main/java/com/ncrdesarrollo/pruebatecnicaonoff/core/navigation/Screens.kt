package com.ncrdesarrollo.pruebatecnicaonoff.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class CommentsView(val idProduct: Int? = null)
