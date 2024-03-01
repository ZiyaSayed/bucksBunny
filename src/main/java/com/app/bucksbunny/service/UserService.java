package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.User;
import com.app.bucksbunny.exceptions.UserNotFoundException;
import com.app.bucksbunny.repository.UserRepository;
import com.app.bucksbunny.serviceInterface.IUser;
import com.app.bucksbunny.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class UserService implements IUser, UserDetailsService {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User addUser(User user){

        user.setRoles("ROLE_USER");
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        user.setCreateDate(ZonedDateTime.now());
        userRepo.save(user);

        return user;
    }

    @Override
    public User addAdmin(User user){

        user.setRoles("ROLE_ADMIN");
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        user.setCreateDate(ZonedDateTime.now());
        userRepo.save(user);

        return user;
    }

    @Override
    public void updatePassword(String userEmail, String newPassword) {

        Optional<User> possibleUser = Optional.ofNullable(userRepo.findByUserEmail(userEmail));

        User user = possibleUser.orElseThrow(UserNotFoundException::new);
        user.setUserPassword(encoder.encode(newPassword));
        userRepo.save(user);

    }


    @Override
    public User updateUserName(String userEmail, String userName) {

        Optional<User> possibleUser = Optional.ofNullable(userRepo.findByUserEmail(userEmail));

        User user = possibleUser.orElseThrow(UserNotFoundException::new);
        user.setUserName(userName);
        return userRepo.save(user);


    }

    @Override
    public String getUserName(String userEmail) {

        Optional<User> possibleUser = Optional.ofNullable(userRepo.findByUserEmail(userEmail));
        if(possibleUser.isPresent()){
            User user = possibleUser.get();
            return user.getUserName();
        }
        else{
            throw new UserNotFoundException("User does not exist");
        }
    }

    @Override
    public User getUserByEmail(String userEmail) {
        Optional<User> possibleUser = Optional.ofNullable(userRepo.findByUserEmail(userEmail));
        if(possibleUser.isPresent()){
            User user = possibleUser.get();
            return user;
        }
        else{
            throw new UserNotFoundException("User does not exist");
        }
    }

    @Override
    public void deleteUser(String userEmail) {
        Optional<User> possibleUser = Optional.ofNullable(userRepo.findByUserEmail(userEmail));

        if(possibleUser.isPresent()){
            userRepo.deleteByUserEmail(userEmail);
        }
        else{
            throw new UserNotFoundException("User does not exist");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        Optional<User> userDetail = Optional.ofNullable(userRepo.findByUserEmail(userEmail));

        return userDetail.map(UserInfoService::new).orElseThrow(()-> new UsernameNotFoundException("User not found"));

    }

    private String getUserEmailFromToken(String token){
        String jwtToken = token.replace("Bearer ", "");
        // Extract user information from the token
        String userEmail = jwt.extractUserEmail(jwtToken);
        return userEmail;
    }

}
