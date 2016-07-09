package com.example.yaksa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yaksa.fileexplorer.R;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Yaksa on 2016/7/8.
 */

public class FileAdapter extends ArrayAdapter<File> {

    //    private Context mContext;
    private static final String TAG = "Yaksa";
    private boolean[] arrIsSelected;
    private int resourceId;
    private ViewHolder viewHolder;
    private File[] files;

    public FileAdapter(Context context, int resource, File[] objects) {
        super(context, resource, objects);
        resourceId = resource;
        files = objects;
        arrIsSelected = new boolean[objects.length];
    }


    public void setFiles(File[] files) {
        this.files = files;
        arrIsSelected = new boolean[files.length];
    }

    public boolean getIsSelected(int position) {
        return (position < files.length) ? arrIsSelected[position] : false;
    }

    public void setIsSelected(int position, boolean isSelected) {
        arrIsSelected[position] = isSelected;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        //获取及缓存
        File file = getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.iv_file_icon);
            viewHolder.name = (TextView) view.findViewById(R.id.tv_file_name);
            viewHolder.lastModified = (TextView) view.findViewById(R.id.tv_last_modified);
            viewHolder.size = (TextView) view.findViewById(R.id.tv_file_size);
            viewHolder.childNum = (TextView) view.findViewById(R.id.tv_child_num);
            viewHolder.isChecked = (CheckBox) view.findViewById(R.id.cb_is_checked);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        //设置
        try {
//            System.out.println(file);
            viewHolder.name.setText(file.getName());
            DateFormat format = new SimpleDateFormat("yyyy年M月dd日 HH时mm分ss秒");
            viewHolder.lastModified.setText(String.valueOf(format.format(file.lastModified())));
            if (position < files.length && arrIsSelected[position]) {
//                Log.d(TAG, "setCheckBox");
                viewHolder.isChecked.setVisibility(View.VISIBLE);
                viewHolder.isChecked.setChecked(true);
            }
            if (file.isDirectory()) {
//                Log.d(TAG, "isDirectory");
                viewHolder.icon.setImageResource(R.drawable.ic_folder);
                viewHolder.size.setVisibility(View.GONE);
                viewHolder.childNum.setVisibility(View.VISIBLE);
                viewHolder.childNum.setText("共" + String.valueOf(file.list().length) + "项");

            } else {
//                Log.d(TAG, "else isDirectory");
                viewHolder.icon.setImageResource(R.drawable.ic_common_file);
                viewHolder.childNum.setVisibility(View.GONE);
                viewHolder.size.setVisibility(View.VISIBLE);
                viewHolder.size.setText(String.valueOf(file.length()));
            }

        } catch (NullPointerException e) {
            Log.d(TAG, "NullPointerException");
            e.printStackTrace();
        }

        return view;
    }
    //缓存类
    class ViewHolder {
        ImageView icon;
        TextView name;
        TextView lastModified;
        TextView size;
        TextView childNum;
        CheckBox isChecked;
    }

}
