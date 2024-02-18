package com.example.crytocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crytocurrencyapp.common.Constants
import com.example.crytocurrencyapp.common.Resource
import com.example.crytocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

/** savedStateHandle */
// savedStateHandle = It is a bundle, and it contains information about the saved state
// so that we can use that info to restore our app from process death.
// It will also contain the navigation parameters

// What we do is when we navigate from CoinListScreen to CoinDetailScreen we will pass the CoinId
// as parameter to our CoinDetailScreen and in CoinDetailScreen we load that coin from the api.

// BCZ we pass CoinId as a navigation parameter then that CoinId is contained in that savedInstance state
// already i.e. A 'SavedStateHandle'.

// So now we can just access it in our VM and load the corresponding coin here


/** VM */

// Why we still have VM bcz there main work is taking data n all from repository
// but that thing is already done in 'domain layer->useCases' as per 'clean code architecture'

// We still have VM bcz they keep the "State" of the ui
// : when we rotate screen
// : when configuration changes
// It still holds some business logic like
// : do we want to show progress bar : is there an error : what is the coinsList

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    // The init block is a section of code that executes when an object of a class is created.
    init {
        // .let is to check null
        // if it is not equal to null then proceed with the block
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }
    private fun getCoin(coinId: String) {
        // bcz of invoke fun we are now able to use "GetCoinsUseCase" class as a fun
        // and there is a flow which emit various thing
        // So now we are going to handle/Use them here
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
        // launchIn = Flows are asynchronous so we have to launch them on coroutine
    }

}