package com.example.myapplication.data.presentation.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.model.Breed
import com.example.myapplication.data.persistence.DaoRepository
import com.example.myapplication.data.persistence.Model

private const val TAG = "FavouritesViewModel"

class FavouritesViewModel(
    private val repository: DaoRepository
) : ViewModel(){
    private val _dogsViewModel = MutableLiveData<List<Breed>>()
    val dogsLiveData = _dogsViewModel

    fun favBreed(breed: Breed) {
        repository.deleteById(breed.id) {
            updateDogs()
        }
    }

    fun loadDogs() {
        updateDogs()
    }

    private fun updateDogs() {
        repository.getDogs { dogs ->

            dogs.map(::dogToBreed)
                .let {
                    _dogsViewModel.postValue(it)
                }
        }
    }

    private fun dogToBreed(dog: Model): Breed {
        return Breed(
            bredFor = dog.bredFor,
            bredGroup = dog.bredGroup,
            id = dog.id,
            lifeSpan = dog.lifeSpan,
            name = dog.name,
            origin = dog.origin,
            temperament = dog.temperament,
            height = null,
            weight = null,
            fav = true,
        )
    }
}

class FavouritesViewModelFactory(
    private val repository: DaoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouritesViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}