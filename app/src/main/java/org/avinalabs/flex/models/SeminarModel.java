package org.avinalabs.flex.models;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SeminarModel {

    private static final String TAG = "SeminarLog";

    private Date dateCreated;
    private boolean enabled;
    private String name;
    private String facilitatorId;
    private String questionSetLink;
    private boolean closed;
    private String[] participantList;
    private String code;
    private String documentID;


    public SeminarModel(){

    }

    public SeminarModel(String id, Date dateCreated, boolean enabled, String name, String facilitatorId, String questionSetLink, boolean closed, String[] participantList, String code)
    {
        this.documentID = id;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.name = name;
        this.facilitatorId = facilitatorId;
        this.questionSetLink = questionSetLink;
        this.closed = closed;
        this.participantList = participantList;
        this.code = code;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public String getFacilitatorId() {
        return facilitatorId;
    }

    public String getQuestionSetLink() {
        return questionSetLink;
    }

    public boolean isClosed() {
        return closed;
    }

    public String[] getParticipantList() {
        return participantList;
    }

    public String getCode() {
        return code;
    }


    public static String generateCode() {
        String letters = "ABCDEFGHJKLMNPQRSTUVWXYZ123456789";
        Random random = new Random();
        String code = "";
        for(int i = 0; i < 6; i++) {
            code = code + letters.charAt(random.nextInt(letters.length()));
        }
        return code;
    }

    public void writeParticipant(String partUUID) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference seminarRef = db.collection("seminars").document(documentID);
        final Map<String, Object> addUserToArrayMap = new HashMap<>();

        // TODO: check to see partUUID has to be passed into arrayUnion, or an array containing partUUID
        addUserToArrayMap.put("participantList", FieldValue.arrayUnion(partUUID));
        seminarRef.update(addUserToArrayMap);
    }

    public static void joinSeminar(String code, final String participantId, final SeminarCallback callback) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference seminarRef = db.collection("seminars");
        Query query = seminarRef.whereEqualTo("code", code);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    QuerySnapshot data = task.getResult();
                    if (data != null) {
                        SeminarModel first = data.getDocuments().get(0).toObject(SeminarModel.class);
                        first.writeParticipant(participantId);
                        callback.completion(first);
                    }
                    else {
                        Log.d(TAG, "Query result was null");
                        callback.completion(null);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Query fetching failed.");
                callback.completion(null);
            }
        });
    }
}
