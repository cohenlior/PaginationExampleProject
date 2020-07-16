package com.lior.pagination.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lior.pagination.R;
import com.lior.pagination.databinding.PhotoFragmentBinding;
import com.lior.pagination.ui.detail.FullScreenFragment;

import java.util.Objects;

public class MainFragment extends Fragment {
    private static final int SPAN_COUNT = 3;
    private PhotoListAdapter adapter;
    private PhotoFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PhotoFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PhotosViewModel photosViewModel = new ViewModelProvider(this).get(PhotosViewModel.class);

        binding.photoList.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));

        adapter = new PhotoListAdapter(this::replaceFragment);

        photosViewModel.getPhotoLiveData().observe(getViewLifecycleOwner(), pagedList ->
                adapter.submitList(pagedList));

        photosViewModel.getNetworkState().observe(getViewLifecycleOwner(), networkState -> {
            switch (networkState) {
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    binding.progressBar.setVisibility(View.GONE);
                case FAILED:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.errorTextView.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.photoList.setAdapter(adapter);
    }

    private void replaceFragment(String url) {
        if (getActivity() == null) return;

        (getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FullScreenFragment.newInstance(url))
                .addToBackStack(null)
                .commit();
    }

}