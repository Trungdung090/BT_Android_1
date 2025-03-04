package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity1 extends AppCompatActivity {
    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Khởi tạo WebView
        webView = findViewById(R.id.webView);

        // Cấu hình WebView
        WebSettings webSettings = webView.getSettings();

        webView.getSettings().setJavaScriptEnabled(true); // Bật JavaScript (nếu cần)
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false); // Cho phép tự động phát video
        webView.getSettings().setDomStorageEnabled(true); // Bật DOM storage (cần thiết cho nhiều nội dung video)

        webView.setWebChromeClient(new WebChromeClient() {
            // Xử lý fullscreen video (nếu cần)
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
                // Ví dụ: thiết lập chế độ toàn màn hình
            }
        });

        // Thêm JavaScript Interface
        webView.addJavascriptInterface(this, "Android");

        // Load file HTML từ thư mục assets
        webView.loadUrl("file:///android_asset/index.html");
    }

    @android.webkit.JavascriptInterface
    public void xuly(String name, String hours){
        float housr_fl = Float.parseFloat(hours);
        TinhToan tien = new TinhToan();
        tien.setLoaiPhong(name);
        tien.setSoGio(housr_fl);
        double kq = tien.tinhtoan();
        runOnUiThread(() -> webView.evaluateJavascript(
                "NhanKQ('" + kq + "');", null
        ));
    }
}