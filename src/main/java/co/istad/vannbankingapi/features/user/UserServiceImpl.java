package co.istad.vannbankingapi.features.user;

import co.istad.vannbankingapi.features.user.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public void createNewUser(UserCreateRequest userCreateRequest) {
        // Check phone number if exist
        if (userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }

        // Check National ID if exist
        if (userRepository.existsByNationalCardId(userCreateRequest.nationalId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National ID card has already existed!"
            );
        }

        // Check Student ID card if exist
        if (userRepository.existsByStudentCardId(userCreateRequest.studentIdCard())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already existed!"
            );
        }

        if (!userCreateRequest.password()
                .equals(userCreateRequest.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }



    }
}
