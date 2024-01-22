package com.example.socialgallery.service;

import androidx.annotation.NonNull;

import com.example.socialgallery.ui.label.LabelViewModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseService {


private FirebaseFirestore db ;
private  CollectionReference labelRef ;


public  FirebaseService (){
    db = FirebaseFirestore.getInstance();
    labelRef = db.collection("label");
}

public  void  getLabels(EventListener<QuerySnapshot> listener){
    labelRef.addSnapshotListener(listener);
}

public void addLabel (String labelText , String description , OnSuccessListener<DocumentReference> successListener , OnFailureListener failureListener){
    labelRef.whereEqualTo("labelText" , labelText).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful() && task.getResult().size()>0   ){
                failureListener.onFailure(new Exception("Bu label zaten mevcut"));

            }
            else {
                LabelViewModel labelViewModel = new LabelViewModel(labelText , description);
                labelRef.add(labelViewModel).addOnSuccessListener(successListener).addOnFailureListener(failureListener);
            }


        }
    });

}

















}
