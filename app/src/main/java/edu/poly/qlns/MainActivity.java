package edu.poly.qlns;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        // Chèn dữ liệu mẫu vào bảng Nguoidung
        dbHelper.insertSampleUserData("user1", "password1");
        dbHelper.insertSampleUserData("user2", "password2");
    }

    private void insertSampleData() {
        ContentValues values = new ContentValues();

        // Đưa dữ liệu mẫu vào bảng Nhân viên
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
