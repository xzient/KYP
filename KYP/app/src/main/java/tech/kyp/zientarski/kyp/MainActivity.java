package tech.kyp.zientarski.kyp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button post;
    Button feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set buttons
        post = (Button)findViewById(R.id.main_post);
        feed = (Button)findViewById(R.id.main_feed);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Go to post
                 */
                Intent intent = new Intent(MainActivity.this,
                        Post.class);
                startActivity(intent);

            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Go to feed
                 */
                Intent intent = new Intent(MainActivity.this,
                        Feed.class);
                startActivity(intent);

            }
        });



    }
}
