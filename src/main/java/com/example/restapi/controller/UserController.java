package com.example.restapi.controller;

import com.example.restapi.config.security.JwtTokenProvider;
import com.example.restapi.domain.User;
import com.example.restapi.dto.UserDto;
import com.example.restapi.exception.InvalidParameterException;
import com.example.restapi.exception.UserNotFoundException;
import com.example.restapi.repository.UserRepository;
import com.example.restapi.service.UserService;
import com.example.restapi.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private ModelMapper modelMapper = new ModelMapper();



    @PostMapping(path = "/join", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User addUser(@RequestBody @Valid UserDto.RegisterUserInfo userDto, BindingResult result) throws ParseException{

        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }

        return userRepository.save(new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword())));
    }


    @PostMapping("/login")
    public String login(@RequestBody UserDto.LoginUser userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일"));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호");
        }
        return jwtTokenProvider.createToken(user);
    }


    @GetMapping
    @ResponseBody
    public List<UserDto> getUsers() {
        List<User> users = userService.getUsers();

        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable String userId) {
        return userService.getUserById(Long.parseLong(userId));
    }


//    @PutMapping("/{userId}")
//    public User updateUser(@PathVariable Long userId, User user) {
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId) {
//    }



    @GetMapping("/token")
    public String testJWT(String code) {
        return "ok";
    }


    // User 엔티티 -> DTO
    // 이너 클래스들에 대한 전환 함수를 통합할 수 있는 방법은?
    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class); // 엔티티와 DTO 객체 매핑
        return userDto;
    }

    private User convertToEntity(UserDto.RegisterUserInfo userDto) throws ParseException {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
