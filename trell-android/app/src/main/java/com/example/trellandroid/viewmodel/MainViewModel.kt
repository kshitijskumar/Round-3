package com.example.trellandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.trellandroid.data.repositories.MainRepository
import com.example.trellandroid.data.repositories.repoimpl.MainRepositoryImpl

@Suppress("UNCHECKED_CAST")
class MainViewModel(
        private val repo: MainRepository
) : ViewModel() {




    companion object {
        private class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val mainRepository = MainRepositoryImpl()
                return MainViewModel(mainRepository) as T
            }
        }

        fun provideMainViewModel(owner: ViewModelStoreOwner): MainViewModel {
            return ViewModelProvider(owner, MainViewModelFactory())[MainViewModel::class.java]
        }
    }
}