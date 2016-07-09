package com.example.yaksa.fileexplorer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yaksa.adapter.FileAdapter;

import java.io.File;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private static final String TAG = "Yaksa";

    private File[] files;
    private ListView lvFiles;
    private FileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_browse_mian);
        initView();
        lvFiles.setOnItemClickListener(this);
        lvFiles.setOnItemLongClickListener(this);
    }

    private void initView() {
        File SDcardFile = Environment.getExternalStorageDirectory();    // /mnt/sdCard/
        files = SDcardFile.listFiles();
        lvFiles = (ListView) findViewById(R.id.list);
        try {
            mAdapter = new FileAdapter(this, R.layout.file_list_item, files);
            lvFiles.setAdapter(mAdapter);
            Log.d(TAG, "initView");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (files[position].isDirectory()) {
            mAdapter.setFiles(files[position].listFiles());
            mAdapter.notifyDataSetChanged();
//            FileAdapter newAdapter = new FileAdapter(this,
//                    R.layout.file_list_item, files[position].listFiles());
//            lvFiles.setAdapter(newAdapter);
        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.setIsSelected(position, true);
        mAdapter.notifyDataSetChanged();
        return true;

    }
}
