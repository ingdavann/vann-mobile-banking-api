package co.istad.vannbankingapi.features.user;

import co.istad.vannbankingapi.base.BasedMessage;
import co.istad.vannbankingapi.features.user.dto.UserCreateRequest;
import co.istad.vannbankingapi.features.user.dto.UserResponse;
import co.istad.vannbankingapi.features.user.dto.UserUpdateProfileResponse;
import co.istad.vannbankingapi.features.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest){
        userService.createNewUser(userCreateRequest);
    }

    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }

    @GetMapping("/{uuid}")
    UserResponse findByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    void deleteUserByUuid(@PathVariable String uuid){
        userService.deleteUserByUuid(uuid);
    }

    @GetMapping
    Page<UserResponse> findList(@RequestParam int page, int limit){
        return userService.findList(page, limit);
    }

//    @PatchMapping("/{uuid}")
//    UserUpdateProfileResponse editByProfileByUuid(@Valid @RequestBody @PathVariable String uuid, UserCreateRequest userUpdateProfileResponse){
//        return userService.editeProfileByUuid(uuid, userUpdateProfileResponse);
//    }

}
