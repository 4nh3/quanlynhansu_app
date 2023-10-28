package edu.poly.qlns;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class dangnhap extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        // Kết nối các phần tử giao diện với mã Java
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin đăng nhập từ người dùng
                String username = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Kiểm tra đăng nhập
                if (validateLogin(username, password)) {
                    // Đăng nhập thành công, bạn có thể chuyển đến màn hình chính ở đây
                    Toast.makeText(dangnhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(dangnhap.this, "Đăng nhập không thành công. Vui lòng kiểm tra tên tài khoản và mật khẩu.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        // Kết nối đến cơ sở dữ liệu
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truy vấn cơ sở dữ liệu để kiểm tra đăng nhập
        String query = "SELECT * FROM Nguoidung WHERE tentk = ? AND matkhau = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        // Kiểm tra xem dữ liệu có tồn tại trong cơ sở dữ liệu hay không
        if (cursor.moveToFirst()) {
            // Đăng nhập thành công
            cursor.close();
            db.close();
            return true;
        } else {
            // Đăng nhập thất bại
            cursor.close();
            db.close();
            return false;
        }
    }
}

