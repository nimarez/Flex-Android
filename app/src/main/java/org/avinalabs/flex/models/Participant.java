package org.avinalabs.flex.models;


public class Participant {

    private String uid;
    private String firstName;
    private String lastName;
    private static final Participant shared = new Participant();

    private Participant() {
        this.uid = "";
        this.firstName = "";
        this.lastName = "";
    }

    public static Participant getShared() {
        return shared;
    }

    public static void updateShared(String uid, String firstName, String lastName) {
        shared.uid = uid;
        shared.firstName = firstName;
        shared.lastName = lastName;
    }

    public String getUid() {
        return shared.uid;
    }

    public String getFirstName() {
        return shared.firstName;
    }

    public String getLastName() {
        return shared.lastName;
    }


}
