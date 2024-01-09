package com.example.progettoandroid;

public class User {
    private int experience, life, profileversion, uid;

    public void setUser(int experience, int life, int profileversion, int uid) {
        this.experience = experience;
        this.life = life;
        this.profileversion = profileversion;
        this.uid = uid;
    }

    public User(int experience, int life, int profileversion, int uid) {
        //constructor
        this.experience = experience;
        this.life = life;
        this.profileversion = profileversion;
        this.uid = uid;

    }

    public int getExperience() {
        return experience;
    }

    public int getLife() {
        return life;
    }

    public int getProfileversion() {
        return profileversion;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return  getUid() + " " + getExperience();
    }

}
