package com.tms.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Adrian on 2/21/2016.
 */

/*
This class is responsable for constructing and managing the listView
inside a fragment.
The list contains 2 TextFields and one ImageView = List with images
 */
public class CustomAdapter extends BaseAdapter {

    String items[];
    String underItems[];
    LayoutInflater mInflater;
    Bitmap[] image;
    //Bitmap[] imageStatus;

    public CustomAdapter(Context context, String[] items , String[] underItems, Bitmap[] image) {
        mInflater = LayoutInflater.from(context);
        this.items = items;
        this.underItems = underItems;
        this.image = image;
        //this.imageStatus = imageStatus;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView ==null)
        {
            convertView = mInflater.inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.textView1);
            holder.tv2 = (TextView) convertView.findViewById(R.id.textView2);
            holder.iv = (ImageView) convertView.findViewById(R.id.imageView1);
            //holder.iv2 = (ImageView) convertView.findViewById(R.id.imageView2);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(items[position]);
        holder.tv2.setText(underItems[position]);
        holder.iv.setImageBitmap(image[position]);
        //holder.iv2.setImageBitmap(imageStatus[position]);
        // use holder.iv to set whatever image you want according to the position
        return convertView;
    }

    static class ViewHolder
    {
        ImageView iv;
        //ImageView iv2;
        TextView tv;
        TextView tv2;
    }
}