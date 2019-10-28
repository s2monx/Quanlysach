package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemtacgiaActi extends AppCompatActivity {
    Button btnLuutacgia, btnXoatrang;
    EditText etMatacgia, etTentacgia, etHotacgia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themtacgia);
        anhXa();
        final Intent i = getIntent();
        btnLuutacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("matacgia", etMatacgia.getText().toString());
                bundle.putString("tentacgia", etTentacgia.getText().toString());
                bundle.putString("hotacgia", etHotacgia.getText().toString());
                i.putExtra("DATA_TACGIA", bundle);
                setResult(MainActivity.SEND_DATA_FROM_TACGIA_ACTIVITY, i);
                ThemtacgiaActi.this.finish();
            }
        });
    }

    private void anhXa() {
        btnLuutacgia = findViewById(R.id.btnLuutacgia);
        btnXoatrang = findViewById(R.id.btnXoatrang);
        etMatacgia = findViewById(R.id.etMatacgia);
        etTentacgia = findViewById(R.id.etTentacgia);
        etHotacgia = findViewById(R.id.etHotacgia);
    }
}
