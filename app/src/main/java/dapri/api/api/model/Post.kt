package dapri.api.api.model

data class Post(
    val title: String,
    val body: String,
    val userId: Int = 1,
    val id: Int? = null
)
