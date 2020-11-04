package com.example.watsontranslate.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watsontranslate.tools.ImageUtil;
import com.example.watsontranslate.R;

public class HomeFragment extends Fragment {

    private static final String TAG = "Home Fragment";
    private ImageView imageViewBackground;
    private TextView tvTitle;
    private ImageButton btnTranslate;
    private ImageButton btnListLangs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tvTitle = root.findViewById(R.id.tv_title);
        btnTranslate = root.findViewById(R.id.btn_translate);
        imageViewBackground = root.findViewById(R.id.imageViewHome);
        btnListLangs = root.findViewById(R.id.btn_list_langs);
        int lorum_picsum_id = getResources().getIdentifier("lorum_picsum_large", "drawable", getContext().getPackageName());

        LanguagesViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()))
                .get(LanguagesViewModel.class);
        viewModel.onLoadSupportedLanguages();
        doOnViewLayout(imageViewBackground, () -> {
            imageViewBackground.setImageBitmap(ImageUtil.decodeSampledBitmapFromResources(
                    getResources(),
                    lorum_picsum_id,
                    imageViewBackground.getWidth(),
                    imageViewBackground.getHeight()
            ));
        });

        tvTitle.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.title_anim));
        doOnViewLayout(btnTranslate, () -> {
            btnTranslate.setImageBitmap(ImageUtil.decodeSampledBitmapFromResources(getResources(),
                    getResources().getIdentifier("ic_translate", "drawable", getContext().getPackageName()),
                    btnTranslate.getWidth(),
                    btnTranslate.getHeight()));
        });
        btnTranslate.setBackgroundColor(Color.TRANSPARENT);

        doOnViewLayout(btnListLangs, () -> {
            btnListLangs.setImageBitmap(ImageUtil.decodeSampledBitmapFromResources(getResources(),
                    getResources().getIdentifier("ic_langs", "drawable", getContext().getPackageName()),
                    btnListLangs.getWidth(),
                    btnListLangs.getHeight()));
        });
        btnListLangs.setBackgroundColor(Color.TRANSPARENT);

        btnListLangs.setOnClickListener(v -> {

        });
        return root;
    }

    private void doOnViewLayout(View view, Runnable action) {
        if (ViewCompat.isLaidOut(view)) {
            action.run();
        } else {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);
                    action.run();
                }
            });
        }
    }

}