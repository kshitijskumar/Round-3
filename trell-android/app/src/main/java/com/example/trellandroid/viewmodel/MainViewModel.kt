package com.example.trellandroid.viewmodel

import androidx.lifecycle.*
import com.example.trellandroid.data.repositories.MainRepository
import com.example.trellandroid.data.repositories.repoimpl.MainRepositoryImpl
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.Constants.LIKE_SCORE
import com.example.trellandroid.utils.Constants.PROFILE_SCORE
import com.example.trellandroid.utils.Constants.WATCH_SCORE
import com.example.trellandroid.utils.Result
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainViewModel(
        private val repo: MainRepository
) : ViewModel() {

    private var _allPosts = MutableLiveData<Result<List<VlogResponse>>>()
    val allPosts : LiveData<Result<List<VlogResponse>>>
        get() = _allPosts

    private var _postDetails = MutableLiveData<Result<VlogResponse>>()
    val postDetails : LiveData<Result<VlogResponse>>
        get() = _postDetails

    private var _userDetails = MutableLiveData<Result<UserResponse>>()
    val userDetails : LiveData<Result<UserResponse>>
        get() = _userDetails



    fun getAllVlogs() = viewModelScope.launch {
        _allPosts.value = Result.Loading
        val vlogs = repo.fetchAllVlogs()
        _allPosts.value = vlogs
    }


    fun getVlogDetails(vlogId: Long) = viewModelScope.launch {
        _postDetails.value = Result.Loading
        val vlog = repo.getVlogDetails(vlogId)
        _postDetails.value = vlog
        repo.setInterestScore(vlogId, WATCH_SCORE)
    }

    fun getUserProfile(vlogId: Long, userId: Long) = viewModelScope.launch {
        _userDetails.value = Result.Loading
        val user = repo.getProfile(userId)
        _userDetails.value = user
        repo.setInterestScore(vlogId, PROFILE_SCORE)
    }

    fun likeVlog(vlogId: Long) = viewModelScope.launch {

        //a function in the repository would be called to notify the backend about the like
        //repo.notifyLike(vlogId)


        repo.setInterestScore(vlogId, LIKE_SCORE)
    }

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