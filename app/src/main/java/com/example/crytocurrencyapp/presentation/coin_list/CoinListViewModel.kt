package com.example.crytocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crytocurrencyapp.common.Resource
import com.example.crytocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

// Why we still have VM bcz there main work is taking data n all from repository
// but that thing is already done in 'domain layer->useCases' as per 'clean code architecture'

// We still have VM bcz they keep the "State" of the ui
// : when we rotate screen
// : when configuration changes
// It still holds some business logic like
// : do we want to show progress bar : is there an error : what is the coinsList

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    // The init block is a section of code that executes when an object of a class is created.
    init {
        getCoins()
    }
    private fun getCoins() {
        // bcz of invoke fun we are now able to use "GetCoinsUseCase" class as a fun
        // and there is a flow which emit various thing
        // So now we are going to handle/Use them here
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
        // launchIn = Flows are asynchronous so we have to launch them on coroutine
    }

}