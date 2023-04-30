package com.android.batya.dreams.repository

import android.util.Log
import com.android.batya.dreams.data.DataOrException
import com.android.batya.dreams.model.Dream
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await

class DreamRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private fun getDreamsCollection(): CollectionReference {
        return db.collection("users/${auth.currentUser!!.uid}/dreams")
    }
    suspend fun getDreams() : DataOrException<List<Dream>, Boolean, Exception> {
        val dataOrException = DataOrException<List<Dream>, Boolean, Exception>()

        try {
            dataOrException.loading = true

            dataOrException.data = getDreamsCollection().get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(Dream::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false

        } catch (exc: FirebaseFirestoreException) {
            dataOrException.e = exc
        }
        return dataOrException
    }

    suspend fun getDreamById(dreamId: String) : DataOrException<Dream, Boolean, Exception> {
        val dataOrException = DataOrException<Dream, Boolean, Exception>()

        try {
            dataOrException.loading = true

            dataOrException.data = getDreamsCollection().document(dreamId).get().await().toObject(Dream::class.java)
            if (dataOrException.data != null) dataOrException.loading = false

        } catch (exc: FirebaseFirestoreException) {
            dataOrException.e = exc
        }
        return dataOrException
    }

    fun addDream(dream: Dream) {
        val dreamMap = hashMapOf(
            "id" to dream.id,
            "title" to dream.title,
            "description" to dream.description,
            "date" to dream.date,
            "time" to dream.time,
            "mood" to dream.mood,
            "lucidity" to dream.lucidity,
            "tags" to dream.tags
        ).toMap()
        getDreamsCollection()
            .document(dream.id)
            .set(dreamMap)
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
    }
    fun deleteDreamById(dreamId: String) {
        getDreamsCollection()
            .document(dreamId)
            .delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "Success deleting dream $dreamId")
                }
            }
    }

    fun updateDream(dream: Dream) {
        val dreamToUpdate = hashMapOf(
           // "id" to dream.id,
            "title" to dream.title,
            "description" to dream.description,
            "date" to dream.date,
            "time" to dream.time,
            "mood" to dream.mood,
            "lucidity" to dream.lucidity,
            "tags" to dream.tags
        ).toMap()

        getDreamsCollection()
            .document(dream.id)
            .update(dreamToUpdate)
            .addOnCompleteListener {
                Log.d("TAG", "Success updating dream $dream")

            }.addOnFailureListener{ exception ->
                Log.w("TAG", "Error updating document" , exception)
            }
    }

}