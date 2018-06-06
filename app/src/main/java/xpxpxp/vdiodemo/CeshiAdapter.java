package xpxpxp.vdiodemo;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import xpxpxp.vdiodemo.sqlite.Ceshi;

public class CeshiAdapter extends BaseAdapter {
    private List<Ceshi> mList;
    private LayoutInflater layoutInflater;

    public CeshiAdapter(Context context, List<Ceshi> list) {
        mList = list;
        layoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.dao_item, parent, false);
            viewHolder.itemId = convertView.findViewById(R.id.item_id);
            viewHolder.itemName = convertView.findViewById(R.id.item_name);
            viewHolder.itemAge = convertView.findViewById(R.id.item_age);
            viewHolder.itemDate = convertView.findViewById(R.id.item_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Ceshi ceshi = mList.get(position);
        viewHolder.itemId.setText(String.valueOf(ceshi.getId()));
        viewHolder.itemName.setText(ceshi.getName());
        viewHolder.itemAge.setText(String.valueOf(ceshi.getAge()));
        viewHolder.itemDate.setText(String.valueOf((int) ceshi.getDate()));
        return convertView;
    }

    class ViewHolder {//listview_item_mid对应的样式
        TextView itemId, itemName, itemAge, itemDate;
    }
}
