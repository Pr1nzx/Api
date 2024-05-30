package dapri.api

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dapri.api.api.ApiClient
import dapri.api.api.ApiService
import dapri.api.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textViewUsername = findViewById(R.id.textViewUsername)
        textViewEmail = findViewById(R.id.textViewEmail)

        val buttonToPageTwo: Button = findViewById(R.id.button_to_pagetwo)
        buttonToPageTwo.setOnClickListener {
            val intent = Intent(this, PageTwo::class.java)
            startActivity(intent)
        }

        val buttonToThird: Button = findViewById(R.id.button_to_third)
        buttonToThird.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val apiService = ApiClient.retrofit.create(ApiService::class.java)
        val call = apiService.getUser(1)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {

                        textViewUsername.text = "Username: ${it.username}"
                        textViewEmail.text = "Email: ${it.email}"
                    }
                } else {
                    Toast.makeText(applicationContext, "Failed to get data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
