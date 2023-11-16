package edu.poly.qlns.chucnang;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.R;

public class chamcong extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;
    private int selectedThang; // Declare selectedThang at the class level
    private  Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chamcong);

        listView = findViewById(R.id.listView_ChamCong);
        databaseHelper = new DatabaseHelper(this);

        loadManvAndTennv();

        // Lấy tham chiếu đến Button và Spinner
        Button btnLuuchamcong = findViewById(R.id.btn_luuchamcong);
        Spinner spinnerThang = findViewById(R.id.spinnerThang);

        // Tạo một mảng dữ liệu cho Spinner (1 đến 12 là các tháng)
        Integer[] thangArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        // Tạo một ArrayAdapter để hiển thị dữ liệu trong Spinner
        ArrayAdapter<Integer> thangAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, thangArray);

        // Đặt kiểu dropdown cho Spinner
        thangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Đặt Adapter cho Spinner
        spinnerThang.setAdapter(thangAdapter);
        databaseHelper = new DatabaseHelper(this);
        selectedThang = (int) spinnerThang.getSelectedItem();

        // Lắng nghe sự kiện khi nút được nhấn
        btnLuuchamcong.setOnClickListener(v -> {
            // Lấy giá trị tháng từ Spinner
            selectedThang = (int) spinnerThang.getSelectedItem();

            // Lấy dữ liệu từ EditText
            EditText editTextNgayCong = findViewById(R.id.editTextNgayCong);
            EditText editTextNgayPhep = findViewById(R.id.editTextNgayPhep);
            EditText editTextNgoaiGio = findViewById(R.id.editTextNgoaiGio);

            // Lấy giá trị từ các EditText
            int ngayCong = Integer.parseInt(editTextNgayCong.getText().toString());
            int ngayPhep = Integer.parseInt(editTextNgayPhep.getText().toString());
            int ngoaiGio = Integer.parseInt(editTextNgoaiGio.getText().toString());

            // Thêm dữ liệu vào bảng chamcong cho tất cả nhân viên
            if (cursor.moveToFirst()) {
                do {
                    int maNV = cursor.getInt(cursor.getColumnIndex("manv"));

                    // Thêm dữ liệu vào bảng chamcong
                    databaseHelper.addChamCong(maNV, selectedThang, ngayCong, ngayPhep, ngoaiGio);

                    // Hiển thị thông báo hoặc làm gì đó khi dữ liệu được thêm thành công
                    Toast.makeText(chamcong.this, "Đã thêm dữ liệu chấm công cho nhân viên có MaNV: " + maNV, Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }

            // Hiển thị thông báo hoặc làm gì đó khi dữ liệu được thêm thành công
            Toast.makeText(chamcong.this, "Đã thêm dữ liệu chấm công cho tất cả nhân viên", Toast.LENGTH_SHORT).show();
        });

    }

    private void loadManvAndTennv() {
        // Thực hiện lệnh SQL để lấy maNV và tenNV từ bảng NhanVien
         cursor = databaseHelper.getWritableDatabase().rawQuery("SELECT manv AS _id, manv, tennv FROM NhanVien", null);

        // Tạo một adapter để hiển thị dữ liệu từ Cursor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_chamcong, // Sử dụng layout có tên là item_chamcong
                cursor,
                new String[]{"manv", "tennv"},
                new int[]{R.id.textViewMaNV, R.id.textViewTenNV} // Map dữ liệu từ cột "manv" và "tennv" vào TextView tương ứng
        );

        // Thiết lập adapter cho ListView
        listView.setAdapter(adapter);


    }
}
