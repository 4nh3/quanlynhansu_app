package edu.poly.qlns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PhongBanAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> items;

    public PhongBanAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_nghihuu, null);
        }

        String item = items.get(position);

        TextView textStt = convertView.findViewById(R.id.textStt);
        TextView manvnh = convertView.findViewById(R.id.manvnh);
        TextView hotennh = convertView.findViewById(R.id.hotennh);
        TextView phaitinhnh = convertView.findViewById(R.id.phaitinhnh);
        TextView ngaysinhnh = convertView.findViewById(R.id.ngaysinhnh);

        // Tách thông tin từ chuỗi 'item' và đặt nó vào các TextView tương ứng
        String[] parts = item.split(";");
        textStt.setText(parts[0]);
        manvnh.setText(parts[1]);
        hotennh.setText(parts[2]);
        phaitinhnh.setText(parts[3]);
        ngaysinhnh.setText(parts[4]);

        return convertView;
    }
}
