package com.pandorika.cryptomap.api

import com.app.coinwatcher.models.CoinCap
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Leonov Oleg, http://pandorika-it.com on 24.05.16.
 */
interface ServerApi {

    @GET("ticker")
    fun loadData(@Query("limit") limit: Int): Deferred<List<CoinCap>>
}
