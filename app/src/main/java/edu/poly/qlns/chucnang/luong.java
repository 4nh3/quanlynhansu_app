package edu.poly.qlns.chucnang;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.R;

public class luong extends AppCompatActivity {

    private ListView listView;
    private Spinner spinnerThang, spinnerPhongBan;
    private List<String> phongBanList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luong);

        // Ánh xạ view từ layout
        spinnerThang = findViewById(R.id.spinnerThang);
        spinnerPhongBan = findViewById(R.id.spinnerPhongBan);
        listView = findViewById(R.id.listView);

        // Khởi tạo DatabaseHelper và load danh sách phòng ban
        databaseHelper = new DatabaseHelper(this);
        loadPhongBanToSpinner();
        loadThangToSpinner();

        // Đặt sự kiện khi chọn tháng
        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loadChamCongByThangAndPhongBan(position, spinnerPhongBan.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        // Đặt sự kiện khi chọn phòng ban
        spinnerPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loadChamCongByThangAndPhongBan(spinnerThang.getSelectedItemPosition(), spinnerPhongBan.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    private void loadThangToSpinner() {
        String[] thangArray = new String[]{"Tất cả", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
        ArrayAdapter<String> thangAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, thangArray);
        thangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThang.setAdapter(thangAdapter);
    }

    // Thêm phương thức loadPhongBanToSpinner để load danh sách phòng ban vào Spinner Phòng Ban
    private void loadPhongBanToSpinner() {
        // Khởi tạo phongBanList nếu chưa được khởi tạo
        if (phongBanList == null) {
            phongBanList = new ArrayList<>();
        }

        // Xóa các phần tử cũ trước khi thêm mới để tránh trùng lặp
        phongBanList.clear();

        // Thêm giá trị "All" vào danh sách
        phongBanList.add("Tất cả");

        // Thêm danh sách phòng ban từ database vào phongBanList
        phongBanList.addAll(databaseHelper.getAllPhongBanNames());

        ArrayAdapter<String> phongBanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phongBanList);
        phongBanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPhongBan.setAdapter(phongBanAdapter);
    }

    // Phương thức load dữ liệu Chấm Công từ database và hiển thị lên ListView
    private void loadChamCongByThangAndPhongBan(int selectedThang, String selectedPhongBan) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query;


            if ("Tất cả".equals(selectedPhongBan) && selectedThang == 0) {
                query = "SELECT ChamCong.manv AS _id, " +
                        "NhanVien.tennv, " +
                        "NhanVien.chucvu, " +
                        "NhanVien.luongcb, " +
                        "SUM(NhanVien.luongcb * 2 / 26) AS tongluong " +
                        "FROM ChamCong " +
                        "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                        "INNER JOIN PhongBan ON NhanVien.maphongban = PhongBan.mapb " +
                        "GROUP BY ChamCong.manv, NhanVien.tennv, NhanVien.chucvu, NhanVien.luongcb;";
            } else if ("Tất cả".equals(selectedPhongBan)) {
                query = "SELECT ChamCong.manv AS _id, NhanVien.tennv, NhanVien.chucvu, NhanVien.luongcb, " +
                        "SUM(NhanVien.luongcb * 2 / 26) AS tongluong " +
                        "FROM ChamCong " +
                        "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                        "WHERE (ChamCong.thang = ? OR ? = 0) " +
                        "GROUP BY ChamCong.manv, NhanVien.tennv, NhanVien.chucvu, NhanVien.luongcb;";
            } else {
                query = "SELECT ChamCong.manv AS _id, NhanVien.tennv, NhanVien.chucvu, NhanVien.luongcb, " +
                        "SUM(NhanVien.luongcb * 2 / 26) AS tongluong " +
                        "FROM ChamCong " +
                        "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                        "INNER JOIN PhongBan ON NhanVien.maphongban = PhongBan.mapb " +
                        "WHERE (ChamCong.thang = ? OR ? = 0) AND PhongBan.tenpb = ? " +
                        "GROUP BY ChamCong.manv, NhanVien.tennv, NhanVien.chucvu, NhanVien.luongcb;";
            }



            String[] selectionArgs;

            // Adjust the number of placeholders in the query based on the conditions
            if ("Tất cả".equals(selectedPhongBan) && selectedThang == 0) {
                selectionArgs = new String[]{};
            } else if ("Tất cả".equals(selectedPhongBan)) {
                selectionArgs = new String[]{String.valueOf(selectedThang), String.valueOf(selectedThang)};
            } else {
                selectionArgs = new String[]{String.valueOf(selectedThang), String.valueOf(selectedThang), selectedPhongBan};
            }

            cursor = db.rawQuery(query, selectionArgs);

            // LoadChamCongByThangAndPhongBan method
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.item_hienthiluong,
                    cursor,
                    new String[]{"tennv", "chucvu", "luongcb", "tongluong"},

                    new int[]{R.id.textViewHoTen, R.id.textViewChucVu, R.id.textViewLuongCoBan, R.id.textViewThucLanh}
            );


            Log.d("LuongActivity", "Số lượng dòng trong Cursor: " + cursor.getCount());

            listView.setAdapter(adapter);


            // Gọi hàm tính lương và hiển thị kết quả
            tinhVaHienThiLuong(selectedThang, selectedPhongBan);
        } finally {
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//            }
        }
    }

    // Phương thức tính và hiển thị lương
    private void tinhVaHienThiLuong(int selectedThang, String selectedPhongBan) {
        // Thực hiện tính lương theo logic bạn đã định nghĩa
        double luong = tinhLuong(selectedThang, selectedPhongBan);

        // Tham chiếu đến TextView có ID là textView_tongtatcaluong
        TextView textViewLuong = findViewById(R.id.textView_tongtatcaluong);

        // Kiểm tra xem textViewLuong có tồn tại không
        if (textViewLuong != null) {
            // Hiển thị kết quả tính lương trong TextView
            if (selectedThang == 0){
                textViewLuong.setText("Lương của tất cả tháng và  "  + selectedPhongBan + "phòng ban là: " + luong);

            }else {
                textViewLuong.setText("Lương tháng " + selectedThang + " của  " + selectedPhongBan + "phòng ban là: " + luong);
            }
        } else {
            // Nếu textViewLuong không tồn tại, bạn có thể thông báo hoặc xử lý theo cách khác
            Toast.makeText(this, "Không tìm thấy TextView_tongtatcaluong", Toast.LENGTH_SHORT).show();
        }
    }


    // Phương thức tính lương
    private double tinhLuong(int selectedThang, String selectedPhongBan) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        double luongCB = 0;

        try {
            // Tính tổng lương theo logic bạn đã định nghĩa
            String query;

            if ("Tất cả".equals(selectedPhongBan) && selectedThang == 0) {
                query = "SELECT SUM(NhanVien.luongcb * (ChamCong.ngaycong + ChamCong.ngoaigio * 2 - ChamCong.ngayphep) / 26) AS tongluong " +
                        "FROM ChamCong " +
                        "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv";
            } else if ("Tất cả".equals(selectedPhongBan)) {
                query = "SELECT SUM(NhanVien.luongcb * (ChamCong.ngaycong + ChamCong.ngoaigio * 2 - ChamCong.ngayphep) / 26) AS tongluong " +
                        "FROM ChamCong " +
                        "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                        "WHERE (ChamCong.thang = ? OR ? = 0)";
            } else {
                query = "SELECT SUM(NhanVien.luongcb * (ChamCong.ngaycong + ChamCong.ngoaigio * 2 - ChamCong.ngayphep) / 26) AS tongluong " +
                        "FROM ChamCong " +
                        "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                        "INNER JOIN PhongBan ON NhanVien.maphongban = PhongBan.mapb " +
                        "WHERE (ChamCong.thang = ? OR ? = 0) AND PhongBan.tenpb = ?";
            }

            String[] selectionArgs;

            if ("Tất cả".equals(selectedPhongBan) && selectedThang == 0) {
                selectionArgs = new String[]{};
            } else if ("Tất cả".equals(selectedPhongBan)) {
                selectionArgs = new String[]{String.valueOf(selectedThang), String.valueOf(selectedThang)};
            } else {
                selectionArgs = new String[]{String.valueOf(selectedThang), String.valueOf(selectedThang), selectedPhongBan};
            }

            cursor = db.rawQuery(query, selectionArgs);

            if (cursor.moveToFirst()) {
                luongCB = cursor.getDouble(cursor.getColumnIndex("tongluong"));
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return luongCB;
    }


    // Các phương thức khác nếu cần
}
