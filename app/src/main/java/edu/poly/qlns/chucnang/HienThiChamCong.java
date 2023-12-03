package edu.poly.qlns.chucnang;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.MainActivity;
import edu.poly.qlns.R;

public class HienThiChamCong extends AppCompatActivity {
    private Spinner spinnerPhongBan;
    private List<String> phongBanList;
    private ListView listViewChamCong;
    private Spinner spinnerThang;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private LinearLayout btnNhanVien;
    private LinearLayout btnPhongBan;

    private LinearLayout btnNghiHuu;
    private LinearLayout btnChamCong;
    private LinearLayout btnLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hienthichamcong);

        listViewChamCong = findViewById(R.id.listView_HienThiChamCong);
        spinnerThang = findViewById(R.id.spinnerThang_HienThi);
        databaseHelper = new DatabaseHelper(this);
        Button btnThemChamCong = findViewById(R.id.btnThemChamCong);
        spinnerPhongBan = findViewById(R.id.spinnerPhongBan);
        loadThangToSpinner();
        loadPhongBanToSpinner();
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
                Intent intent = new Intent(HienThiChamCong.this, nhanvien.class);
                startActivity(intent);
            }
        });
        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(HienThiChamCong.this, phongban.class);
                startActivity(intent);
            }
        });
        btnNghiHuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(HienThiChamCong.this, nghihuu.class);
                startActivity(intent);
            }
        });
        btnChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(HienThiChamCong.this, HienThiChamCong.class);
                startActivity(intent);
            }
        });
        btnLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(HienThiChamCong.this, luong.class);
                startActivity(intent);
            }
        });

        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loadChamCongByThangAndPhongBan(
                        position, // Sử dụng vị trí của item thay vì giá trị
                        spinnerPhongBan.getSelectedItem().toString()
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        spinnerPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                loadChamCongByThangAndPhongBan(
                        spinnerThang.getSelectedItemPosition(), // Sử dụng vị trí của item thay vì giá trị
                        spinnerPhongBan.getSelectedItem().toString()
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        btnThemChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HienThiChamCong.this, chamcong.class);
                startActivity(intent);
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


    private void loadChamCongByThangAndPhongBan(int selectedThang, String selectedPhongBan) {
        String query;

        if ("Tất cả".equals(selectedPhongBan) && selectedThang == 0) {
            query = "SELECT ChamCong.manv AS _id, NhanVien.tennv, ChamCong.ngaycong, ChamCong.ngayphep, ChamCong.ngoaigio " +
                    "FROM ChamCong " +
                    "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                    "INNER JOIN PhongBan ON NhanVien.maphongban = PhongBan.mapb";
        } else if ("Tất cả".equals(selectedPhongBan)) {
            query = "SELECT ChamCong.manv AS _id, NhanVien.tennv, ChamCong.ngaycong, ChamCong.ngayphep, ChamCong.ngoaigio " +
                    "FROM ChamCong " +
                    "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                    "WHERE (ChamCong.thang = ? OR ? = 0)";
        } else {
            query = "SELECT ChamCong.manv AS _id, NhanVien.tennv, ChamCong.ngaycong, ChamCong.ngayphep, ChamCong.ngoaigio " +
                    "FROM ChamCong " +
                    "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                    "INNER JOIN PhongBan ON NhanVien.maphongban = PhongBan.mapb " +
                    "WHERE (ChamCong.thang = ? OR ? = 0) AND PhongBan.tenpb = ?";
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

        cursor = databaseHelper.getWritableDatabase().rawQuery(query, selectionArgs);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_hienthichamcong,
                cursor,
                new String[]{"tennv", "ngaycong", "ngayphep", "ngoaigio"},
                new int[]{R.id.textViewMaNV_HienThi, R.id.textViewNgayCong_HienThi, R.id.textViewNgayPhep_HienThi, R.id.textViewNgoaiGio_HienThi}
        );

        listViewChamCong.setAdapter(adapter);
    }

}
