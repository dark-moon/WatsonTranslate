package com.example.watsontranslate.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watsontranslate.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LanguagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_languages, container, false);

        LanguagesViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()))
                .get(LanguagesViewModel.class);

        return root;
    }
}