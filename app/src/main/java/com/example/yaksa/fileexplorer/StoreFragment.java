package com.example.yaksa.fileexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Yaksa on 2016/7/8.
 */
public class StoreFragment extends Fragment{
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_fragment, container, false);
        mListView = (ListView) view.findViewById(R.id.store_file_list);
        return view;
    }

}
