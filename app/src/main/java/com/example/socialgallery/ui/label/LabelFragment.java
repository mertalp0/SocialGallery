package com.example.socialgallery.ui.label;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialgallery.LoginActivity;
import com.example.socialgallery.databinding.FragmentLabelBinding;
import com.example.socialgallery.service.FirebaseService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.example.socialgallery.R;

import org.w3c.dom.Text;

public class LabelFragment extends Fragment {
    private FragmentLabelBinding binding;
    private FirebaseService firebaseService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLabelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayout linearLayout = binding.layout;
        firebaseService = new FirebaseService();

        firebaseService.getLabels(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w("TAG", "Listen failed.", error);
                    return;
                }

                linearLayout.removeAllViews();

                for (QueryDocumentSnapshot document : value) {
                    LabelViewModel label = document.toObject(LabelViewModel.class);
                    TextView text = new TextView(getActivity());
                    text.setText(label.getLabelText());
                    linearLayout.addView(text);
                }
            }
        });

        Button added = binding.addLabelBtn;
        added.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText labeltext = binding.addlabeltxt;
                String labelEditText = labeltext.getText().toString();
                EditText descriptiontxttext = binding.descriptiontxt;
                String descriptionEditText = descriptiontxttext.getText().toString();
                if(labelEditText.isEmpty()){
                    Toast.makeText(getActivity(), "Label Boş Olamaz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(descriptionEditText.isEmpty()){
                    Toast.makeText(getActivity(), "Description Boş Olamaz", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseService.addLabel(labelEditText,descriptionEditText,
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getActivity(), "Label başarıyla eklendi!", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {Toast.makeText(getActivity(), "Label ekleme başarısız! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return root;
    }
}
