package husseinm19.github.nightclock;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class StopwatchFragment extends Fragment {

    private static final String STOPW = "Stopwatch";
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;
    private Button sws;
    private Button swr;
    private Button swp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        chronometer = rootView.findViewById(R.id.chronometert);
        sws = rootView.findViewById(R.id.sws);
        swr = rootView.findViewById(R.id.swr);
        swp = rootView.findViewById(R.id.swp);

        sws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startChronometer(rootView);            }
        });

        swp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                pauseChronometer(rootView);            }
        });

        swr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                resetChronometer(rootView);            }
        });


        return rootView;
    }
    public void startChronometer(View v){
        if (!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            swp.setVisibility(VISIBLE);
            sws.setVisibility(INVISIBLE);
            swr.setVisibility(INVISIBLE);
            running = true;
        }
    }

    public void pauseChronometer(View v){
        if (running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            swp.setVisibility(INVISIBLE);
            sws.setVisibility(VISIBLE);
            swr.setVisibility(VISIBLE);
            running = false;
        }
    }
    public void resetChronometer(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        sws.setVisibility(VISIBLE);
        swp.setVisibility(INVISIBLE);
        swr.setVisibility(INVISIBLE);
        pauseOffset = 0;
    }
}
