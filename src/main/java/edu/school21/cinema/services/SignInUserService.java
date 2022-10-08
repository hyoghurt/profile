package edu.school21.cinema.services;

import edu.school21.cinema.models.SignIn;
import edu.school21.cinema.models.SignInDTO;

import java.util.List;

public interface SignInUserService {
    Integer save(SignIn signIn);
    List<SignInDTO> getListSignInDTO(Long userId);
}
