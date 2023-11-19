// luong.java
package edu.poly.qlns.chucnang;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.LuongAdapter;
import edu.poly.qlns.R;
import edu.poly.qlns.data.Luong;

public class luong extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private edu.poly.qlns.data.Luong luong;
    private ListView listView;
    private LuongAdapter adapter;
    private Spinner spinnerThang, spinnerPhongBan;
    private List<Luong> luongDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luong);

        // Khởi tạo DatabaseHelper và Luong object
        databaseHelper = new DatabaseHelper(this);
        luong = new edu.poly.qlns.data.Luong(databaseHelper);

        // Ánh xạ view từ layout
        spinnerThang = findViewById(R.id.spinnerThang);
        spinnerPhongBan = findViewById(R.id.spinnerPhongBan);
        listView = findViewById(R.id.listView);

        // Thiết lập spinner và xử lý sự kiện
        setupSpinners();
    }

    private void setupSpinners() {
        // Thiết lập ArrayAdapter cho Spinner tháng
        ArrayAdapter<CharSequence> thangAdapter = ArrayAdapter.createFromResource(
                this, R.array.thang_array, android.R.layout.simple_spinner_item);
        thangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThang.setAdapter(thangAdapter);

        // Thiết lập ArrayAdapter cho Spinner phòng ban từ cơ sở dữ liệu
        ArrayAdapter<String> phongBanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                databaseHelper.getAllDepartments());
        phongBanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPhongBan.setAdapter(phongBanAdapter);

        // Xử lý sự kiện khi lựa chọn từ Spinner tháng
        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                filterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Khi không có mục nào được chọn trong Spinner tháng
                // Có thể thực hiện các hành động cần thiết ở đây
            }
        });

        // Xử lý sự kiện khi lựa chọn từ Spinner phòng ban
        spinnerPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                filterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Khi không có mục nào được chọn trong Spinner phòng ban
                // Có thể thực hiện các hành động cần thiết ở đây
            }
        });
    }

    // Phương thức lọc dữ liệu dựa trên lựa chọn từ Spinner
    private void filterData() {
        int selectedMonth = spinnerThang.getSelectedItemPosition() + 1;
        String selectedDepartment = spinnerPhongBan.getSelectedItem().toString();

        List<Luong> filteredData = databaseHelper.getDataByMonthAndDepartment(selectedMonth, selectedDepartment);

        Log.d("SpinnerDebug", "Tháng được chọn: " + selectedMonth + ", Phòng ban được chọn: " + selectedDepartment);

        if (filteredData.isEmpty()) {
            // Xử lý khi danh sách rỗng
            Toast.makeText(this, "Không có dữ liệu để hiển thị", Toast.LENGTH_SHORT).show();
        } else {
            Luong luongObj = new Luong(databaseHelper);
            filteredData = luongObj.tinhLuongNhanVien(filteredData); // Tính toán thu nhập thực lãnh và cập nhật vào danh sách filteredData

            if (adapter == null) {
                adapter = new LuongAdapter(this, R.layout.item_luong, filteredData);
                listView.setAdapter(adapter);
            } else {
                adapter.clear();
                adapter.addAll(filteredData);
                adapter.notifyDataSetChanged();
            }
        }
    }

}

