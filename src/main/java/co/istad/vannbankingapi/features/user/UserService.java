package co.istad.vannbankingapi.features.user;

import co.istad.vannbankingapi.domain.User;
import co.istad.vannbankingapi.features.user.dto.UserCreateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService {
    void createNewUser(UserCreateRequest userCreateRequest);

}
