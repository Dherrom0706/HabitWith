package www.iesmurgi.habitwith.models

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    /**
     *Obtiene la lista de ejercicios por músculo.
     * @param url URL de la solicitud.
     * @return Respuesta de la API que contiene los ejercicios.
     */
    @GET
    suspend fun getExercisesByMuscle(@Url url: String): Response<EjercicioResponse>

    /**
     *
     * Obtiene un ejercicio por su ID.
     * @param url URL de la solicitud.
     * @return Respuesta de la API que contiene el ejercicio.
     */
    @GET
    suspend fun getExerciseById(@Url url: String): Response<EjercicioImagenResponse>

    /**
     *
     * Obtiene la lista de recetas.
     * @param url URL de la solicitud.
     * @return Respuesta de la API que contiene las recetas.
     */
    @GET
    suspend fun getRecipes(@Url url: String): Response<RecetaResponse>

    /**
     *
     * Obtiene un producto por su código.
     * @param code Código del producto.
     * @return Respuesta de la API que contiene el producto.
     */
    @GET
    suspend fun getProductByCode(@Url code: String): Response<ProductResponse>
}