package org.avinalabs.flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.avinalabs.flex.facilitators.FCreateProfileActivity;
import org.avinalabs.flex.facilitators.FRequestAccessActivity;
import org.avinalabs.flex.facilitators.FThankForEnrollingActivity;
import org.avinalabs.flex.participants.PCreateProfileActivity;

public class SelectUserTypeActivity extends AppCompatActivity {

    private static final String TAG = "SUTLog";

    private Button participantButton;
    private Button facilitatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);

        participantButton = findViewById(R.id.SUT_participant_button);
        facilitatorButton = findViewById(R.id.SUT_facilitator_button);

        participantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // For now(meaning no verification?), just redirect to make profile.
                startActivity(new Intent(SelectUserTypeActivity.this, PCreateProfileActivity.class));
            }
        });

        facilitatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyFacilitator()) {
                    startActivity(new Intent(SelectUserTypeActivity.this, FThankForEnrollingActivity.class));
                }
                else {
                    startActivity(new Intent(SelectUserTypeActivity.this, FRequestAccessActivity.class));
                }
            }
        });
    }

    // Returns True for valid facilitators
    // TODO Complete this without hard coding. Probably use regex to see if their email contains .edu
    private boolean verifyFacilitator() {
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if(email != null) {
            if(email.contains("nimarezaeian1@gmail.com")) {
                // Nima's gonna facilitate some people eh
                return true;
            }
            else {
                // Someone other than Nima has signed in.
                return false;
            }
        }
        else {
            Log.d(TAG, "Email not found.");
            return false;
        }
    }
}
