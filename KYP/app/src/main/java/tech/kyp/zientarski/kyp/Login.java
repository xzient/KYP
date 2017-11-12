package tech.kyp.zientarski.kyp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;




public class Login extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ID");
    DatabaseReference mConditionRef = myRef.child("ID");
    //myRef.setValue("Hello, World!");

    LoginButton loginButton;
    CallbackManager callbackManager;
    //AWS
    CognitoCachingCredentialsProvider credentialsProvider;
    private static final String TAG = "Login";

    //FB

    //USER
    User user;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user = User.getInstance();


        FacebookSdk.getApplicationContext();

        //LOGIN FB
        loginButton = (LoginButton)findViewById(R.id.login_button_FB);
        callbackManager = CallbackManager.Factory.create();

        //AWs
        credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:98e98490-bf8d-4153-ab99-de265b97ad48", // Identity pool ID
                Regions.US_EAST_1 // Region
        );


        //
        //If access token is already here, set fb session
        final AccessToken fbAccessToken = AccessToken.getCurrentAccessToken();
        if (fbAccessToken != null) {
            //setFacebookSession(fbAccessToken);
            //btnLoginFacebook.setVisibility(View.GONE);
        }



        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final String userId = loginResult.getAccessToken().getUserId();

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object);

                        /**
                         * ADD PUSH
                         */
                        myRef.push().setValue(userId);


                        //mConditionRef.getKey().push().setValue(userId);
                        //Toast.makeText(Login.this, mConditionRef.getKey(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this,
                                Questions1.class);
                        startActivity(intent);


                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name, last_name, email, id, gender");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }


            @Override
            public void onError(FacebookException error) {

            }
        });

    }//End OnCreate

    @Override
    public void onStart() {
        super.onStart();
        /*No listener now
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                TextView tv_name = (TextView) findViewById(R.id.name);
                tv_name.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */


    }

    public void displayUserInfo(JSONObject object) {
        String first_name, last_name, email, id;
        first_name = "";
        last_name = "";
        email = "";
        id = "";
        try {
            first_name = object.getString("first_name");
            last_name = object.getString("last_name");
            email = object.getString("email");
            id = object.getString("id");
            user.gender = object.getString("gender");
            //Toast.makeText(Login.this, object.getString("gender"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView tv_name, tv_email, tv_id;
        tv_name = (TextView) findViewById(R.id.name);
        tv_email = (TextView) findViewById(R.id.email);
        tv_id = (TextView) findViewById(R.id.id);

        tv_name.setText(first_name + " "+ last_name);
        tv_email.setText("Email : " + email);
        tv_id.setText("ID :" + id);

        user.name = first_name;
        user.id = id;






        Map logins = new HashMap();
// Set the facebook token in the logins map

        logins.put("graph.facebook.com", tv_id);
// Add the new map we created to the Credentials Provider
        credentialsProvider.withLogins(logins);
        //;






    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }





}
