package com.example.progettoandroid;

import java.util.ArrayList;
import java.util.List;

public class Model {

    List<String> contacts;
    //bisogna creare una lista di Player con caratteristiche "nome_player" e "punteggio_player" --> bisogna creare la classe Player

    public Model() {
        contacts = new ArrayList<>();
    }

    public void simulateLoadData(List<String> help) {
        for (int i=0; i<help.size(); i++){
            contacts.add(help.get(i));
        }
    }

    public int getContactsCount() {
        return contacts.size();
    }

    public String getContact(int index) {
        return contacts.get(index);
    }
}
