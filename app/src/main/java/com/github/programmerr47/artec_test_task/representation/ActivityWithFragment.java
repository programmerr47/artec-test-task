package com.github.programmerr47.artec_test_task.representation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.github.programmerr47.artec_test_task.R;

public class ActivityWithFragment extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ShopListFragment())
                    .commit();
        }
    }
}
