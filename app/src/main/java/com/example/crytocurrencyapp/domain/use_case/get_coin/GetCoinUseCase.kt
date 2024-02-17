package com.example.crytocurrencyapp.domain.use_case.get_coin

import com.example.crytocurrencyapp.common.Resource
import com.example.crytocurrencyapp.data.remote.dto.toCoin
import com.example.crytocurrencyapp.data.remote.dto.toCoinDetail
import com.example.crytocurrencyapp.domain.model.Coin
import com.example.crytocurrencyapp.domain.model.CoinDetail
import com.example.crytocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    // some call this 'invoke' fun 'execute'
    // using this invoke we can call this "GetCoinsUseCase" class as a "fun"

    // Then we return a flow bcz we want to emit multiple values over a period of time
    // 1. we want to emit that now we are actually loading use cases so we can display the progress bar
    // 2. we want to emit if its sucessful and we want to attach our data i.e. list of coins
    // 3. and if an error happens we want to emit an error here
    // that is what this "Resource" will be helpful for

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            // val 'coin' is CoinDetailDto and we are returning the CoinDetail
            // so we map them
            // Meaning = Jo CoinDetailDto ka data tha usko hum-ne CoinDetail mai map kr dia

            // so if that line did crash so we know it was successful { IDK didn't understand }
            // so we can emit Resource.Success

            // Success mai wo data pass kr na hai jo VM mai pass hoga
            emit(Resource.Success(coin))
        }

        // HttpException = which will happen if we get a not successful req
        // i.e. if we get a response code that doesn't start with 2 (means 200's)
        catch (e: HttpException) {
            // if e.localizedMessage is null then use the string given
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error Occurred"))
        }

        // IOException = when our Repository or api can't talk to the actual Remote Api
        // ForEx = No internet connection
        // [my guess] = basically Client error and response code should be in 400's
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your Internet Connection"))
        }
    }
}