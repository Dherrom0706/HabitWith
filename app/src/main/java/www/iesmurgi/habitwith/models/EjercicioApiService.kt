package www.iesmurgi.habitwith.models

import retrofit2.http.GET
import retrofit2.http.Path

interface EjercicioApiService {


    @GET("exercises/bodyPartList")
    suspend fun getMuscles(): List<Musculo>

    @GET("exercises/exercisesByMuscle/{muscleId}")
    suspend fun getExercisesByMuscle(@Path("muscleId") muscleId: String): List<Ejercicio>


}