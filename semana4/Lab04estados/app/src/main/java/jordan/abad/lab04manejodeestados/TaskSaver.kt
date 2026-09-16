package jordan.abad.lab04manejodeestados

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.snapshots.SnapshotStateList

fun tasksSaver(): Saver<SnapshotStateList<Task>, *> = listSaver(
    save = { list -> list.map { listOf(it.id, it.title, it.completed) } },
    restore = { saved ->
        val list = mutableStateListOf<Task>()
        saved.forEach {
            @Suppress("UNCHECKED_CAST")
            val item = it as List<Any>
            list.add(Task(id = item[0] as Int, title = item[1] as String, completed = item[2] as Boolean))
        }
        list
    }
)