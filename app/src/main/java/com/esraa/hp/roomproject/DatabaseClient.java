package com.esraa.hp.roomproject;


import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private AppDatabase db;
    private final String DB_NAME="MyContacts";

           public DatabaseClient(Context context){

               db=Room.databaseBuilder(context,AppDatabase.class,DB_NAME).build();
    }

    public AppDatabase getAppDatabase(){
               return db;
    }


    String comment="هنا بنعمل build للداتابيز و هنديها اسم " +
            "هنعمل اوبجكت من AppDatabase اللى جواه متعرف ال DAO اللى جواه ال methods " +
            "جوة ال constructor هنستخدم databaseBuilder method و دى هنديها كونتكست و اسم ال AppDatabase و اسم الداتابيز " +
            "و هنعمل getter يرجع ال AppDatabase " +
            "كدة لو عاوز انادى على الميثود اللى بتعمل create مثلا اللى جوه ال DAO " +
            "هعمل اوبجكن من الكلاينت و انادى على getAppDatabase بعد كدة databaseDao و بعد كدة انادى على الميثود اللى جواه";
}
