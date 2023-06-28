package com.example.farmer.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.farmer.Model.Farmer
import com.example.farmer.Repository.FarmerRepository
import kotlinx.coroutines.launch

class FarmerViewModel(private val repository: FarmerRepository): ViewModel() {
    val allFarmers: LiveData<List<Farmer>> = repository.allfarmers.asLiveData()

    fun insert(farmer: Farmer) = viewModelScope.launch {
        repository.insertFarmer(farmer)
    }

    fun delete(farmer: Farmer) = viewModelScope.launch {
        repository.deleteFarmer(farmer)
    }

    fun update(farmer: Farmer) = viewModelScope.launch {
        repository.updateFarmer(farmer)
    }
}

class FarmerViewModelFactory(private val repository: FarmerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FarmerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FarmerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
