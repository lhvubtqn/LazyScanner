package com.github.lhvubtqn.lazyscanner;

import android.content.Intent;
import android.os.Bundle;

import com.github.lhvubtqn.lazyscanner.ui.authentication.AuthenticationActivity;
import com.github.lhvubtqn.lazyscanner.utils.BroadcastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private static View background;
    private static ProgressBar progressBar;
    private static boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_files, R.id.nav_translation, R.id.nav_discussion,
                R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        background = findViewById(R.id.main_background);
        progressBar = findViewById(R.id.main_progress_bar);
        isLoading = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onSignOutClicked(MenuItem item) {

        new Runnable() {
            @Override
            public void run() {
                toggleProgress();
                BroadcastUtil.sendBroadcast(BroadcastUtil.ACTION.SIGN_OUT, MainActivity.this, null, null);
                startActivity(new Intent(MainActivity.this, AuthenticationActivity.class));
                finish();
            }
        }.run();
    }

    public static void toggleProgress() {
        if (isLoading) {
            background.setAlpha(1.0f);
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            background.setAlpha(0.6f);
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
