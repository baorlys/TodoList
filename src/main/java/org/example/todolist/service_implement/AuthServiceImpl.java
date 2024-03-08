package org.example.todolist.service_implement;

import org.example.todolist.dto.request.AuthenticationRequest;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.SignupResponse;
import org.example.todolist.enums.RegistrationSource;
import org.example.todolist.model.Role;
import org.example.todolist.model.User;
import org.example.todolist.repository.RoleRepository;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.service.AuthService;
import org.example.todolist.service.StateService;
import org.example.todolist.service.UserService;
import org.example.todolist.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StateService stateService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public SignupResponse createUser(SignupRequest signupRequest) throws Exception {
        if(userRepository.findByEmail(signupRequest.getEmail()) != null) {
            throw new Exception("Email already exists");
        }
        User user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        return getSignupResponse(signupRequest, user);
    }

    @Override
    public SignupResponse createUserWithOAuth2(String email, String username) throws Exception {
        if(userRepository.findByEmail(email) != null) {
            return mapper.map(userRepository.findByEmail(email), SignupResponse.class);
        }
        User user = new User();
        return getSignupResponse(new SignupRequest(email,"",username), user);
    }

    private SignupResponse getSignupResponse(SignupRequest signupRequest, User user) throws Exception {
        Role role = roleRepository.findById(2).orElse(null);
        user.setEmail(signupRequest.getEmail());
        user.setRole(role);
        user.setUsername(signupRequest.getUsername());
        user.setMobileHidden(true);
        user.setIsHidden(false);
        user.setLastloginAt(new Timestamp(System.currentTimeMillis()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        User createdUser = userRepository.save(user);
        stateService.createDefautStates(createdUser.getId());
        return mapper.map(createdUser, SignupResponse.class);
    }


    @Override
    public String login(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Email or password is incorrect");
        }
        userService.updateLastLogin(authenticationRequest.getEmail());
        return jwtUtil.generateToken(authenticationRequest.getEmail());
    }
}
