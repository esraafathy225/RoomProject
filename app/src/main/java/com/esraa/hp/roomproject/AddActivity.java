package com.esraa.hp.roomproject;

import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
EditText editName,editPhone;
Button add;
String name,phone;

String comment="هنعمل AsyncTask و انادى على الميثود اللى بتعمل create جواه بعد كدة اعمله execute جوة ال onCreate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editName=findViewById(R.id.editname);
        editPhone=findViewById(R.id.editphone);
        add=findViewById(R.id.btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=editName.getText().toString();
                phone=editPhone.getText().toString();

                new SaveTask().execute();
            }
        });

    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Contact contact=new Contact();
            contact.setName(name);
            contact.setPhone(phone);

            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());
                    databaseClient.getAppDatabase().databaseDao()
                    .insertAll(contact);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        }
    }


}
