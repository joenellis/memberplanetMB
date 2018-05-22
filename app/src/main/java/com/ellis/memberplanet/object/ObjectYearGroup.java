package com.ellis.memberplanet.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joenellis on 18/05/2018.
 */

public class ObjectYearGroup {

    @SerializedName("")
    private ArrayList<ObjectMembers> objectMembers;

    public ArrayList<ObjectMembers> getObjectMembers() {
        return objectMembers;
    }

    public ObjectYearGroup(ArrayList<ObjectMembers> objectMembers) {
        this.objectMembers = objectMembers;
    }

}
