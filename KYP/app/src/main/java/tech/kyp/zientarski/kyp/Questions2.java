package tech.kyp.zientarski.kyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Questions2 extends AppCompatActivity {

    SeekBar seekbar1; // relogious
    SeekBar seekbar2; // follows texts
    Button next;

    int q1 = 5;
    int q2 = 5;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions2);

        seekbar1 = (SeekBar) findViewById(R.id.question2_seekbar1);
        seekbar2 = (SeekBar) findViewById(R.id.question2_seekbar2);
        next = (Button) findViewById(R.id.question2_button);
        user = User.getInstance();


        /**
         * Seekbar 1
         */
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                q1 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /**
         * Seekbar 2
         */
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                q2 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekbar1.setProgress(q1);
                seekbar2.setProgress(q2);


                user.religion = (q1 + q2) / 2; //How religious
                Toast.makeText(Questions2.this, String.valueOf(user.religion), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Questions2.this,
                        Questions3.class);
                startActivity(intent);
            }
        });







    }//ON CREATE
}
