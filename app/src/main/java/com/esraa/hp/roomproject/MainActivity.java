package com.esraa.hp.roomproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;

    String comment="https://developer.android.com/training/data-storage/room   لينك شرحها " +
            "رووم هى مكتبة بديلة لل sqlite بتسهل التعامل شوية فمش هكون مضطر اعمل ال queries الكبيرة المعقد" +
            "اللى كنا بنعملها عشان ن create table احنا بس هنعمل شوية خطوات و هنكتب queries بسيطة خالص " +
            "هنحتاج الاول نزود ال library في ال gradle" +
            "1. اول خطوة هنعرف ال table و ال columns اللى جواه بس بطريقة اسهل بكتير من sqlite " +
            "الخطوة دى بنعملها في -->Contact class" +
            "2.بعد ما عرفنا ال table تانى خطوة انى اعمل hg interface اللى جواه ال methods" +
            " اللى هتعمل create,read,update,delete" +
            "دة اسمه DatabaseDAO و DAO اختصار ل Data Access Object" +
            "  3. هنعمل الكلاس اللى بعرف فيه اسم الكلاس اللى فيه ال table و اكيد ممكن يكون عندى اكتر من واحد " +
            "و اسم الكلاس اللى فيه ال methods و دة اسمه AppDatabase" +
            "4. اخر حاجة في database creation هنعمل build للداتابيز و دة هنعمله في DatabaseClient" +
            "بعد كدة هبدأ انفذ ال operations " +
            "اول حاجة لازم نعرفها اننا مينفعش نعمل اى حاجة من ال methods دى جوة ال main thread لازم في thread تانى " +
            "فهنستخدم AsyncTask كل مرة انادى على اى ميثود من جوة الداتابيز " +
            "اول حاجة ال create موجودة في ال add activity " +
            "تانى حاجة ال read هنعمله هنا في ال main activity اللى فيها ال list هنجيب contact list و نحطها في ال listview" +
            "برضو جوه AsyncTask و هنعمله excute جوة الonResume"+
            "و زى ما عملنا قبل كدة هنجيب ال id بتاع ال contact اللى عملنا عنده click و هنبعته لل update activity " +
            "عشان نعمل update w delete " +
            "شرح كل كلاس موجود جواه " ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= findViewById(R.id.list1);


        button= findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });

        new GetContacts().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selected_contact= (Contact) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("id", selected_contact.getId());
                startActivity(intent);
            }
        });

    }

        class GetContacts extends AsyncTask<Void, Void, List<Contact>> {

            @Override
            protected List<Contact> doInBackground(Void... voids) {
                DatabaseClient databaseClient = new DatabaseClient(getApplicationContext());


                List<Contact> contactList = databaseClient.getAppDatabase()
                        .databaseDao()
                        .getAll();
                return contactList;
            }

            @Override
            protected void onPostExecute(List<Contact> contacts) {
                super.onPostExecute(contacts);
                ContactAdapter adapter = new ContactAdapter(MainActivity.this, contacts);
                listView.setAdapter(adapter);
            }
        }

    @Override
    protected void onResume() {
        super.onResume();
               new GetContacts().execute();

    }
    }
