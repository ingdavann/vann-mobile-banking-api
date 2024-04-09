package co.istad.vannbankingapi.mapper;

import co.istad.vannbankingapi.domain.User;
import co.istad.vannbankingapi.domain.UserAccount;
import co.istad.vannbankingapi.features.user.dto.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // SourceType = UserCreateRequest (Param)
    // TargetType = User (ReturnType)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest); //from dto to entity
    UserUpdateProfileResponse toUserUpdateProfileResponse(UserUpdateProfileResponse userUpdateProfileResponse);
    UserDetailResponse toUserDetailResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)

    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

//    UserResponse toUserResponse(User user);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }

    UserResponse toUserResponse(User user);
}
