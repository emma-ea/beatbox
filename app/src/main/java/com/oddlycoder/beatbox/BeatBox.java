package com.oddlycoder.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    public static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private SoundPool mSoundPool;


    private List<Sound> mSounds = new ArrayList<Sound>();

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        // this old constructor is deprecated but needed for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundID(soundId);
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "loadSounds: Sounds found: " + soundNames.length);
        } catch (IOException ioe) {
            Log.e(TAG, "loadSounds: could not list assets ", ioe);
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "loadSounds: Could not load sounds: ", ioe);
            }
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

}
