package org.avinalabs.flex;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.avinalabs.flex.Types.Facilitator;
import org.avinalabs.flex.Types.FacilitatorModel;
import org.avinalabs.flex.Types.Participant;
import org.avinalabs.flex.Types.ParticipantModel;

enum UserType {
    participant,
    facilitator,
    newUser
}


interface CompletionCallback {

    void completion(UserType usertype);
}


public class UserUtil {

    private static FirebaseAuth mAuth;
    private static GoogleSignInClient mGoogleSignInClient;

    public static void getUserType(final CompletionCallback callback) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            Out.d("New User");
            callback.completion(UserType.newUser);
        }
        else {
            final String uid = currentUser.getUid();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference participantRef = db.document("participants/" + uid);
            participantRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful() && task.getResult().exists()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        Out.d("Fetched document Snapshot for participant");


                        ParticipantModel participant = documentSnapshot.toObject(ParticipantModel.class);
                        Participant.updateShared(participant.getUid(), participant.getFirstName(), participant.getLastName());
                        Out.d("Updated Participant Singleton");

                        callback.completion(UserType.participant);
                    }
                    else {
                        Out.d("User is a facilitator");
                        DocumentReference facilitatorRef = db.document("facilitators/" + uid);
                        facilitatorRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful() && task.getResult().exists()) {
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    Out.d("Fetched document Snapshot for facilitator");

                                    FacilitatorModel facilitator = documentSnapshot.toObject(FacilitatorModel.class);
                                    Facilitator.updateShared(facilitator.getUid(), facilitator.getFirstName(), facilitator.getLastName());
                                    Out.d("Update Facilitator Singleton");

                                    callback.completion(UserType.facilitator);
                                }
                                else
                                {
                                    Out.d("Assumed new user.");
                                    callback.completion(UserType.newUser);
                                }
                            }
                        });

                    }
                }
            });
        }


    }

    public static void logout() {
        // TODO
    }

}


