package com.mustafagur.a12yilsurenproje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listActivity);
        db = openOrCreateDatabase("veritabani", MODE_PRIVATE, null);  // Veritabanı eğer yoksa oluşturuldu ve açıldı. Varsa açıldı.
        db.execSQL("CREATE TABLE IF NOT EXISTS veriler (id INTEGER PRIMARY KEY," +
                "kullaniciadi TEXT," +
                "telno TEXT)");
        listele();
    }

    private void listele() {
        ArrayList veriler = new ArrayList<>();  // veriler ismminde arraylsit tanımladık.
        Cursor cursor = db.rawQuery("SELECT * FROM veriler",null);  // cursor ile sorgumuzu oluşturduk
        if (cursor != null && cursor.moveToFirst()) {  // eğer boş veri gelmiyorsa veya null değeri döndürmüyorsa
            do {
                @SuppressLint("Range") String kadi = cursor.getString(cursor.getColumnIndex("kullaniciadi"));
                @SuppressLint("Range") String telno = cursor.getString(cursor.getColumnIndex("telno"));// veritabanından verilerimizi çekip değişkenlere eşitledik.
                veriler.add(new DataClass(kadi,telno));  // tanımladığımız Arraylist in içine VerilerClass sınıfı aracılığıyla tüm verileri ekledik. Böylece kolaylıkla hem adapter hemde VerilerClass ı kullanabileceğiz

            } while (cursor.moveToNext()); // veriler bitene kadar döngü devam eder. Veritabanında veri bitince döngü false değeri alır ve durur.
        }
        cursor.close();  // cursor umuzu kapattık.
        DataAdapter veriAdapterClass = new DataAdapter(veriler,this);  // VeriAdapterClass ımızı çağırdık ve tüm verilerimizi o sınıfa Constractor aracılığıyla gönderdik Böylelikle hem verileri hemde hangi layout üzerinden göstereceksek onu belirttik.
        veriAdapterClass.notifyDataSetChanged();  // Adapter 'ımız üzerinde herhangi bir değişiklik olduysa (yeni veri veya var olan veriyi güncelleme gibi.) o işlemi tespit edip uygulamak için çağırılan method
        listview.setAdapter(veriAdapterClass);
    }

    public void veriEkle(View view) {
        Intent i = new Intent(this,VeriEkleme.class);
        startActivity(i);
    }
}