package com.lior.pagination.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lior.pagination.databinding.PhotoItemBinding;
import com.lior.pagination.model.Photo;
import com.squareup.picasso.Picasso;

public class PhotoListAdapter extends PagedListAdapter<Photo, RecyclerView.ViewHolder> {

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }
    public PhotoListAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PhotoItemBinding itemBinding = PhotoItemBinding.inflate(layoutInflater, parent, false);
        return new PhotoItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PhotoItemViewHolder) holder).bindTo(getItem(position), listener);
    }

    public static class PhotoItemViewHolder extends RecyclerView.ViewHolder {

        private PhotoItemBinding binding;

        public PhotoItemViewHolder(PhotoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(Photo photo, final OnItemClickListener listener) {

            itemView.setOnClickListener(v -> listener.onItemClick(photo.urlS));

            Picasso.get()
                    .load(photo.urlS)
                    .fit()
                    .centerCrop()
                    .into(binding.image);
        }
    }

    private static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(Photo oldPhoto, Photo newPhoto) {
            return oldPhoto.getId().equals(newPhoto.getId());
        }

        @Override
        public boolean areContentsTheSame(Photo oldPhoto,
                                          Photo newPhoto) {
            return oldPhoto.equals(newPhoto);
        }
    };
}
