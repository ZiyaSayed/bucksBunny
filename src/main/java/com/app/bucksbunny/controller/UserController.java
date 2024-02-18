package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.AuthRequest;
import com.app.bucksbunny.entity.User;
import com.app.bucksbunny.request.UpdateUserBody;
import com.app.bucksbunny.request.UpdateUserPasswordBody;
import com.app.bucksbunny.response.APIResponse;
import com.app.bucksbunny.response.JWTResponse;
import com.app.bucksbunny.service.UserService;
import com.app.bucksbunny.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

   @GetMapping("/welcome")
    public String welcome(){
       return "Welcome, this endpoint is not secure";
   }

   @PostMapping("/new")
    public User addNewUser(@RequestBody User user){
       return service.addUser(user);
   }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<APIResponse> profile(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " prefix from the token
        String jwtToken = token.replace("Bearer ", "");

        // Extract user information from the token
        String userEmail = jwt.extractUserEmail(jwtToken);
        User user = service.getUserByEmail(userEmail);

        APIResponse response = new APIResponse("", true, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

   @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody AuthRequest request){
        //authenticate
        doAuthenticate(request);

        String token = jwt.generateToken(request.getUserEmail());
        JWTResponse jwtResponse = JWTResponse.builder()
                .jwtToken(token)
                .username(request.getUserEmail()).build();

        APIResponse response = new APIResponse("", true, jwtResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update-username")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<APIResponse> updateUserName(@RequestHeader("Authorization") String token, @RequestBody UpdateUserBody updateUser){


            String userEmail = getUserEmailFromToken(token);
            User user = service.updateUserName(userEmail, updateUser.getUserName());

            APIResponse response = new APIResponse("", true, user);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PatchMapping("/update-password")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<APIResponse> updateUserPassword(@RequestHeader("Authorization") String token, @RequestBody UpdateUserPasswordBody updateUser){

        String userEmail = getUserEmailFromToken(token);
        service.updatePassword(userEmail, updateUser.getPassword());

        APIResponse response = new APIResponse("", true, "Password Updated Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/remove")
    @Transactional
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<APIResponse> deleteUser(@RequestHeader("Authorization") String token) throws Exception {
        try {
            String userEmail = getUserEmailFromToken(token);
            service.deleteUser(userEmail);
            APIResponse response = new APIResponse("",  true, "Password Updated Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // Return a more informative response
            APIResponse response = new APIResponse("",  false, "Access Denied");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    private void doAuthenticate(AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authRequest.getUserEmail(), authRequest.getPassword());
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }
    }


    private String getUserEmailFromToken(String token){
        String jwtToken = token.replace("Bearer ", "");
        // Extract user information from the token
        String userEmail = jwt.extractUserEmail(jwtToken);
        return userEmail;
    }



}
