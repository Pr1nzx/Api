package dapri.api

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dapri.api.api.ApiClient
import dapri.api.api.ApiService
import dapri.api.api.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextBody: EditText
    private lateinit var buttonPost: Button
    private lateinit var buttonPut: Button
    private lateinit var buttonDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextBody = findViewById(R.id.editTextBody)
        buttonPost = findViewById(R.id.button_post)
        buttonPut = findViewById(R.id.button_put)
        buttonDelete = findViewById(R.id.button_delete)

        buttonPost.setOnClickListener { postData() }
        buttonPut.setOnClickListener { putData() }
        buttonDelete.setOnClickListener { deleteData() }
    }

    private fun postData() {
        val title = editTextTitle.text.toString()
        val body = editTextBody.text.toString()
        val post = Post(title, body)

        val call = ApiClient.apiService.createPost(post)

        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Post Created", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Failed to create post", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun putData() {
        val title = editTextTitle.text.toString()
        val body = editTextBody.text.toString()
        val post = Post(title, body)

        val call = ApiClient.apiService.updatePost(11, post)

        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Post Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Failed to update post", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteData() {
        val call = ApiClient.apiService.deletePost(11)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Post Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Failed to delete post", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
