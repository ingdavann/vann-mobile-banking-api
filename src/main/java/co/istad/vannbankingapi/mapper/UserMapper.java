package co.istad.vannbankingapi.mapper;

import co.istad.vannbankingapi.domain.User;
import co.istad.vannbankingapi.features.user.dto.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // SourceType = UserCreateRequest (Param)
    // TargetType = User (ReturnType)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest); //from dto to entity
}
