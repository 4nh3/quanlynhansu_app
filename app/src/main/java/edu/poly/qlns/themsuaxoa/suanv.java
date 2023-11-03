package edu.poly.qlns.themsuaxoa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.R;
import edu.poly.qlns.data.NhanVien;

public class suanv extends AppCompatActivity {

    EditText edtMaNV, edtTenNV, edtNgaySinh, edtPhaiTinh, edtCoGiaDinh, edtDiaChi, edtSoDienThoai, edtTrinhDo, edtLuongCB, edtNgayLamViec, edtChucVu, edtMaPhongBan;
    Button btnLuu;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsv);

        // Ánh xạ các thành phần giao diện
        edtMaNV = findViewById(R.id.txtmanv);
        edtTenNV = findViewById(R.id.txttennv);
        edtNgaySinh = findViewById(R.id.txtngaysinh);
        edtPhaiTinh = findViewById(R.id.txtphaitinh);
        edtCoGiaDinh = findViewById(R.id.txtcogiadinh);
        edtDiaChi = findViewById(R.id.txtdiachi);
        edtSoDienThoai = findViewById(R.id.txtsodienthoai);
        edtTrinhDo = findViewById(R.id.txttrinhdo);
        edtLuongCB = findViewById(R.id.txtluongcb);
        edtNgayLamViec = findViewById(R.id.txtngaylamviec);
        edtChucVu = findViewById(R.id.txtchucvu);
        edtMaPhongBan = findViewById(R.id.txtmaphongban);
        btnLuu = findViewById(R.id.button_luu);

        dbHelper = new DatabaseHelper(this);

        // Nhận thông tin nhân viên để hiển thị lên giao diện
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int maNhanVien = extras.getInt("edit_manv");
            String tenNhanVien = extras.getString("edit_tennv");

            edtMaNV.setText(String.valueOf(maNhanVien));
            edtTenNV.setText(tenNhanVien);

            // Lấy thông tin nhân viên từ cơ sở dữ liệu và hiển thị lên giao diện
            NhanVien editNhanVien = dbHelper.getNhanVienByMaNV(maNhanVien);
            if (editNhanVien != null) {
                edtNgaySinh.setText(editNhanVien.getNgaySinh());
                edtPhaiTinh.setText(editNhanVien.getPhaiTinh());
                edtCoGiaDinh.setText(editNhanVien.getCoGiaDinh());
                edtDiaChi.setText(editNhanVien.getDiaChi());
                edtSoDienThoai.setText(editNhanVien.getSoDienThoai());
                edtTrinhDo.setText(editNhanVien.getTrinhDo());
                edtLuongCB.setText(String.valueOf(editNhanVien.getLuongCB()));
                edtNgayLamViec.setText(editNhanVien.getNgayLamViec());
                edtChucVu.setText(editNhanVien.getChucVu());
                edtMaPhongBan.setText(editNhanVien.getMaPhongBan());
            }
        }

        // Bắt sự kiện khi nhấn nút "Lưu"
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ EditText
                int maNV = Integer.parseInt(edtMaNV.getText().toString());
                String tenNV = edtTenNV.getText().toString();
                String ngaySinh = edtNgaySinh.getText().toString();
                String phaiTinh = edtPhaiTinh.getText().toString();
                String coGiaDinh = edtCoGiaDinh.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                String soDienThoai = edtSoDienThoai.getText().toString();
                String trinhDo = edtTrinhDo.getText().toString();
                double luongCB = Double.parseDouble(edtLuongCB.getText().toString());
                String ngayLamViec = edtNgayLamViec.getText().toString();
                String chucVu = edtChucVu.getText().toString();
                String maPhongBan = edtMaPhongBan.getText().toString();

                // Tạo đối tượng NhanVien mới
                NhanVien newNhanVien = new NhanVien(maNV, tenNV, ngaySinh, phaiTinh, diaChi, soDienThoai, coGiaDinh, trinhDo, luongCB, ngayLamViec, chucVu, maPhongBan);

                // Cập nhật thông tin nhân viên vào cơ sở dữ liệu
                long result = dbHelper.updateNhanVien(newNhanVien);

                if (result > 0) {
                    Toast.makeText(suanv.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(suanv.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
