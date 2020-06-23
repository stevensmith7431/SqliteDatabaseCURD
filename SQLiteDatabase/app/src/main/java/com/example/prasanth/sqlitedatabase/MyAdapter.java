package com.example.prasanth.sqlitedatabase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyList> mproduct;

    public MyAdapter(Context mContext, List<MyList> mproduct) {
        this.mContext = mContext;
        this.mproduct = mproduct;
    }

    @Override
    public int getCount() {
        return mproduct.size();
    }

    @Override
    public Object getItem(int position) {
        return mproduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.list_items, null);
        TextView text1 = v.findViewById(R.id.emailid);

        final MyList listItem = mproduct.get(position);

        text1.setText(mproduct.get(position).getEmailid());
        return v;
    }
}
