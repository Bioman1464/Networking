package bonch.dev.networking.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.networking.R
import bonch.dev.networking.adapters.UsersAdapter
import bonch.dev.networking.networking.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class UsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val recycler=findViewById<RecyclerView>(R.id.users_recycler)
        recycler.layoutManager= LinearLayoutManager(this)
        val service= RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.TransferToUsersActivity()
            try {
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        recycler.adapter= UsersAdapter(response.body()!!,this@UsersActivity)
                    }
                    else{
                        Toast.makeText(applicationContext,"${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

            }catch (err: HttpException){
                Log.e("Retrofit","${err.printStackTrace()}")
            }
        }
    }
}
