package com.example.trellandroid.viewmodel

import androidx.lifecycle.*
import com.example.trellandroid.data.repositories.MainRepository
import com.example.trellandroid.data.repositories.repoimpl.MainRepositoryImpl
import com.example.trellandroid.data.responses.CommentResponse
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.Constants.COMMENT_SCORE
import com.example.trellandroid.utils.Constants.LIKE_SCORE
import com.example.trellandroid.utils.Constants.PROFILE_SCORE
import com.example.trellandroid.utils.Constants.SAVE_SCORE
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


    private var _comments = MutableLiveData<Result<List<CommentResponse>>>()
    val comments : LiveData<Result<List<CommentResponse>>>
        get() = _comments


    /**
     * function that gets list of vlogs from the repository and updates the _allPosts livedata
     */
    fun getAllVlogs() = viewModelScope.launch {
        _allPosts.value = Result.Loading
        val vlogs = repo.fetchAllVlogs()
        _allPosts.value = vlogs
    }


    /**
     * function that gets the the details of a particular vlog and updates the _postDetails livedata.
     * It also calls the setInterestScore function in the repository with score = WATCH_SCORE
     * @param vlogId vlogId of the vlog whose data is to be fetched
     */
    fun getVlogDetails(vlogId: Long) = viewModelScope.launch {
        _postDetails.value = Result.Loading
        val vlog = repo.getVlogDetails(vlogId)
        _postDetails.value = vlog
        repo.setInterestScore(vlogId, WATCH_SCORE)
    }

    /**
     * function that gets the details of the user profile and updates the _userDetails livedata
     * It also calls the setInterestScore function in the repository with score = PROFILE_SCORE only when the isInterest = true
     * @param vlogId vlogId of the particular vlog
     * @param userId userId of the creator of the vlog
     * @param isInterest if the user-profile is fetched through the post-details then it is true, else default value is false
     */
    fun getUserProfile(vlogId: Long, userId: Long, isInterest: Boolean = false) = viewModelScope.launch {
        _userDetails.value = Result.Loading
        val user = repo.getProfile(userId)
        _userDetails.value = user
        if(isInterest) {
            repo.setInterestScore(vlogId, PROFILE_SCORE)
        }
    }

    /**
     * function that handle the action of like button.
     * It also calls the setInterestScore fun with score = LIKE_SCORE
     * @param vlogId vlogId of the vlog
     */
    fun likeVlog(vlogId: Long) = viewModelScope.launch {

        //a function in the repository would be called to notify the backend about the like
        //repo.notifyLike(vlogId)

        repo.setInterestScore(vlogId, LIKE_SCORE)
    }

    /**
     * function that gets all the comments and updates comments livedata
     * @param vlogId vlogId of the vlog
     */
    fun getAllComments(vlogId: Long) = viewModelScope.launch {
        _comments.value = Result.Loading
        val vlogComments = repo.fetchAllComments(vlogId)
        _comments.value = vlogComments
    }

    /**
     * function that handles saving the vlog.
     * It also calls the setInterestScore fun with score = SAVE_SCORE
     * @param vlogId vlogId of the vlog
     */
    fun saveVlog(vlogId: Long) = viewModelScope.launch {

        //a function in the repository would be called to notify the backend about the save
        //repo.saveVlog(vlogId)

        repo.setInterestScore(vlogId, SAVE_SCORE)
    }

    /**
     * function that handles posting the comment.
     * It also calls the setInterestScore fun with score = COMMENT_SCORE
     * @param vlogId vlogId of the vlog
     * @param text text of the comment
     */
    fun postComment(vlogId: Long, text: String) = viewModelScope.launch {
        //main-repository would be having a function to actually post a comment
        //we'll add the user's comment to the existing comment list so that they can instantly see their comment

        repo.setInterestScore(vlogId, COMMENT_SCORE)
    }

    companion object {
        private class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val mainRepository = MainRepositoryImpl()
                return MainViewModel(mainRepository) as T
            }
        }

        /**
         * provides the MainViewModel.
         * @param owner Owner of the viewmodel
         * @return MainViewModel
         */
        fun provideMainViewModel(owner: ViewModelStoreOwner): MainViewModel {
            return ViewModelProvider(owner, MainViewModelFactory())[MainViewModel::class.java]
        }
    }
}