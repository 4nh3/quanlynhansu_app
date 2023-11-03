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
import edu.poly.qlns.NhanVienAdapter;
import edu.poly.qlns.R;
import edu.poly.qlns.data.NhanVien;
import edu.poly.qlns.themsuaxoa.suanv;
import edu.poly.qlns.themsuaxoa.themsv;

public class nhanvien extends AppCompatActivity {

    private ListView listView;
    private NhanVienAdapter nhanVienAdapter;
    private TextView themNV;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhanvien);

        // Khởi tạo ListView và adapter cho danh sách nhân viên
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

        List<NhanVien> nhanVienList = databaseHelper.getAllNhanVien();
        nhanVienAdapter = new NhanVienAdapter(this, nhanVienList, databaseHelper); // Truyền thêm tham số databaseHelper
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

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật danh sách nhân viên khi quay lại màn hình
        List<NhanVien> nhanVienList = databaseHelper.getAllNhanVien();
        nhanVienAdapter.updateData(nhanVienList);
    }
}
