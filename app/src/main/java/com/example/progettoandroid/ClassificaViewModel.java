package com.example.progettoandroid;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClassificaViewModel extends AndroidViewModel {

    private ClassificaModel theModel;

    public ClassificaViewModel(@NonNull Application application) {
        super(application);
        theModel = new ClassificaModel();
    }


    public void setHelp(List<String> help) {
        theModel.simulateLoadData(help);
    }


    public int getContactsCount() {
        return theModel.getContactsCount();
    }

    public String getContact(int index) {
        return theModel.getContact(index);
    }
}
