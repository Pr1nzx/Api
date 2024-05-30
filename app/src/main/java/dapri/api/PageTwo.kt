package dapri.api

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dapri.api.api.ApiClient
import dapri.api.api.adapter.ProductAdapter
import dapri.api.api.model.Product
import dapri.api.api.model.ProductResponse
import androidx.recyclerview.widget.DiffUtil
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.ListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PageTwo : AppCompatActivity() {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ProductResponse>
    private lateinit var adapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagetwo)
        swipeRefresh = findViewById(R.id.swipe_refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)

        adapter = ProductAdapter { product -> productOnClick(product) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefresh.setOnRefreshListener {
            getData()
        }
        getData()

    }

    private fun productOnClick(product: Product) {
        Toast.makeText(applicationContext, product.description, Toast.LENGTH_SHORT).show()
    }

    private fun getData(){
        swipeRefresh.isRefreshing = true


        call = ApiClient.productService.getProducts()

        call.enqueue(object : Callback<ProductResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                swipeRefresh.isRefreshing = false
                if (response.isSuccessful) {
                   adapter.submitList(response.body()?.products)
                    adapter.notifyDataSetChanged()
                }
            }


            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }

}
