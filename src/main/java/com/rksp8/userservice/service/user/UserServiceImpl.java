package com.rksp8.userservice.service.user;

import com.rksp8.userservice.dto.UserDto;
import com.rksp8.userservice.entity.Provider;
import com.rksp8.userservice.entity.Role;
import com.rksp8.userservice.entity.User;
import com.rksp8.userservice.mapper.UserMapper;
import com.rksp8.userservice.repository.UserRepository;
import com.rksp8.userservice.util.CustomOauth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(String username) {

        if (userRepository.findByUsername(username).isEmpty()) {

            User user = new User();

            user.setUsername(username);

            user.setRole(Role.USER);

            user.setProvider(Provider.GOOGLE);

            user.setCreatedAt(LocalDateTime.now());

            userRepository.save(user);

            log.info("User {} created", username);
        }
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public void deleteUserByUsername(String username) {

        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        System.out.println("Principal type: " + principal.getClass().getName());

        if (!(authentication.getPrincipal() instanceof CustomOauth2User)) {
            throw new IllegalStateException("Current user is not authenticated with OAuth2");
        }

        CustomOauth2User oauth2User = (CustomOauth2User) principal;
        Map<String, Object> attributes = oauth2User.getAttributes();

        return userMapper.toDto(userRepository.findByUsername((String) attributes.get("username")).orElseThrow(() -> new RuntimeException("User not found")));
    }
}
