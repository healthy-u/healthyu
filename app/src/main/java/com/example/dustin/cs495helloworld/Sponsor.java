package com.example.dustin.cs495helloworld;

public class Sponsor {
    Long id = 0L;
    String username;
    String email;
    long campus_id = 0L;

    public Sponsor(Long ID, String user_name, String emailAddress, Long campusId) {
        id = ID;
        username = user_name;
        email = emailAddress;
        campus_id = campusId;
    }

    public Sponsor(Long ID, String user_name, String emailAddress) {
        this(0L, user_name, emailAddress, 0L);
    }

    public Sponsor(String user_name, String emailAddress) {
        this(0L, user_name, emailAddress);
    }

    static Sponsor loggedInSponsor = null;
}