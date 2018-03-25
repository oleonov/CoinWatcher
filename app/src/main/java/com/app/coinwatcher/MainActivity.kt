package com.app.coinwatcher

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.app.coinwatcher.adapters.CoinAdapter
import com.app.coinwatcher.components.NetworkActivity
import com.pandorika.cryptomap.api.ServiceGenerator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainActivity : NetworkActivity() {

    private lateinit var adapter:CoinAdapter
    companion object {
        private const val NETWORK_REQUEST="networkRequest"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= CoinAdapter()
        recyclerView.adapter=adapter

        loadData()
    }

    private fun loadData()
    {
        stopJob(NETWORK_REQUEST)
        addAsyncJob(launch(UI) {
            try {
                llLoading.visibility= View.VISIBLE
                llNoConnection.visibility = View.GONE
                adapter.mData = ServiceGenerator.serverApi.loadData(100).await()
                recyclerView.visibility = View.VISIBLE
            }
            catch (ex:Exception)
            {
                //Нет сети
                ex.printStackTrace()
                llNoConnection.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            finally {
                llLoading.visibility= View.GONE
            }
        }, NETWORK_REQUEST)
    }
    fun onRetryClick(v:View)
    {
        loadData()
    }
}
