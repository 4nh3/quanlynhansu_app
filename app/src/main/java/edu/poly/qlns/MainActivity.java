package edu.poly.qlns;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.R;
import edu.poly.qlns.chucnang.HienThiChamCong;
import edu.poly.qlns.chucnang.chamcong;
import edu.poly.qlns.chucnang.nghihuu;
import edu.poly.qlns.chucnang.nhanvien;
import edu.poly.qlns.chucnang.phongban;
import edu.poly.qlns.data.NhanVien;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Button btnNhanVien;
    private Button btnPhongBan;

    private Button btnNghiHuu;
    private Button btnChamCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        // Chèn dữ liệu mẫu vào bảng Nguoidung
        dbHelper.insertSampleUserData("user1", "password1");
        dbHelper.insertSampleUserData("user2", "password2");
        dbHelper.insertSampleData();

        btnNhanVien = findViewById(R.id.btnNV); // Tìm button "NHÂN VIÊN" bằng ID
        btnPhongBan = findViewById(R.id.btnPB); // Tìm button "NHÂN VIÊN" bằng ID
        btnNghiHuu = findViewById(R.id.btnNH); // Tìm button "NHÂN VIÊN" bằng ID
        btnChamCong = findViewById(R.id.btnCC); // Tìm button "NHÂN VIÊN" bằng ID

        // Đặt lệnh lắng nghe sự kiện click cho button "NHÂN VIÊN"
        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(MainActivity.this, nhanvien.class);
                startActivity(intent);
            }
        });
        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(MainActivity.this, phongban.class);
                startActivity(intent);
            }
        });
        btnNghiHuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(MainActivity.this, nghihuu.class);
                startActivity(intent);
            }
        });
        btnChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(MainActivity.this, HienThiChamCong.class);
                startActivity(intent);
            }
        });
    }
}
