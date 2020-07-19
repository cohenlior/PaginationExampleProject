package com.lior.pagination.utils;

import androidx.lifecycle.ViewModelProvider;

import com.lior.pagination.api.FlickerApi;
import com.lior.pagination.api.FlickerService;
import com.lior.pagination.data.FlickerRepository;
import com.lior.pagination.data.FlickerRepositoryIml;
import com.lior.pagination.ui.main.PhotosViewModelFactory;

/**
 * Class that handles object creation, stores dependencies and then provides those dependencies on demand.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
public class ServiceLocator {

    private static ServiceLocator instance = null;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized (ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    /**
     * Creates an instance of [FlickerRepository] based on the [FlickerService].
     */
    private FlickerRepository provideFlickerRepository() {
        return new FlickerRepositoryIml(FlickerApi.getInstance().create(FlickerService.class));
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    public ViewModelProvider.Factory provideViewModelFactory() {
        return new PhotosViewModelFactory(provideFlickerRepository());
    }
}