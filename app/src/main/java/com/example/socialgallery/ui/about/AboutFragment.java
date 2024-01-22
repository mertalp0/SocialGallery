package com.example.socialgallery.ui.about;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.socialgallery.R;

public class AboutFragment extends Fragment {

    private AboutViewModel mViewModel;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        Button linkedinbtn = view.findViewById(R.id.btnLinkedin);
        linkedinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLinkedin = "https://www.linkedin.com/in/mert-alp-08015a244/";
                Intent intentLinkedin  = new Intent(Intent.ACTION_SEND);
                intentLinkedin.setData(Uri.parse(urlLinkedin));
                startActivity(intentLinkedin);
            }
        });
        Button githubBtn = view.findViewById(R.id.btnGithub);
        githubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLinkedin = "https://github.com/mertalp0";
                Intent intentLinkedin  = new Intent(Intent.ACTION_SEND);
                intentLinkedin.setData(Uri.parse(urlLinkedin));
                startActivity(intentLinkedin);
            }
        });
        return  view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);
        // TODO: Use the ViewModel
    }

}