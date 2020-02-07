package com.github.lhvubtqn.lazyscanner.ui.authentication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.lhvubtqn.lazyscanner.MainActivity;
import com.github.lhvubtqn.lazyscanner.R;
import com.github.lhvubtqn.lazyscanner.utils.BroadcastUtil;

import mehdi.sakout.fancybuttons.Utils;

public class AuthenticationActivity extends FragmentActivity {

    final public static int FRAGMENT_LOGIN = 1;
    final public static int FRAGMENT_SIGN_UP = 2;
    final public static int FRAGMENT_FORGET_PASSWORD = 3;

    private static FragmentManager myFragmentManager;
    private static LoginFragment loginFragment;
    private static SignUpFragment signUpFragment;
    private static ForgetPasswordFragment forgetPasswordFragment;

    private BroadcastReceiver broadcastReceiver;

    private static View background;
    private static ProgressBar progressBar;
    private static boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        this.addBroadcastReceiver();

        myFragmentManager = getSupportFragmentManager();
        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment();
        forgetPasswordFragment = new ForgetPasswordFragment();

        background = findViewById(R.id.authentication_background);
        progressBar = findViewById(R.id.authentication_progress_bar);
        isLoading = false;

        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.authentication_fragment_container, loginFragment);
            fragmentTransaction.commit();
        }
    }

    public static void SwapFragment(int fragment_id) {
        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (fragment_id) {
            case FRAGMENT_LOGIN:
                fragment = loginFragment;
                break;
            case FRAGMENT_SIGN_UP:
                fragment = signUpFragment;
                break;
            case FRAGMENT_FORGET_PASSWORD:
                fragment = forgetPasswordFragment;
                break;
            default: return;
        }
        fragmentTransaction.replace(R.id.authentication_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void addBroadcastReceiver() {
        this.broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String name = intent.getStringExtra("name");
                int status = intent.getIntExtra("status", 0);
                if (name.equals(BroadcastUtil.NAME.LOGIN)) {
                    if (status == BroadcastUtil.STATUS.OK) {
                        showMainActivity();
                    }else {
                        Toast.makeText(AuthenticationActivity.this, "Sai tên đăng nhập hoặc mật khẩu!",
                                Toast.LENGTH_LONG).show();
                        if (isLoading)
                            toggleProgress();
                    }
                } else if (name.equals(BroadcastUtil.NAME.SIGN_UP)) {
                    if (status == BroadcastUtil.STATUS.OK) {
                        Toast.makeText(AuthenticationActivity.this, "Đăng ký thành công!",
                                Toast.LENGTH_LONG).show();
                        String username = intent.getStringExtra("username");
                        String password = intent.getStringExtra("password");

                        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                        loginFragment = new LoginFragment();
                        loginFragment.preset(username, password);
                        fragmentTransaction.replace(R.id.authentication_fragment_container, loginFragment);
                        fragmentTransaction.commit();
                    }else {
                        Toast.makeText(AuthenticationActivity.this, "Tên đăng nhập đã tồn tại!",
                                Toast.LENGTH_LONG).show();
                        if (isLoading)
                            toggleProgress();
                    }
                }
            }
        };
        registerReceiver(this.broadcastReceiver, new IntentFilter(BroadcastUtil.ACTION.AUTHENTICATION));
    }

    private void showMainActivity() {
        Intent intentSuccess = new Intent(this, MainActivity.class);
        startActivity(intentSuccess);
        this.finish();
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
    @Override
    protected void onDestroy() {
        this.unregisterReceiver(this.broadcastReceiver);
        super.onDestroy();
    }
}
