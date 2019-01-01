package org.avinalabs.flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import org.avinalabs.flex.facilitators.FHomeActivity;
import org.avinalabs.flex.participants.PHomeActivity;
import org.avinalabs.flex.utilities.UserTypeCallback;
import org.avinalabs.flex.utilities.UserType;
import org.avinalabs.flex.utilities.UserUtil;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // TODO: Some UI Stuff

        Log.d(TAG, "This happened");

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Log.d(TAG, "User is signed in(authenticated)");
            UserUtil.getUserType(new redirectUser());
        } else {
            Log.d(TAG, "New User! Proceed to Login.");
            Intent signIn = new Intent(this, LoginActivity.class);
            startActivity(signIn);
        }
    }

    // Let's make this a lambda function later.
    class redirectUser implements UserTypeCallback {
        @Override
        public void completion(UserType userType) {
            if (userType == UserType.facilitator) {
                Log.d(TAG, "Facilitator found!");
                Intent toFHome = new Intent(FirstActivity.this, FHomeActivity.class);
                startActivity(toFHome);
            }
            else if(userType == UserType.participant) {
                Log.d(TAG, "Participant found!");
                Intent toPHome = new Intent(FirstActivity.this, PHomeActivity.class);
                startActivity(toPHome);
            }
            else if (userType == UserType.newUser) {
                Log.d(TAG, "User Not Found in Database – assume New User! Proceed to Login.");
                Intent toLogin = new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        }
    }
}

