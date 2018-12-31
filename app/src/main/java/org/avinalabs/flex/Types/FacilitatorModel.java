package org.avinalabs.flex.Types;

// Non singleton class, to use for Documentsnapshot.toObject()

public class FacilitatorModel {

    private String uid;
    private String firstName;
    private String lastName;

    public FacilitatorModel() {
        this.uid = "";
        this.firstName = "";
        this.lastName = "";
    }

    public FacilitatorModel(String uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUid() {
        return this.uid;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

}
