package jordan.abad.lab04manejodeestados

data class Task(
    val id: Int,
    val title: String,
    val completed: Boolean = false
)