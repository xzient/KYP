package tech.kyp.zientarski.kyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Questions1 extends AppCompatActivity {

    SeekBar seekbar1; // Conservative
    SeekBar seekbar2; // Liberal
    Button next;

    int conservative = 5;
    int liberal = 5;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions1);

        seekbar1 = (SeekBar) findViewById(R.id.question1_seekbar1);
        seekbar2 = (SeekBar) findViewById(R.id.question1_seekbar2);
        next = (Button) findViewById(R.id.question1_button);
        user = User.getInstance();


        /**
         * Seekbar 1
         */
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                conservative = progress;
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
                liberal = progress;
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

                seekbar1.setProgress(conservative);
                seekbar2.setProgress(liberal);


                user.politics = (liberal + (10 - conservative)) / 2; //How liberal they are
                Toast.makeText(Questions1.this, String.valueOf(user.politics), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Questions1.this,
                        Questions2.class);
                startActivity(intent);
            }
        });





    }//ON CREATE
}
