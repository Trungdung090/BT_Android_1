package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText soPhong, loaiPhong, soGio;
    AppCompatButton tinh,chuyen;
    TextView ketqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();

        tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinhToan tien = new TinhToan();
                tien.setLoaiPhong(loaiPhong.getText().toString());
                tien.setSoGio(Float.parseFloat(soGio.getText().toString()));
                ketqua.setText("Tổng tiền phòng "+soPhong.getText().toString()+": "+tien.tinhtoan()+"VND");
            }
        });
        chuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                startActivity(intent);
            }
        });
    }
    private void anhXa(){
        soPhong = findViewById(R.id.sophong);
        loaiPhong = findViewById(R.id.loaiphong);
        soGio = findViewById(R.id.sogio);
        tinh = findViewById(R.id.tinh);
        chuyen = findViewById(R.id.chuyen);
        ketqua = findViewById(R.id.ketqua);
    }
}
