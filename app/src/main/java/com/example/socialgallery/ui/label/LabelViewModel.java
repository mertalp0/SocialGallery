package com.example.socialgallery.ui.label;

import androidx.lifecycle.ViewModel;

public class LabelViewModel extends ViewModel {


    private  String labelText ;
    private  String description ;



    public  LabelViewModel(){
        this.labelText = null;
        this.description = null;
    }
    public  LabelViewModel(String label , String description){
        this.labelText = label;
        this.description = description;
    }
    public  String getLabelText(){
        return  labelText ;
    }

    public  String getDescription(){
        return  description ;
    }

    public  void  setLabelText (String text){
        this.labelText = text ;
    }




}