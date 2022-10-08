package edu.school21.cinema.services;

import edu.school21.cinema.models.SignIn;
import edu.school21.cinema.models.SignInDTO;
import edu.school21.cinema.repositories.SignInUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SignInUserServiceImplService implements SignInUserService {
    private final SignInUserRepository signInUserRepository;

    @Override
    public Integer save(SignIn signIn) {
        return signInUserRepository.save(signIn);
    }

    @Override
    public List<SignInDTO> getListSignInDTO(Long userId) {
        List<SignIn> signIns = signInUserRepository.findAllByUserId(userId);

        DateFormat fmtDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        DateFormat fmtTime = new SimpleDateFormat("HH:mm:ss");

        return signIns.stream().map(e -> new SignInDTO(fmtDate.format(e.getDate()),
                fmtTime.format(e.getDate()), e.getIp())).collect(Collectors.toList());
    }
}
