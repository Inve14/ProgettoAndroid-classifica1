package com.example.progettoandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ClassificaModel {

    private List<String> contacts;

    public ClassificaModel() {
        contacts = new ArrayList<>();
    }


    public void simulateLoadData(List<String> help) {
        contacts.addAll(help);
    }


    public int getContactsCount() {
        return contacts.size();
    }

    public String getContact(int index) {
        return contacts.get(index);
    }
}
