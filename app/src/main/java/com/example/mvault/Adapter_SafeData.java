package com.example.mvault;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.mvault.databinding.RowListviewBinding;

import java.util.ArrayList;

public class Adapter_SafeData extends BaseAdapter {
    private Context context;
    private ArrayList<SafeData> safeData_List;
    private RowListviewBinding binding;

    public Adapter_SafeData(Context context, ArrayList<SafeData> safeData_List) {
        this.context = context;
        this.safeData_List = safeData_List;
    }

    @Override
    public int getCount() {
        return safeData_List.size();
    }

    @Override
    public Object getItem(int position) {
        return safeData_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = binding.inflate(LayoutInflater.from(context), viewGroup, false);
        view = binding.getRoot();
        SafeData tmp = safeData_List.get(i);

        String uname = tmp.getUser_name();
        binding.tvName.setText(uname);

        String pss = tmp.getPassword();
        binding.tvPass.setText(pss);

        binding.tvUrl.setText(tmp.getUrl());

        return view;
    }
}
