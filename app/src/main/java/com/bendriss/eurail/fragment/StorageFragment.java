package com.bendriss.eurail.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bendriss.eurail.R;

public class StorageFragment extends Fragment {

    private Button button;
    private TextView textView;
    private EditText editText;

    private final static String EDIT_TEXT_VALUE = "editTextValue";

    public StorageFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storage, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        init(view);

        textView.setText(sharedPreferences.getString(EDIT_TEXT_VALUE, ""));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText().toString());
                editor.putString(EDIT_TEXT_VALUE, editText.getText().toString());
                editor.commit();
            }
        });

        return view;
    }

    /**
     * This function will init our vars
     *
     * @param view
     */
    private void init(View view) {
        textView = view.findViewById(R.id.textTv);
        editText = view.findViewById(R.id.textEt);
        button = view.findViewById(R.id.button);
    }

}
