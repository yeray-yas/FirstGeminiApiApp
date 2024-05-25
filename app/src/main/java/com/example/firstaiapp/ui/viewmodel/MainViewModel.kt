package com.example.firstaiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstaiapp.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private val _responseText = MutableLiveData<String?>()
    val responseText: LiveData<String?> = _responseText

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun generateContent(prompt: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repositoryImpl.generateContent(prompt)
            withContext(Dispatchers.Main) {
                _responseText.value = response
                _isLoading.value = false
            }
        }
    }
}

