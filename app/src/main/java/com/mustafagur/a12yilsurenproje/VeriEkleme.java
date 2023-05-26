package com.mustafagur.a12yilsurenproje;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VeriEkleme extends AppCompatActivity {

    SQLiteDatabase db;

    EditText kadi, telno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veri_ekleme);
        tanimlamalar();
        db = openOrCreateDatabase("veritabani", MODE_PRIVATE, null);  // Veritabanı eğer yoksa oluşturuldu ve açıldı. Varsa açıldı.
        db.execSQL("CREATE TABLE IF NOT EXISTS veriler (id INTEGER PRIMARY KEY," +
                "kullaniciadi TEXT," +
                "telno TEXT)");
    }

    private void tanimlamalar() {
        kadi = findViewById(R.id.addKadi);
        telno = findViewById(R.id.addPhNo);
    }

    public void btnclick(View view) {
        String gelenkadi = kadi.getText().toString();
        String gelentel = telno.getText().toString();
        SQLiteStatement statement = db.compileStatement("INSERT INTO veriler (kullaniciadi,telno) VALUES (?,?)");
        statement.bindString(1, gelenkadi);
        statement.bindString(2, gelentel);
        statement.execute();
        Toast.makeText(this, "Veri başarıyla veri tabanına eklendi!", Toast.LENGTH_SHORT).show();
    }
}