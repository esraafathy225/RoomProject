package com.esraa.hp.roomproject;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DatabaseDAO {
String comment="الاول هحط قبل اسم ال interface ال annotation @DAO عشان اعرفه ان دا مكان ال operations اللى انا عاوز اعملها " +
        "1. اول حاجة ال add هستخدم @Insert و هعمل ال abstract method بتاخد List of contacts ك parameter" +
        "2. تانى حاجة ال read هستخدم @Query و هعمل ال select query و اخليه يرجع List of contacts و هنا مينفعش يرجع " +
        "غير List مينفعش arraylist مثلا" +
        "3. ال update هيبقي @update و الميثود بتاخد contact object عشان تعدله " +
        "4.ال delete هيبقي @delete و الميثود هتاخد contact object عشان تمسحه ";
    @Insert
    void insertAll(Contact... contacts);

    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE id = :id ")
    Contact getContact(int id);


    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
