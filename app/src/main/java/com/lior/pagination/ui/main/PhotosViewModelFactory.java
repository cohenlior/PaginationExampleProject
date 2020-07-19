package com.lior.pagination.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.lior.pagination.data.FlickerRepository;

/**
 * Factory class for ViewModels.
 */
public class PhotosViewModelFactory implements ViewModelProvider.Factory {

    private FlickerRepository repository;

    public PhotosViewModelFactory(FlickerRepository repository) {
        this.repository = repository;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PhotosViewModel.class)) {
            return (T) new PhotosViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}