package com.github.lhvubtqn.lazyscanner.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BroadcastUtil {
    public static final class ACTION {
        public static final String AUTHENTICATION = "AUTHENTICATION";
        public static final String SIGN_OUT = "SIGN_OUT";
    }

    public class Params {
        public String[] names;
        public String[] values;

        public Params(String[] names, String[] values) {
            this.names = names;
            this.values = values;
        }
    }

    public static void sendBroadcast(String action, Context context, String[] names, String[] values) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (names != null) {
            for (int i = 0; i < names.length; ++i) {
                intent.putExtra(names[i], values[i]);
            }
        }
        context.sendBroadcast(intent);
    }
}
