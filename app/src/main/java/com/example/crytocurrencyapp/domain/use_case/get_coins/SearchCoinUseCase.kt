package com.example.crytocurrencyapp.domain.use_case.get_coins

import androidx.compose.runtime.mutableStateOf
import com.example.crytocurrencyapp.common.Resource
import com.example.crytocurrencyapp.data.remote.dto.toCoin
import com.example.crytocurrencyapp.domain.model.Coin
import com.example.crytocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coinList = repository.getCoins().map { it.toCoin() }

        }
        catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "An Unexpected Error Occurred"
                )
            )
        }
        catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your Internet Connection"))
        }

    }
}