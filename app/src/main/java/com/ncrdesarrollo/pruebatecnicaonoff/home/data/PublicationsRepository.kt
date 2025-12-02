package com.ncrdesarrollo.pruebatecnicaonoff.home.data

import android.util.Log
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.IPublicationsRepository
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication
import org.json.JSONArray
import javax.inject.Inject

class PublicationsRepository @Inject constructor(private val dataSource: IPublicationsDataSource):
    IPublicationsRepository {
    override suspend fun getPublications(): List<Publication> {

        val listPublications: MutableList<Publication> = mutableListOf()

        runCatching { dataSource.getPublications() }
            .onSuccess {
                val responseString: String? = it.body()?.string() ?: it.errorBody()?.string()
                val jsonArray = JSONArray(responseString)
                for (i in 0  until  jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val model = Publication(
                        userId = jsonObject.optInt("userId"),
                        id = jsonObject.optInt("id"),
                        title = jsonObject.optString("title"),
                        body = jsonObject.optString("body"),
                    )
                    listPublications.add(model)
                }

            }
            .onFailure { Log.e("errorPublications", it.message.toString()) }

        return listPublications
    }
}