package me.pgb.a2021_02_19a.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import me.pgb.a2021_02_19a.R;

public class DashboardFragment extends Fragment {

    private Button incButton;
    private Button backgroundButton;
    private TextView incTextView;
    private DashboardViewModel dashboardViewModel;

    public void onCreate(Bundle myBundle) {
        super.onCreate(myBundle);

        dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
    }

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        incButton = root.findViewById(R.id.value_button_dashboard);
        incTextView = root.findViewById(R.id.inc_value_dashboard);
        backgroundButton = root.findViewById(R.id.background_button);

        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashboardViewModel.increment();
            }
        });

        backgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread(5, dashboardViewModel);
                thread.start();
            }
        });

        dashboardViewModel.getInt().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                incTextView.setText(String.valueOf(integer).toString());
            }
        });

        return root;
    }

    private class BackgroundThread extends Thread {
        private int seconds;
        private DashboardViewModel dashboardViewModel;

        public BackgroundThread(int seconds, DashboardViewModel dashboardViewModel) {
            this.seconds = seconds;
            this.dashboardViewModel = dashboardViewModel;
        }

        @Override
        public void run() {
            for (int i=0; i < seconds; i++) {
                try {
                    Thread.sleep(1000);
                    Log.i("ON_SECOND", " ticks second: " + i);
                } catch (Exception e) {
                    Log.e("ERROR",e.toString());
                }
            }
            dashboardViewModel.setInt(-9986);
        }
    }
}