package bonch.dev.networking.networking

import bonch.dev.networking.models.Post
import retrofit2.Response
import retrofit2.http.GET

/*Интерфейс, который отвечает за составление запросов к серверу.
Запросы могут быть не только GET, но и POST, PUT, DELETE, PATCH

 */

interface RetrofitService {

    //Тип запроса с указание в скобках эндпоинта
    @GET("/posts")

    //Объявление функции с модификатором suspend
    //т.е. она будет вызвана из корутины, т.к. будет выполнять обращаение к серверу
    //Response это тип возвращаемых данных, который подразумевает успех и возможную ошибку,
    //при успешном выполнении запроса будет предоставлен массив из объектов типа Post
    suspend fun getPosts() : Response<List<Post>>

}