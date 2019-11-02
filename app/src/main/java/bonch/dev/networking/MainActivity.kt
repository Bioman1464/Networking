package bonch.dev.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.networking.adapters.PostAdapter
import bonch.dev.networking.models.Post
import bonch.dev.networking.networking.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)

        rv.layoutManager = LinearLayoutManager(this)

        //Ссылка на метод создания запроса
        val service = RetrofitFactory.makeRetrofitService()

        //пускаем корутину в контексте диспетчера Ввода Вывод
        //с помощью билдера launch
        CoroutineScope(Dispatchers.IO).launch {

            //В response ходит ответ от сервера после обращения
            //к нему прописанного в RetrofitService
            val response = service.getPosts()

            //блок try catch
            try {

                //запускаем корутину к котексте Диспетчера Main,
                //чтобы м могли обратиться к элементам пользовательского интерфейса
                withContext(Dispatchers.Main) {

                    //обработка данные, если ответ от сервера успешный
                    if (response.isSuccessful) {

                        //передаём в функцию initRecyclerView
                        //данные уже сконвертированные из JSON массив
                        initRecyclerView(response.body()!!)

                       //Обработка отрицательного ответа сервера
                    } else {
                        //Вывод текста с сообщением ошибки
                        Toast.makeText(applicationContext, "${response.errorBody()}", Toast.LENGTH_SHORT).show()

                    }
                }
                //Ловим исключение возникающее при запроси в сеть, допустим если сервер недоступен
            } catch (err :  HttpException) {

                //Выводим в лог Error с тэгом Retrofit ошибку
                Log.e("Retrofit", "${err.printStackTrace()}")

            }
        }
    }

    //Фукнция получающая на вход массив объектов типа Post
    private fun initRecyclerView (list : List<Post>) {

        //Объявление адаптера к прокручиваемому списку
        //в PostAdater мы передаём массив объектов и контекст нашего приложения
        //в котором всё будет отображаться
        recyclerView.adapter = PostAdapter(list, this)

    }
}




