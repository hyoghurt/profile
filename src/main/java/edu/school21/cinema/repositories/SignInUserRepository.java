package edu.school21.cinema.repositories;

import edu.school21.cinema.models.SignIn;

import java.util.List;

public interface SignInUserRepository {
    List<SignIn> findAllByUserId(Long userId);
    int save(SignIn signIn);
}
