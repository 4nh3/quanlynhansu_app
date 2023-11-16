package edu.poly.qlns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.poly.qlns.data.ChamCong;

public class ChamCongAdapter extends ArrayAdapter<ChamCong> {

    private final Context context;
    private final List<ChamCong> chamCongList;

    public ChamCongAdapter(Context context, List<ChamCong> chamCongList) {
        super(context, R.layout.item_chamcong, chamCongList);
        this.context = context;
        this.chamCongList = chamCongList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_chamcong, parent, false);

        TextView textViewMaNV = rowView.findViewById(R.id.textViewMaNV);


        ChamCong chamCong = chamCongList.get(position);

        textViewMaNV.setText("MaNV: " + chamCong.getMaNhanVien());


        return rowView;
    }
}

