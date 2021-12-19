package com.webmuffins.rtsx.board.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.webmuffins.rtsx.board.dto.user.UserDto;

@FeignClient("security-service")
public interface SecurityServiceProxyClient {

    @GetMapping("/users")
    List<UserDto> getAllUsers(@RequestHeader("Authorization") String token);

}
