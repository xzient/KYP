package tech.kyp.zientarski.kyp;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    User user;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set textview

        user = User.getInstance();

        textview = (TextView)findViewById(R.id.main_text);

        textview.setText(user.id);



    }
}
