package bonch.dev.networking.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//Объект, который формирует запрос в сеть
object RetrofitFactory {

    //Константа содержащая базовый URL-адрес
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    //Функция, компанующая запрос в сеть
    fun makeRetrofitService () : RetrofitService {

        //Retrofit.Builder компанует запрос в сеть
        val retrofit = Retrofit.Builder()

            //метод, в который передаётся базовый URL
            .baseUrl(BASE_URL)

            //Метод addConverterFactory() нужен чтобы конвертировать
            //JSON ответ в объект, мы используем
            //класс MoshiConverterFactory и его стандартный метод create.
            .addConverterFactory(MoshiConverterFactory.create())

            //метод, собирающий запрос
            .build()

        //Возвращаем из функции объект, при помощи которого будем выполнять запросы
        return retrofit.create(RetrofitService::class.java)
    }

}