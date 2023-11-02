package edu.poly.qlns.chucnang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import edu.poly.qlns.NhanVienAdapter;
import edu.poly.qlns.R;
import edu.poly.qlns.data.NhanVien;
import edu.poly.qlns.themsuaxoa.themsv;

public class nhanvien extends AppCompatActivity {

    private ListView listView;
    private NhanVienAdapter nhanVienAdapter;
    private TextView themNV;
    private List<NhanVien> nhanVienList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhanvien);

        // Khởi tạo ListView và adapter cho danh sách nhân viên
        listView = findViewById(R.id.NhanVien);
        themNV = findViewById(R.id.tthemNV);
        themNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(nhanvien.this, themsv.class);
                startActivity(intent);
            }
        });
        // Khởi tạo danh sách nhân viên (ví dụ: sử dụng ArrayList)
        nhanVienList = new ArrayList<>();
        nhanVienAdapter = new NhanVienAdapter(this, nhanVienList);
        listView.setAdapter(nhanVienAdapter);
    }

    // Phương thức này dùng để cập nhật danh sách nhân viên sau khi thêm một nhân viên mới
    private void updateNhanVienList() {
        // Thực hiện cập nhật danh sách nhân viên từ cơ sở dữ liệu hoặc nguồn dữ liệu của bạn
        // Ví dụ: nhanVienList = yourDataSource.getUpdatedNhanVienList();
        nhanVienAdapter.updateData(nhanVienList); // Cập nhật giao diện
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateNhanVienList(); // Cập nhật danh sách nhân viên khi quay lại màn hình
    }
}
