package com.expensetracking;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.logging.Logger;

/**
 * A placeholder fragment containing a simple view.
 */
public class DashboardActivityFragment extends Fragment {
    private static final Logger logger= Logger.getLogger(DashboardActivityFragment.class.getName());

    public DashboardActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}
