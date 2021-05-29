package org.lentils.oboronataganroga1855.ui.sites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.lentils.oboronataganroga1855.R;

public class SitesFragment extends Fragment {

    private SitesViewModel sitesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sitesViewModel =
                new ViewModelProvider(this).get(SitesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sites, container, false);
        final TextView textView = root.findViewById(R.id.text_sites);
        sitesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}