/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chromium.latency.walt;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A screen that shows the log.
 */
public class LogFragment extends Fragment {

    TextView mTextView;
    MainActivity activity;


    private BroadcastReceiver mLogReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("message");
            LogFragment.this.appendLogText(msg);
        }
    };

    public LogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (MainActivity) getActivity();
        View view =  inflater.inflate(R.layout.fragment_log, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTextView = (TextView) activity.findViewById(R.id.txt_log);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        mTextView.setText(activity.logger.getLogText());
        activity.logger.broadcastManager.registerReceiver(mLogReceiver,
                new IntentFilter(activity.logger.LOG_INTENT));

    }

    @Override
    public void onPause() {
        activity.logger.broadcastManager.unregisterReceiver(mLogReceiver);
        super.onPause();
    }

    public void appendLogText(String msg) {
        mTextView.append(msg + "\n");
    }

}
