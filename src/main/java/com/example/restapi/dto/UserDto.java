package com.example.restapi.dto;

import com.example.restapi.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter @Setter
@ToString
public class UserDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Long id;
    private String name;
    private String email;
    private String password;


    /**
     * 회원가입 dto
     */
    @Getter
    public static class RegisterUserInfo {

        @NotBlank(message = "email required")
        private String email;

        @NotBlank(message = "name required")
        private String name;

        @NotBlank(message = "password required")
        private String password;
    }

    /**
     * 로그인 dto
     */
    @Getter
    public static class LoginUser {

        @NotBlank(message = "email required")
        private String email;

        @NotBlank(message = "password required")
        private String password;
    }


    @Getter
    public static class ChangeEmailInfo {
        private Long userId;
        private String oldEmail;
        private String newEmail;
    }


    @Getter @Setter
    public static class MyInfo {
        private String name;
        private String password;
    }
}
