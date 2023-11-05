package edu.poly.qlns.chucnang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.EmployeeAdapter;
import edu.poly.qlns.NhanVienAdapter;
import edu.poly.qlns.R;
import edu.poly.qlns.data.NhanVien;
import edu.poly.qlns.themsuaxoa.suanv;
import edu.poly.qlns.themsuaxoa.themsv;

public class nhanvien extends AppCompatActivity {

    private ListView listView;
    private NhanVienAdapter nhanVienAdapter;
    private EmployeeAdapter employeeAdapter; // Sử dụng biến riêng cho in theo phòng ban
    private TextView themNV;
    private DatabaseHelper databaseHelper;
    private String selectedPhongBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhanvien);

        listView = findViewById(R.id.NhanVien);
        themNV = findViewById(R.id.tthemNV);
        databaseHelper = new DatabaseHelper(this);

        themNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(nhanvien.this, themsv.class);
                startActivity(intent);
            }
        });

        selectedPhongBan = getIntent().getStringExtra("selectedPhongBan");

        if (selectedPhongBan != null) {
            // Nếu selectedPhongBan không null, chuyển sang chế độ in theo phòng ban
            loadNhanVienByPhongBan();
        } else {
            // Nếu không có selectedPhongBan, ở chế độ in tất cả nhân viên
            loadAllNhanVien();
        }
    }

    private void loadAllNhanVien() {
        List<NhanVien> nhanVienList = databaseHelper.getAllNhanVien();
        nhanVienAdapter = new NhanVienAdapter(this, nhanVienList, databaseHelper);
        listView.setAdapter(nhanVienAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien selectedNhanVien = nhanVienList.get(position);
                Intent intent = new Intent(nhanvien.this, suanv.class);
                intent.putExtra("edit_manv", selectedNhanVien.getMaNhanVien());
                intent.putExtra("edit_tennv", selectedNhanVien.getTenNhanVien());
                startActivity(intent);
            }
        });
    }

    private void loadNhanVienByPhongBan() {
        List<DatabaseHelper.Employee> employeeList = databaseHelper.getNhanVienByPhongBan(selectedPhongBan);

        if (employeeList != null && !employeeList.isEmpty()) {
            EmployeeAdapter adapter = new EmployeeAdapter(this, employeeList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DatabaseHelper.Employee selectedNhanVien = employeeList.get(position);
                    Intent intent = new Intent(nhanvien.this, suanv.class);
                    intent.putExtra("edit_manv", selectedNhanVien.getMaNhanVien());
                    intent.putExtra("edit_tennv", selectedNhanVien.getTenNhanVien());
                    startActivity(intent);
                }
            });
        } else {
            // Xử lý trường hợp không có dữ liệu trong Cursor, ví dụ thông báo hoặc hiển thị thông tin khác.
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (selectedPhongBan != null) {
            // Nếu ở chế độ in theo phòng ban
            loadNhanVienByPhongBan();
        } else {
            // Nếu không có selectedPhongBan, ở chế độ in tất cả nhân viên
            loadAllNhanVien();
        }
    }
}
