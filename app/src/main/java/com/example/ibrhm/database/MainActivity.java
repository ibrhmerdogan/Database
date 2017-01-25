package com.example.ibrhm.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button,buton;
    EditText one,Two;
    databaseClass databaseClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView2);
        button=(Button)findViewById(R.id.button);
        buton=(Button)findViewById(R.id.button2);
        one=(EditText)findViewById(R.id.editText);
        Two=(EditText)findViewById(R.id.editText2);
        databaseClass=new databaseClass(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    KayitEkle(one.getText().toString(),Two.getText().toString());
                }catch (Exception e){textView.setText("hata"+e);}
                finally{
                    databaseClass.close();
                }

            }
        });
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    KayitGoster(KayitGetir());
                }catch (Exception e){textView.setText("hattta"+e);}
            }
        });
    }
    public void KayitEkle(String isim, String soyad){

        SQLiteDatabase db = databaseClass.getWritableDatabase();

        ContentValues veriler = new ContentValues();

        veriler.put("isim", isim);
        veriler.put("soyad",soyad);
        db.insertOrThrow("information", null, veriler);

    }
    public Cursor KayitGetir(){
        SQLiteDatabase db = databaseClass.getReadableDatabase();
        Cursor cursor = db.query("information", new String[]{"id", "isim", "soyad"}, null, null, null, null, null);
        return cursor;

    }
    public void KayitGoster(Cursor cursor){
        StringBuilder builder = new StringBuilder();

        while(cursor.moveToNext()){

            long id = cursor.getLong(cursor.getColumnIndex("id"));
            String ad = cursor.getString((cursor.getColumnIndex("isim")));
            String soyad = cursor.getString((cursor.getColumnIndex("soyad")));
            builder.append(id).append(" Adı: ");
            builder.append(ad).append(" Soyadı: ");
            builder.append(soyad).append("\n");
        }


        textView.setText(builder);
    }


}
