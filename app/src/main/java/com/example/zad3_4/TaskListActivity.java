package com.example.zad3_4;

import androidx.fragment.app.Fragment;


public class TaskListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TaskListFragment();
    }
}