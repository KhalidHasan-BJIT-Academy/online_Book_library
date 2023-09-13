package com.khalid.onlinebooklibraryapplication.service;

import com.khalid.onlinebooklibraryapplication.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto) throws Exception;
    UserDto getUser(String email);
    UserDto getUserByUserId(Long id) throws Exception;
}
