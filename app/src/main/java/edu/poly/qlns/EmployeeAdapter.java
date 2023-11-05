package edu.poly.qlns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<DatabaseHelper.Employee> employeeList;

    public EmployeeAdapter(Context context, List<DatabaseHelper.Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);
        }

        TextView maNhanVienTextView = convertView.findViewById(R.id.tvMaNV);
        TextView tenNhanVienTextView = convertView.findViewById(R.id.tvTenNV);
//        TextView maPhongBanTextView = convertView.findViewById(R.id.maPhongBanTextView);
//        TextView soDienThoaiTextView = convertView.findViewById(R.id.soDienThoaiTextView);

        DatabaseHelper.Employee employee = employeeList.get(position);

        maNhanVienTextView.setText("Mã NV: " + employee.getMaNhanVien());
        tenNhanVienTextView.setText("Tên: " + employee.getTenNhanVien());
//        maPhongBanTextView.setText("Phòng ban: " + employee.getMaPhongBan());
//        soDienThoaiTextView.setText("SĐT: " + employee.getSoDienThoai());

        return convertView;
    }
}

