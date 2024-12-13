package com.rksp8.userservice.service.user;

import com.rksp8.userservice.client.PostServiceClient;
import com.rksp8.userservice.dto.PostDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PostServiceClient postServiceClient;

    @Override
    @Transactional
    public void createUser(String username) {
        User newUser = userRepository.findByUsername(username).orElse(null);

        if(newUser == null) {

            newUser = new User();

            newUser.setUsername(username);

            newUser.setRole(Role.USER);

            newUser.setProvider(Provider.GOOGLE);

            newUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(newUser);

            log.info("User {} created", username);
        }

        userRepository.save(newUser);
    }


    @Override
    @Transactional
    public UserDto getUserByUsername(String username) {
        List<PostDto> userPosts = postServiceClient.getPostsByAuthor(username);
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")), userPosts);
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    List<PostDto> userPosts = postServiceClient.getPostsByAuthor(user.getUsername());
                    return userMapper.toDto(user, userPosts);
                })
                .toList();
    }
    @Override
    @Transactional
    public void deleteUserByUsername(String username) {

        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }

    @Override
    @Transactional
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();


        if (!(authentication.getPrincipal() instanceof CustomOauth2User)) {
            throw new IllegalStateException("Current user is not authenticated with OAuth2");
        }

        CustomOauth2User oauth2User = (CustomOauth2User) principal;
        Map<String, Object> attributes = oauth2User.getAttributes();

        String username = (String) attributes.get("name");
        List<PostDto> userPosts = postServiceClient.getPostsByAuthor(username);

        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")), userPosts);
    }
}
