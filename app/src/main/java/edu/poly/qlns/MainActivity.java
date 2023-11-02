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
import edu.poly.qlns.chucnang.nhanvien;
import edu.poly.qlns.data.NhanVien;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Button btnNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        // Chèn dữ liệu mẫu vào bảng Nguoidung
        dbHelper.insertSampleUserData("user1", "password1");
        dbHelper.insertSampleUserData("user2", "password2");

        btnNhanVien = findViewById(R.id.btnNV); // Tìm button "NHÂN VIÊN" bằng ID


        // Đặt lệnh lắng nghe sự kiện click cho button "NHÂN VIÊN"
        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(MainActivity.this, nhanvien.class);
                startActivity(intent);
            }
        });

    }

    private void insertSampleData() {
        ContentValues values = new ContentValues();

        // Đưa dữ liệu mẫu vào bảng Nhân viên
        values.put("manv", "001");
        values.put("tennv", "Nguyen Van A");
        values.put("ngaysinh", "1990-01-01");
        values.put("phaitinh", "Nam");
        values.put("cogiadinh", "Có gia đình");
        values.put("diachi", "123 Đường ABC");
        values.put("sodienthoai", "0123456789");
        values.put("trinhdo", "Cử nhân");
        values.put("luongcb", "10000000");
        values.put("ngaylamviec", "2023-10-01");
        values.put("chucvu", "Nhân viên");
        values.put("maphongban", "BGD");
        db.insert("NhanVien", null, values);

        // Đưa dữ liệu mẫu vào bảng Chấm công
        values.clear();
        values.put("manv", 1);
        values.put("thang", 10);
        values.put("ngaycong", "2023-10-01");
        values.put("ngayphep", 2);
        values.put("ngoaigio", 0);
        db.insert("ChamCong", null, values);

        // Đưa dữ liệu mẫu vào bảng Phòng ban
        values.clear();
        values.put("tenpb", "Phòng A");
        values.put("sdt", "0987654321");
        db.insert("PhongBan", null, values);
    }
}
