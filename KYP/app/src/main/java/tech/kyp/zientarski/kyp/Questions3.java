package tech.kyp.zientarski.kyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Questions3 extends AppCompatActivity {


    SeekBar seekbar1; // BLT
    SeekBar seekbar2; // tranracial
    Button next;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ID");

    int q1 = 5;
    int q2 = 5;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions3);

        seekbar1 = (SeekBar) findViewById(R.id.question3_seekbar1);
        seekbar2 = (SeekBar) findViewById(R.id.question3_seekbar2);
        next = (Button) findViewById(R.id.question3_button);
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


                user.race = (q1 + q2) / 2; //How much for racial issues
                Toast.makeText(Questions3.this, String.valueOf(user.race), Toast.LENGTH_SHORT).show();

                myRef.child(user.id).child("politics").setValue(user.politics);
                myRef.child(user.id).child("religion").setValue(user.religion);
                myRef.child(user.id).child("race").setValue(user.race);
                myRef.child(user.id).child("post_count").setValue(0);

                /*
                myRef.child(user.id).child("post_count").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() != null) {

                            user.post_count = Integer.valueOf((String)dataSnapshot.getValue());

                            //myRef.child(user.id).child("post_count").setValue(dataSnapshot.getValue());
                        }
                        else { // Not found
                            myRef.child(user.id).child("post_count").setValue(0);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                */







                Intent intent = new Intent(Questions3.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

    }//ON CREATE
}
