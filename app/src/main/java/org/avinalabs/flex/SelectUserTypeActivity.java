package org.avinalabs.flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.avinalabs.flex.facilitators.FCreateProfileActivity;
import org.avinalabs.flex.participants.PCreateProfileActivity;

public class SelectUserTypeActivity extends AppCompatActivity {

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
                // For now, just redirect to make profile.
                Intent toPCreateProfile = new Intent(SelectUserTypeActivity.this, PCreateProfileActivity.class);
                startActivity(toPCreateProfile);
            }
        });

        facilitatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toFCreateProfile = new Intent(SelectUserTypeActivity.this, FCreateProfileActivity.class);
                startActivity(toFCreateProfile);
            }
        });

    }
}
