package com.github.lhvubtqn.lazyscanner.ui.authentication;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.lhvubtqn.lazyscanner.R;
import com.github.lhvubtqn.lazyscanner.utils.BroadcastUtil;

public class LoginFragment extends Fragment {
    boolean preset = false;
    String presetUsername = null;
    String presetPassword = null;
    private EditText editTxtUsername;
    private EditText editTxtPassword;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        this.editTxtUsername = root.findViewById(R.id.editTxt_username);
        this.editTxtPassword = root.findViewById(R.id.editTxt_password);
        if (this.preset) {
            this.editTxtUsername.setText(this.presetUsername);
            this.editTxtPassword.setText(this.presetPassword);
            this.preset = false;
        }

        this.editTxtPassword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            doLogin();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        View btn = root.findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        View txtSignUp = root.findViewById(R.id.txt_signUp);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationActivity.SwapFragment(AuthenticationActivity.FRAGMENT_SIGN_UP);
            }
        });

        View txtForgetPassword = root.findViewById(R.id.txt_forgetPassword);
        txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationActivity.SwapFragment(AuthenticationActivity.FRAGMENT_FORGET_PASSWORD);
            }
        });

        btn = root.findViewById(R.id.btn_google_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do google login
            }
        });

        btn = root.findViewById(R.id.btn_facebook_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do facebook login
            }
        });
        return root;
    }

    private void doLogin() {
        String username = editTxtUsername.getText().toString();
        String password = editTxtPassword.getText().toString();
        if (username.compareTo("") == 0) {
            Toast.makeText(getContext(), "Hãy nhập tên đăng nhập!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (password.compareTo("") == 0) {
            Toast.makeText(getContext(), "Hãy nhập mật khẩu!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        new Runnable() {
            @Override
            public void run() {
                AuthenticationActivity.toggleProgress();
                // do login here
                // if success
                BroadcastUtil.sendBroadcast(
                        BroadcastUtil.ACTION.AUTHENTICATION,
                        getContext(),
                        new String[] {"name", "status"}, new String[] {"login", "ok"}); //failed if failed
            }
        }.run();
    }

    public void preset(String username, String password) {
        this.preset = true;
        this.presetUsername = username;
        this.presetPassword = password;
    }
}
