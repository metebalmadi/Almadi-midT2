package com.example.almadi_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Act2 extends AppCompatActivity {


    DatabaseHelper myDB;
    EditText id,name, surname, nationalID;


    String pID;
    String pname;
    String psurname;
    String pNationalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        Button bttnAdd = (Button) findViewById(R.id.bttnAdd);

        Button bttnToAct1 = (Button) findViewById(R.id.bttnToAct1);
        Button bttnToAct3 = (Button) findViewById(R.id.bttnToAct3);

        bttnToAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Act2.this, Act1.class));
            }
        });

        bttnToAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Act2.this, Act3.class));
            }
        });

        myDB = new DatabaseHelper(this);

        id = (EditText) findViewById(R.id.ETID);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        nationalID = (EditText) findViewById(R.id.nationalID);


        bttnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pID = id.getText().toString();
                pname = name.getText().toString();
                psurname = surname.getText().toString();
                pNationalID = nationalID.getText().toString();


                if (pID.equals("") || pname.equals("") || psurname.equals("") || pNationalID.equals(""))
                {
                    Toasty.error(getBaseContext(), "Fields are empty", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                if (!myDB.addData(pID, pname, psurname, pNationalID))
                    Toasty.error(getBaseContext(), "Insert Failed", Toast.LENGTH_SHORT, true).show();
                else
                    Toasty.success(getBaseContext(), "Insert Success", Toast.LENGTH_SHORT, true).show();


            }
        });

    }
}