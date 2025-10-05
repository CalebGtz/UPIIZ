package com.example.practica1_damn_hcgo.ui.extras;

import android.os.Bundle;
import android.view.*; import android.widget.Button; import android.widget.EditText; import android.widget.Toast;
import androidx.annotation.*; import androidx.fragment.app.Fragment;

import com.example.practica1_damn_hcgo.R;

public class AccessFragment extends Fragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle b) {
        View v = i.inflate(R.layout.fragment_access, c, false);
        Button btn = v.findViewById(R.id.btnLogin);
        btn.setOnClickListener(x -> Toast.makeText(getContext(), "Login demo", Toast.LENGTH_SHORT).show());
        return v;
    }
}
