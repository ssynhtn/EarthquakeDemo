package com.ssynhtn.earthquake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssynhtn.earthquake.model.Earthquake
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EarthquakeListViewModel : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _items = MutableLiveData<List<Earthquake>>()
    val items: LiveData<List<Earthquake>> = _items

    private val repo = EarthquakeRepo()


    init {
        refresh()
    }

    fun refresh() {
        _loading.value = true

        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) {
                repo.fetch()
            }

            _loading.value = false
            _items.value = items
        }
    }

}