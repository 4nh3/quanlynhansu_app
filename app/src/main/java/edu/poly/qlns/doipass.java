package edu.poly.qlns;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class doipass extends AppCompatActivity {

    EditText newPasswordEditText;
    EditText confirmPasswordEditText;
    Button changePasswordButton;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenpass);

        newPasswordEditText = findViewById(R.id.editTextText3);
        confirmPasswordEditText = findViewById(R.id.editTextText4);
        changePasswordButton = findViewById(R.id.buttonChangePassword);

        dbHelper = new DatabaseHelper(this);

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = newPasswordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (newPassword.equals(confirmPassword)) {
                    // Mật khẩu khớp, thực hiện thay đổi mật khẩu
                    String username = getIntent().getStringExtra("username");
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    long result = dbHelper.updatePassword(db, username, newPassword);

                    if (result > 0) {
                        Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

