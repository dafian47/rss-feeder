package com.dafian.android.rssfeeder.ui.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dafian.android.rssfeeder.R;
import com.dafian.android.rssfeeder.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Dafian on 10/6/17
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        if (findViewById(R.id.frame_rss) != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_rss, new MainFragment())
                    .commit();
        }
    }
}
