package com.example.watsontranslate.view;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watsontranslate.R;
import com.example.watsontranslate.databinding.FragmentLanguagesBinding;


public class LanguagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLanguagesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_languages, container, false);

        LanguagesAdapter adapter = new LanguagesAdapter();
        binding.languagesRecyclerView.setHasFixedSize(true);
        binding.languagesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.languagesRecyclerView.setAdapter(adapter);

        LanguagesViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()))
                .get(LanguagesViewModel.class);

        viewModel.isLoading().observe(getViewLifecycleOwner(), loading -> {
            binding.progressBar.setVisibility(loading? View.VISIBLE : View.GONE);
        });

        viewModel.getSupportedLanguages().observe(getViewLifecycleOwner(), languagesList -> {
            adapter.setLanguages(languagesList);
        });

        viewModel.onLoadSupportedLanguages();
        return binding.getRoot();
    }
}