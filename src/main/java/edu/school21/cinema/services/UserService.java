package edu.school21.cinema.services;

import edu.school21.cinema.models.*;

import java.sql.Timestamp;

public interface UserService {
    int signUp(User user);
    User signIn(String phone, String password, String ip, Timestamp date);
}
