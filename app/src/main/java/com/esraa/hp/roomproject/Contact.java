package com.esraa.hp.roomproject;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

@Entity
public class Contact {

    String comment="هنا هنبدأ نعرف ال table بتاعنا بمجرد ما نعمل الكلاس و نحط قبله ال annotation @Entity " +
            "كدة دة بقي table خلاص " +
            "و بعد كدة هعرف ال column كل variable هحط قبله @ColumnInfo و هعمل ال getters و ال setters";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "phone")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
