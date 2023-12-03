package edu.poly.qlns.chucnang;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import edu.poly.qlns.DatabaseHelper;
import edu.poly.qlns.MainActivity;
import edu.poly.qlns.R;

public class phongban extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private ListView listView;
    private LinearLayout btnNhanVien;
    private LinearLayout btnPhongBan;

    private LinearLayout btnNghiHuu;
    private LinearLayout btnChamCong;
    private LinearLayout btnLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phongban);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.PhongBan);
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
                Intent intent = new Intent(phongban.this, nhanvien.class);
                startActivity(intent);
            }
        });
        btnPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(phongban.this, phongban.class);
                startActivity(intent);
            }
        });
        btnNghiHuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(phongban.this, nghihuu.class);
                startActivity(intent);
            }
        });
        btnChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(phongban.this, HienThiChamCong.class);
                startActivity(intent);
            }
        });
        btnLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi button "NHÂN VIÊN" được nhấn, chuyển đến trang NhanVien
                Intent intent = new Intent(phongban.this, luong.class);
                startActivity(intent);
            }
        });

        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT mapb AS _id, tenpb, sdt FROM PhongBan", null);

        // Tạo một Adapter để kết nối dữ liệu và giao diện
        String[] fromColumns = {"tenpb", "_id", "sdt"};
        int[] toViews = {R.id.tvTenPB, R.id.tvMaPB, R.id.tvsdt};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_phongban, cursor, fromColumns, toViews, 0);

        // Gán Adapter cho ListView
        listView.setAdapter(adapter);

        // Khi người dùng chọn một phòng ban, chuyển sang trang danh sách nhân viên
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPhongBan = getSelectedPhongBan(position);

                Intent intent = new Intent(phongban.this, nhanvien.class);
                intent.putExtra("selectedPhongBan", selectedPhongBan);
                startActivity(intent);
            }
        });
    }

    // Thêm phương thức để lấy mã phòng ban từ vị trí được chọn
    private String getSelectedPhongBan(int position) {
        // Lấy Cursor hiện tại từ ListView
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);
        // Lấy mã phòng ban từ cột "_id"
        String selectedPhongBan = cursor.getString(cursor.getColumnIndex("_id"));
        return selectedPhongBan;
    }
}
