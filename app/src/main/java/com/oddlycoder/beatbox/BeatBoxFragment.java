package com.oddlycoder.beatbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oddlycoder.beatbox.databinding.FragmentBeatBoxBinding;
import com.oddlycoder.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {

    private static BeatBoxFragment sInstance;
    private BeatBox mBeatBox;

    private BeatBoxFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getActivity());
    }

    public static BeatBoxFragment getInstance(){
        if (sInstance == null) {
            sInstance = new BeatBoxFragment();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentBeatBoxBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);

        binding.recyclerViewLayout.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerViewLayout.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return binding.getRoot();
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding soundBinding;
        private SoundHolder(ListItemSoundBinding soundBinding) {
            super(soundBinding.getRoot());
            this.soundBinding = soundBinding;
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding =
                    DataBindingUtil.inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
