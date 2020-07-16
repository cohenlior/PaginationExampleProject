package com.lior.pagination.ui.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lior.pagination.databinding.FullScreenFragmentBinding;
import com.lior.pagination.databinding.PhotoFragmentBinding;
import com.squareup.picasso.Picasso;

public class FullScreenFragment extends Fragment {

    private String param;
    private FullScreenFragmentBinding binding;

    public static FullScreenFragment newInstance(String url) {
        FullScreenFragment fragment = new FullScreenFragment();
        Bundle args = new Bundle();
        args.putString("photo_url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            param = getArguments().getString("photo_url");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FullScreenFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Picasso.get()
                .load(param)
                .into(binding.fullscreenImage);
    }

}