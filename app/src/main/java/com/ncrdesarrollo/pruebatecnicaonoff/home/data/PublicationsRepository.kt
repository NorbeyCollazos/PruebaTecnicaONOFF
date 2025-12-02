package com.ncrdesarrollo.pruebatecnicaonoff.home.data

import android.util.Log
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase.PublicationsEntity
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.IPublicationsRepository
import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication
import org.json.JSONArray
import javax.inject.Inject

class PublicationsRepository @Inject constructor(private val dataSource: IPublicationsDataSource) :
    IPublicationsRepository {
    override suspend fun getPublications(): List<Publication> {

        val listPublications: MutableList<Publication> = mutableListOf()

        runCatching { dataSource.getPublications() }
            .onSuccess {
                val responseString: String? = it.body()?.string() ?: it.errorBody()?.string()
                val jsonArray = JSONArray(responseString)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val model = Publication(
                        userId = jsonObject.optInt("userId"),
                        id = jsonObject.optInt("id"),
                        title = jsonObject.optString("title"),
                        body = jsonObject.optString("body"),
                    )
                    listPublications.add(model)
                    insertListPublications(listPublications)
                }

            }
            .onFailure { Log.e("errorPublications", it.message.toString()) }

        return listPublications
    }

    override suspend fun getPublicationsDatabase(): List<Publication> {
        val publicationsListDatabase = dataSource.getPublicationsDatabase()

        val listPublications: MutableList<Publication> = mutableListOf()

        publicationsListDatabase.forEach {
            val publication = Publication(
                userId = it.userId,
                id = it.id,
                title = it.title,
                body = it.body
            )
            listPublications.add(publication)
        }

        return listPublications
    }


    private suspend fun insertListPublications(listPublications: List<Publication>) {

        val listPublicationsEntity: MutableList<PublicationsEntity> = mutableListOf()

        listPublications.forEach { publication ->
            val publicationsEntity = PublicationsEntity(
                userId = publication.userId,
                id = publication.id,
                title = publication.title,
                body = publication.body
            )

            listPublicationsEntity.add(publicationsEntity)
        }

        dataSource.insertPublications(listPublicationsEntity)

    }
}