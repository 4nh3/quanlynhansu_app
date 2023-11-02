package edu.poly.qlns.themsuaxoa;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.NhanVienAdapter;
import edu.poly.qlns.R;
import edu.poly.qlns.data.NhanVien;

public class themsv extends AppCompatActivity {

    private EditText edtMaNV, edtTenNV, edtNgaySinh, edtPhaiTinh, edtCoGiaDinh, edtDiaChi, edtSoDienThoai, edtTrinhDo, edtLuongCB, edtNgayLamViec, edtChucVu, edtMaPhongBan;
    private Button btnSave;
    private List<NhanVien> nhanVienList = new ArrayList<>();
    private NhanVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsv);

        // Ánh xạ các thành phần trong layout
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

        // Ánh xạ nút "Lưu"
        btnSave = findViewById(R.id.button_luu);

        // Tạo adapter và thiết lập cho danh sách hiển thị (ListView hoặc RecyclerView)
        adapter = new NhanVienAdapter(this, nhanVienList);

        // Xử lý sự kiện khi nút "Lưu" được nhấn
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ các EditText
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

                // Khởi tạo đối tượng DatabaseHelper
                DatabaseHelper dbHelper = new DatabaseHelper(themsv.this);

                // Lấy đối tượng SQLiteDatabase để ghi dữ liệu
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Tạo đối tượng ContentValues để chứa dữ liệu cần chèn
                ContentValues values = new ContentValues();
                values.put("manv", maNV);
                values.put("tennv", tenNV);
                values.put("ngaysinh", ngaySinh);
                values.put("phaitinh", phaiTinh);
                values.put("cogiadinh", coGiaDinh);
                values.put("diachi", diaChi);
                values.put("sodienthoai", soDienThoai);
                values.put("trinhdo", trinhDo);
                values.put("luongcb", luongCB);
                values.put("ngaylamviec", ngayLamViec);
                values.put("chucvu", chucVu);
                values.put("maphongban", maPhongBan);

                // Chèn dữ liệu vào bảng NhanVien
                long newRowId = db.insert("NhanVien", null, values);

                // Đóng kết nối đến cơ sở dữ liệu
                db.close();

                // Hiển thị thông báo cho người dùng
                Toast.makeText(themsv.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
