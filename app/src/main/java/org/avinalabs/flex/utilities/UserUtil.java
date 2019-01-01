package org.avinalabs.flex.utilities;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.avinalabs.flex.models.Facilitator;
import org.avinalabs.flex.models.FacilitatorModel;
import org.avinalabs.flex.models.Participant;
import org.avinalabs.flex.models.ParticipantModel;

public class UserUtil {

    private static final String TAG = "UtilLog";

    private static FirebaseAuth mAuth;

    public static void getUserType(final UserTypeCallback callback) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null) {
            Log.d(TAG, "New User");
            callback.completion(UserType.newUser);
        }
        else {
            Log.d(TAG, "User with unknown type found.");
            final String uid = currentUser.getUid();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            DocumentReference participantRef = db.document("participants/" + uid);
            participantRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful() && task.getResult().exists()) {
                        Log.d(TAG, "Participant Found.");
                        DocumentSnapshot documentSnapshot = task.getResult();

                        ParticipantModel participant = documentSnapshot.toObject(ParticipantModel.class);
                        Participant.updateShared(participant.getUid(), participant.getFirstName(), participant.getLastName());

                        callback.completion(UserType.participant);
                    }
                    else {
                        DocumentReference facilitatorRef = db.document("facilitators/" + uid);
                        facilitatorRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful() && task.getResult().exists()) {
                                    Log.d(TAG, "User is a facilitator");
                                    DocumentSnapshot documentSnapshot = task.getResult();

                                    FacilitatorModel facilitator = documentSnapshot.toObject(FacilitatorModel.class);
                                    Facilitator.updateShared(facilitator.getUid(), facilitator.getFirstName(), facilitator.getLastName());

                                    callback.completion(UserType.facilitator);
                                }
                                else
                                {
                                    Log.d(TAG, "Assumed new user.");
                                    callback.completion(UserType.newUser);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "Couldn't fetch from Firestore(facilitatorRef)");
                                callback.completion(null);
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "Couldn't fetch from Firestore(participantRef)");
                    callback.completion(null);
                }
            });
        }
    }

    public static void logLog() {
        // TODO
    }

}


