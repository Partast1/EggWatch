package com.example.eggwatch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private Button softBoilButton;
    private Button mediumBoilButton;
    private Button hardBoilButton;
    private Button start_button;
    private CountDownTimer countDownTimer;
    private boolean started;
    private TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding buttons
        softBoilButton = (Button) findViewById(R.id.softBoil);
        mediumBoilButton = (Button) findViewById(R.id.mediumBoil);
        hardBoilButton = (Button) findViewById(R.id.hardBoil);
        start_button = (Button) findViewById(R.id.start_button);
        timer = (TextView) findViewById(R.id.timer);

        //Timer setting buttons
        softBoilButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                timer.setText("03:00");
                start_button.setEnabled(true);



            }
        });
        mediumBoilButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                timer.setText("05:00");
                start_button.setEnabled(true);

            }
        });
        hardBoilButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                timer.setText("07:00");
                start_button.setEnabled(true);
            }
        });
        //Start button starts timer and pauses it.

        start_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            startbutton();
            }
        });

    }
    public void startbutton()
    {
        if (started) {
            started = false;
            start_button.setText("Start");
            timer.setCursorVisible(true);
            countDownTimer.cancel();
        } else {
            started = true;
            timer.setCursorVisible(false);
            start_button.setText("Stop");

            String rawTime = timer.getText().toString();
            String[] tmp = rawTime.split(":");
            long time = 60 * 1000;

            try {
                time = (Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1])) * 1000;
            } catch (Exception e) {
                timer.setText(R.string.time_left);
            }

            countDownTimer = new CountDownTimer(time, 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long remainingSeconds = millisUntilFinished / 1000;
                    long min = remainingSeconds / 60;
                    long seconds = remainingSeconds % 60;

                    timer.setText(min + ":" + (seconds < 10 ? "0" + seconds : seconds));

                }

                @Override
                public void onFinish() {
                    timer.setText(R.string.time_left);

                    new AlertDialog.Builder(MainActivity.this).
                            setTitle("hey").
                            setMessage("done").
                            show();

                }
            };
            countDownTimer.start();
        }

    }
}

