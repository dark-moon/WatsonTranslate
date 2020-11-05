package com.example.watsontranslate.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watsontranslate.R;
import com.example.watsontranslate.databinding.LanguageItemBinding;
import com.example.watsontranslate.network.data.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguageViewHolder> {

    private List<Language> languages = new ArrayList<>();

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LanguageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.language_item, parent, false);
        return new LanguageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        holder.binding.setLanguage(languages.get(position));
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    class LanguageViewHolder extends RecyclerView.ViewHolder {

        final LanguageItemBinding binding;

        public LanguageViewHolder(@NonNull LanguageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
