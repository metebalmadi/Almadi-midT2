package com.example.almadi_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Act3 extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act3);


        myDB = new DatabaseHelper(this);

        Button bttnToAct2 = (Button) findViewById(R.id.bttnToAct22);
        Button bttnView = (Button) findViewById(R.id.bttnView);
        Button bttnDelete = (Button) findViewById(R.id.bttnDelete);

        TextView ViewDB = (TextView) findViewById(R.id.textView7);

        EditText ET = (EditText) findViewById(R.id.editTextNumber);

        bttnToAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Act3.this, Act2.class));
            }
        });


        bttnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myDB.getListContents();
                StringBuffer buffer = new StringBuffer();

                while(cur.moveToNext()){
                    buffer.append("ID: "+cur.getString(0)+"\n");
                    buffer.append("Name: "+cur.getString(1)+"\n");
                    buffer.append("Email: "+cur.getString(2)+"\n");
                    buffer.append("Phone: "+cur.getString(3)+"\n\n");
                }
                ViewDB.setText(buffer.toString());
            }
        });


        bttnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ETID = ET.getText().toString();

                if (ETID.equals(""))
                {
                    Toasty.error(getBaseContext(), "Write something", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if (myDB.deleteData(ETID))
                {
                    Toasty.success(getBaseContext(), "Deleted", Toast.LENGTH_SHORT, true).show();
                    Log.d("Meteb", "Deleted");
                }else
                {
                    Toasty.error(getBaseContext(), "Could not Delete", Toast.LENGTH_SHORT, true).show();
                }

            }
        });

    }
}