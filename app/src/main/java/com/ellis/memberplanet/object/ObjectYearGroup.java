package com.ellis.memberplanet.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joenellis on 18/05/2018.
 */

public class ObjectYearGroup {

    private ObjectMembers objectMembers;

    public ObjectYearGroup(ObjectMembers objectMembers) {
        this.objectMembers = objectMembers;
    }

    public ObjectMembers getObjectMembers() {
        return objectMembers;
    }
}
