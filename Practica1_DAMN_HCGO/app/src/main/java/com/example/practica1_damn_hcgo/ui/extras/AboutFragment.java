package com.example.practica1_damn_hcgo.ui.extras;


import android.os.Bundle;
import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup;
import androidx.annotation.*; import androidx.fragment.app.Fragment;
import com.example.practica1_damn_hcgo.R;

public class AboutFragment extends Fragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater i, @Nullable ViewGroup c, @Nullable Bundle b) {
        return i.inflate(R.layout.fragment_about, c, false);
    }
}
