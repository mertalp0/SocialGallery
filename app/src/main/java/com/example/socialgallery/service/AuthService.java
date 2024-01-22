package com.example.socialgallery.service;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore ;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private FirebaseAuth auth;
    private Activity mActivity;
    public AuthService(Activity activity) {
        auth = FirebaseAuth.getInstance();
        mActivity = activity;
    }
    public FirebaseUser currentUser() {
        return auth.getCurrentUser();
    }
    public void login(String email, String password, final AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mActivity, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        callback.onSuccess(user);
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    public void signup(String email, String password, String firstName , String lastName, final AuthCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mActivity, task -> {
                    if (task.isSuccessful()) {

                        FirebaseFirestore db = FirebaseFirestore.getInstance() ;
                        Map<String,Object> user = new HashMap<>();
                        user.put("name" , firstName ) ;
                        user.put("lastName" , lastName ) ;
                        user.put("email" , email ) ;
                        user.put("password" , password ) ;

                    db.collection("users").document(auth.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            FirebaseUser user = auth.getCurrentUser();
                            callback.onSuccess(user);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            callback.onFailure(task.getException().getMessage());

                        }
                    });
                    } else {
                       callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    public void logout() {
        auth.signOut();
    }
}
