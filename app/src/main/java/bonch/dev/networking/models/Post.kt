package bonch.dev.networking.models

//Класс данных составленный по образцу ответа сервера на эндпоинте /post

data class Post (val userId : Int,
                 val id : Int,
                 val body : String,
                 val title : String)