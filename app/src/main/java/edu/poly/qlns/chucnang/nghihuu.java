package edu.poly.qlns.chucnang;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.PhongBanAdapter;
import edu.poly.qlns.R;

public class nghihuu extends Activity {

    private ListView listView;
    private List<String> employees;
    private PhongBanAdapter adapter;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private EditText yearEditText;
    private LinearLayout btnNhanVien;
    private LinearLayout btnPhongBan;

    private LinearLayout btnNghiHuu;
    private LinearLayout btnChamCong;
    private LinearLayout btnLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nghihuu);

        listView = findViewById(R.id.listView);
        employees = new ArrayList<>();
        adapter = new PhongBanAdapter(this, R.layout.item_nghihuu, employees);
        listView.setAdapter(adapter);
        dbHelper = new DatabaseHelper(this);
        yearEditText = findViewById(R.id.editText); // Thêm EditText để nhập năm
        loadRetiredEmployees(); // Tự động hiển thị nhân viên nghỉ hưu khi mở màn hình
        btnNhanVien = findViewById(R.id.nhanvien);
        btnPhongBan = findViewById(R.id.trangchu);
        btnNghiHuu = findViewById(R.id.nghihuu);
        btnChamCong = findViewById(R.id.chamcong);
        btnLuong = findViewById(R.id.bangluong);
        // Đặt lệnh lắng nghe sự kiện click cho button "NHÂN VIÊN"
        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(nghihuu.this, nhanvien.class);
                startActivity(intent);
            }
        });
        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(nghihuu.this, phongban.class);
                startActivity(intent);
            }
        });
        btnNghiHuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(nghihuu.this, nghihuu.class);
                startActivity(intent);
            }
        });
        btnChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(nghihuu.this, HienThiChamCong.class);
                startActivity(intent);
            }
        });
        btnLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(nghihuu.this, luong.class);
                startActivity(intent);
            }
        });
    }

    public void loadRetiredEmployees() {
        // Sử dụng dbHelper để truy cập cơ sở dữ liệu
        db = dbHelper.getReadableDatabase();

        // Xử lý dữ liệu từ cơ sở dữ liệu
        String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String yearFilter = yearEditText.getText().toString().trim();

        // Nếu người dùng nhập năm, sử dụng năm đó, ngược lại sử dụng năm hiện tại
        if (!yearFilter.isEmpty()) {
            currentYear = yearFilter;
        }

        // Thay đổi điều kiện WHERE để chỉ lấy những nhân viên đã đủ tuổi nghỉ hưu
        String query = "SELECT manv, tennv, phaitinh, ngaysinh FROM NhanVien " +
                "WHERE CAST(SUBSTR(ngaysinh, 1, 4) AS INTEGER) <= ? - CASE WHEN phaitinh = 'Nam' THEN 60 ELSE 50 END";

        Cursor cursor = db.rawQuery(query, new String[]{currentYear});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                employees.clear(); // Xóa danh sách nhân viên trước để cập nhật danh sách mới
                int sttCounter = 1; // Biến đếm cho cột STT, bắt đầu từ 1

                do {
                    // Lấy dữ liệu từ cơ sở dữ liệu
                    String maNV = cursor.getString(cursor.getColumnIndex("manv"));
                    String hoTenNV = cursor.getString(cursor.getColumnIndex("tennv"));
                    String phaiTinh = cursor.getString(cursor.getColumnIndex("phaitinh"));
                    String ngaySinh = cursor.getString(cursor.getColumnIndex("ngaysinh"));

                    // Tạo giá trị cho cột STT
                    String stt = String.valueOf(sttCounter);

                    // Tăng biến đếm cho lần lặp tiếp theo
                    sttCounter++;

                    // Tạo chuỗi item và thêm vào danh sách nhân viên
                    String item = stt + ";" + maNV + ";" + hoTenNV + ";" + phaiTinh + ";" + ngaySinh;
                    employees.add(item);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        adapter.notifyDataSetChanged(); // Cập nhật ListView với danh sách nhân viên mới

        db.close(); // Đảm bảo đóng cơ sở dữ liệu sau khi sử dụng
    }

    public void onButtonClick(View view) {
        // Xử lý khi nút được nhấn
        loadRetiredEmployees(); // Hoặc bất kỳ mã lệnh nào bạn muốn thực hiện khi nút được nhấn
    }
}
