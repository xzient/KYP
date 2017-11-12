package tech.kyp.zientarski.kyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Post extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("POST");
    DatabaseReference myRef2 = database.getReference("ID");
    Button post_button;
    EditText post_text;
    String text = "";
    User user = User.getInstance();
    int user_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post_text = (EditText) findViewById(R.id.post_post);
        post_button = (Button) findViewById(R.id.post_add);



        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = post_text.getText().toString();

                //Check size
                if(text.length() <= 20) {
                    Toast.makeText(Post.this, "Your comment is too short, use at least 250 characters", Toast.LENGTH_SHORT).show();




                }

                else if(false) { //Case there is a slut
                    Toast.makeText(Post.this, "Do not use slurs and make your post more constructive and less offensive", Toast.LENGTH_LONG);
                }

                else {
                    myRef2.child("post_count").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null) {

                                user_count = Integer.valueOf((String)dataSnapshot.getValue());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                    user_count++;
                    String post_count = user.id + "-" + user_count;
                    myRef.child(post_count).child("politics").setValue(user.politics);
                    myRef.child(post_count).child("religion").setValue(user.religion);
                    myRef.child(post_count).child("race").setValue(user.race);
                    myRef.child(post_count).child("gender").setValue(user.gender);
                    myRef2.child(user.id).child("post_count").setValue(user_count);


                    Toast.makeText(Post.this, "New Post was added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Post.this,
                            MainActivity.class);
                    startActivity(intent);

                }


            }
        });

    }
}
