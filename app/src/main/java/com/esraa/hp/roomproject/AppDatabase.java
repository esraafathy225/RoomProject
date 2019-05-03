package com.esraa.hp.roomproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.location.Address;

@Database(entities = {Contact.class}, version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase{

    public abstract DatabaseDAO databaseDao();

    String comment="قبل الكلاس هحط @Database annotation عشان دة كدة اللى فيه كل مكونات الداتابيز" +
            " او ممكن نسميه database schema" +
            "فوق هنعرف ال tables كلها و هنا بنسميها entities و هنحدد ال version بتاعة ال database و جوة الكلاس هنعمل getter لل DAO";

}
