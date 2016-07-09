package com.pencilbox.netknight.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.pencilbox.netknight.R;
import com.pencilbox.netknight.presentor.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wu on 16/7/8.
 */

public class AddressInputDialog extends DialogFragment {
    private EditText address_start;
    private EditText address_end;
    private Button ad_sure;

    public interface DataInputListener {
        void onDataInputListener(String start, String end);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_input, container);
        address_start = (EditText) view.findViewById(R.id.edit_adstartin);
        address_end = (EditText) view.findViewById(R.id.edit_adstartend);
        ad_sure = (Button) view.findViewById(R.id.ad_sure);
        ad_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataInputListener listener = (DataInputListener) getActivity();
                listener.onDataInputListener(address_start.getText().toString(),
                        address_end.getText().toString());
                AddressInputDialog.this.dismiss();
            }
        });

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

