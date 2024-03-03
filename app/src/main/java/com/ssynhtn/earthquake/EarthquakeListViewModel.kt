package com.ssynhtn.earthquake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ssynhtn.earthquake.model.ui.Earthquake
import com.ssynhtn.earthquake.repo.EarthquakeRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EarthquakeListViewModel(private val repo: EarthquakeRepo) : ViewModel() {
    // this state indicates whether the ui is currently loading
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _items = MutableLiveData<Result<List<Earthquake>>>()
    val items: LiveData<Result<List<Earthquake>>> = _items


    fun refresh(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        _loading.value = true
        viewModelScope.launch {
            val items = withContext(dispatcher) {
                try {
                    Result.success(repo.fetch())
                } catch (ex: Exception) {
                    Result.failure(ex)
                }
            }

            _loading.value = false
            _items.value = items
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repo: EarthquakeRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EarthquakeListViewModel(repo) as T
        }
    }

}