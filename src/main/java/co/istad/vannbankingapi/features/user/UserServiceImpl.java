package co.istad.vannbankingapi.features.user;

import co.istad.vannbankingapi.base.BasedMessage;
import co.istad.vannbankingapi.domain.Role;
import co.istad.vannbankingapi.domain.User;
import co.istad.vannbankingapi.features.user.dto.UserCreateRequest;
import co.istad.vannbankingapi.features.user.dto.UserResponse;
import co.istad.vannbankingapi.features.user.dto.UserUpdateRequest;
import co.istad.vannbankingapi.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
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
        if (userRepository.existsByNationalCardId(userCreateRequest.nationalCardId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National ID card has already existed!"
            );
        }

        // Check Student ID card if exist
        if (userRepository.existsByStudentIdCard(userCreateRequest.studentIdCard())){
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

        //DTO pattern (mapstruct ft. lombok)
        User user = userMapper.fromUserCreateRequest(userCreateRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setProfileImage("me.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setIsDeleted(false);

        // set default user role
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Role has been not found!"
                                ));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

    }

    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest) {
        // check uuid if exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        log.info("before user: {}", user);
        userMapper.fromUserUpdateRequest(userUpdateRequest, user);
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse findByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        return userMapper.toUserResponse(user);

    }

    @Transactional
    @Override
    public BasedMessage blockByUuid(String uuid) {
        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User has not been found!");
        }
        userRepository.blockByUuid(uuid);
        return new BasedMessage("User has been blocked");
    }

    @Override
    public void deleteUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has not been found!"
                ));
        userRepository.delete(user);
    }

    @Override
    public Page<UserResponse> findList(int page, int limit) {
        // Create pageRequest object
        PageRequest pageRequest = PageRequest.of(page, limit);

        //Invoke findAll(pageRequest)
        Page<User> users = userRepository.findAll(pageRequest);
        return users.map(userMapper::toUserResponse);
    }


}