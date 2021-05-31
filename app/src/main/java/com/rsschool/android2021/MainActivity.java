package com.rsschool.android2021;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements FirstFragment.FirstFragmentListener,
    SecondFragment.SecondFragmentListener {
    private FirstFragment firstFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.addToBackStack("");
        transaction.commit();
    }

    @Override
    public void generate(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void getRandomValue(@NotNull String value) {
        firstFragment.getValue(value);
    }

}
