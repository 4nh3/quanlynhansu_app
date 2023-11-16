package edu.poly.qlns.chucnang;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hienthichamcong);

        listViewChamCong = findViewById(R.id.listView_HienThiChamCong);
        spinnerThang = findViewById(R.id.spinnerThang_HienThi);
        databaseHelper = new DatabaseHelper(this);
        Button btnThemChamCong = findViewById(R.id.btnThemChamCong); // Tìm button "NHÂN VIÊN" bằng ID
        spinnerPhongBan = findViewById(R.id.spinnerPhongBan);
        loadThangToSpinner();
        loadPhongBanToSpinner();

        // Lắng nghe sự kiện khi giá trị của Spinner thay đổi
        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Load dữ liệu chấm công theo tháng và phòng ban được chọn
                loadChamCongByThangAndPhongBan(
                        (int) spinnerThang.getSelectedItem(),
                        spinnerPhongBan.getSelectedItem().toString()
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        // Lắng nghe sự kiện khi giá trị của Spinner Phòng Ban thay đổi
        spinnerPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Load dữ liệu chấm công theo tháng và phòng ban được chọn
                loadChamCongByThangAndPhongBan(
                        (int) spinnerThang.getSelectedItem(),
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
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(HienThiChamCong.this, chamcong.class);
                startActivity(intent);
            }
        });

    }

    private void loadThangToSpinner() {
        // Tạo một mảng dữ liệu cho Spinner (1 đến 12 là các tháng)
        Integer[] thangArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        // Tạo một ArrayAdapter để hiển thị dữ liệu trong Spinner
        ArrayAdapter<Integer> thangAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, thangArray);

        // Đặt kiểu dropdown cho Spinner
        thangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Đặt Adapter cho Spinner
        spinnerThang.setAdapter(thangAdapter);
    }

    // Thêm phương thức loadPhongBanToSpinner để load danh sách phòng ban vào Spinner Phòng Ban
    private void loadPhongBanToSpinner() {
        phongBanList = databaseHelper.getAllPhongBanNames();
        ArrayAdapter<String> phongBanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phongBanList);
        phongBanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPhongBan.setAdapter(phongBanAdapter);
    }

    // Thêm phương thức loadChamCongByThangAndPhongBan để lọc dữ liệu chấm công theo tháng và phòng ban
    private void loadChamCongByThangAndPhongBan(int selectedThang, String selectedPhongBan) {
        // Thực hiện lệnh SQL để lấy dữ liệu chấm công và thông tin nhân viên theo tháng và phòng ban
        String query = "SELECT ChamCong.manv AS _id, NhanVien.tennv, ChamCong.ngaycong, ChamCong.ngayphep, ChamCong.ngoaigio " +
                "FROM ChamCong " +
                "INNER JOIN NhanVien ON ChamCong.manv = NhanVien.manv " +
                "INNER JOIN PhongBan ON NhanVien.maphongban = PhongBan.mapb " +
                "WHERE ChamCong.thang = ? AND PhongBan.tenpb = ?";

        cursor = databaseHelper.getWritableDatabase().rawQuery(query, new String[]{String.valueOf(selectedThang), selectedPhongBan});

        // Tạo một adapter để hiển thị dữ liệu từ Cursor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_hienthichamcong,
                cursor,
                new String[]{"tennv", "ngaycong", "ngayphep", "ngoaigio"},
                new int[]{R.id.textViewMaNV_HienThi, R.id.textViewNgayCong_HienThi, R.id.textViewNgayPhep_HienThi, R.id.textViewNgoaiGio_HienThi}
        );

        // Thiết lập adapter cho ListView
        listViewChamCong.setAdapter(adapter);
    }


}

