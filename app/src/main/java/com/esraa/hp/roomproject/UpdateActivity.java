package com.esraa.hp.roomproject;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    Button button;
    int id;
    String comment="اول AsyncTask اللى بيقرا contact واحد بال id بتاعه و يحطه في ال edittext عشان ابدأ اعدل فيه " +
            "و التانى بيعمل update و التالت بيعمل delete " +
            " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edit1=findViewById(R.id.editname);
        edit2=findViewById(R.id.editphone);
        button=findViewById(R.id.btn);
        id=getIntent().getIntExtra("id",0);

        new GetContact().execute();



       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit1.getText().toString();
                String phone= edit2.getText().toString();
                Contact newContact=new Contact();
                newContact.setName(name);
                newContact.setPhone(phone);
                newContact.setId(id);
                new UpdateContact().execute(newContact);
            }
        });


    }

    class GetContact extends AsyncTask<Void, Void, Contact> {

        @Override
        protected Contact doInBackground(Void... voids) {

            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());

            Contact contact=databaseClient.getAppDatabase().databaseDao().getContact(id);
           return contact;
        }

        @Override
        protected void onPostExecute(Contact contact) {
            super.onPostExecute(contact);

            edit1.setText(contact.getName());
            edit2.setText(contact.getPhone());

        }
    }
    class UpdateContact extends AsyncTask<Contact, Void, Void> {

        @Override
        protected Void doInBackground(Contact... contact) {

            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());

            databaseClient.getAppDatabase().databaseDao().update(contact[0]);
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }
    class DeleteContact extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            DatabaseClient databaseClient=new DatabaseClient(getApplicationContext());

            databaseClient.getAppDatabase().databaseDao()
                    .delete(databaseClient.getAppDatabase().databaseDao().getContact(id));
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                showAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteContact().execute();
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
