package com.ellis.memberplanet.firebase;

import android.util.Log;

import com.ellis.memberplanet.session.SharedPrefManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by joenellis on 10/08/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {

        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(refreshedToken);


    }

}
