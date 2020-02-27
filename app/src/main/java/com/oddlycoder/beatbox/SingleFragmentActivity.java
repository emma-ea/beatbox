package com.oddlycoder.beatbox;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    FragmentManager fm;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beat_box);

        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frag_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
            .add(R.id.frag_container, fragment).commit();
        }
    }
}
