package co.istad.vannbankingapi.features.user;

import co.istad.vannbankingapi.base.BasedMessage;
import co.istad.vannbankingapi.features.user.dto.UserCreateRequest;
import co.istad.vannbankingapi.features.user.dto.UserResponse;
import co.istad.vannbankingapi.features.user.dto.UserUpdateProfileResponse;
import co.istad.vannbankingapi.features.user.dto.UserUpdateRequest;
import co.istad.vannbankingapi.mapper.UserMapper;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.data.domain.Page;

public interface UserService {
    void createNewUser(UserCreateRequest userCreateRequest);
    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    UserResponse findByUuid(String uuid);

    BasedMessage blockByUuid(String uuid);

    void deleteUserByUuid(String uuid);

    Page<UserResponse> findList(int page, int limit);

}
