package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Spinner spinner;
    EditText editText;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         tv = findViewById(R.id.textView);
         spinner = findViewById(R.id.parcialSpinner);
         editText = findViewById(R.id.parcialEditText);

         btn1 = findViewById(R.id.btn1);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.Opciones,android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        btn1.setOnClickListener(v -> {
            Task task = new Task();
            task.execute();
        });



    }

     class Task extends AsyncTask<Void, Void, List<String>>{

        ArrayList<String> list;
        String textv;
        String editt;
        String sp;




        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textv = MainActivity.this.tv.getText().toString();
            editt = editText.getText().toString();
            sp = spinner.getSelectedItem().toString();
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            list = new ArrayList<>();
            list.add(textv);
            list.add(editt);
            list.add(sp);
            return list;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            prepararNotificacion(list);
        }
    }

    public void prepararNotificacion(ArrayList<String> list ){

        String action = "parcial";
        Intent intent = new Intent(this,Receiver.class);
        intent.setAction(action);
        intent.putStringArrayListExtra("list",list);
        sendBroadcast(intent);
    }
}