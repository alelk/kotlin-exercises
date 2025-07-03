package kotlin_coroutines_example

import kotlinx.coroutines.flow.Flow
import java.util.*

data class User(val id: UUID, val name: String, val age: Int)

interface UserRepo {

  suspend fun getById(id: UUID): User?

  fun getAll(): Flow<User>

}

