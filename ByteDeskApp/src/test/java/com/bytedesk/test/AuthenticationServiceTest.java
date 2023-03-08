package com.bytedesk.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import com.assessment.bytedeskapp.auth.AuthenticationRequest;
import com.assessment.bytedeskapp.auth.AuthenticationResponse;
import com.assessment.bytedeskapp.auth.AuthenticationService;
import com.assessment.bytedeskapp.model.User;
import com.assessment.bytedeskapp.repository.UserRepository;
import com.assessment.bytedeskapp.securityconfig.JwtService;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = AuthenticationServiceTest.class)
public class AuthenticationServiceTest {
	
	@Mock
	private JwtService jwtService;
	
    @Mock
	private UserRepository userRepo;

    @InjectMocks
    private AuthenticationService authenticationService;
    
@Test
@DisplayName("Test for authentication success")
 void authenticate() {
	String username = "testuser";
    String password = "testpassword";
    String firstName = "Test";
    String lastName = "User";
    String token = "testtoken";

    AuthenticationRequest request = new AuthenticationRequest();
    request.setUserName(username);
    request.setPassword(password);

    User user = new User();
    user.setUserName(username);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);

    when(userRepo.findByUserName(username)).thenReturn(Optional.of(user));
    when(jwtService.generateToken(user)).thenReturn(token);

    AuthenticationResponse response = authenticationService.authenticate(request);

    assertEquals(firstName, response.getFirstName());
    assertEquals(lastName, response.getLastName());
    assertEquals(username, response.getUserName());
    assertEquals(password, response.getPassword());
    assertEquals(token, response.getToken());

    verify(userRepo,times(1)).findByUserName(username);
   verify(jwtService,times(1)).generateToken(user);
}
@Test
@DisplayName("Test for authentication Failure")
 void authenticateFailure() {
	String username = "testuser";
    String password = "testpassword";
    String firstName = "Test";
    String lastName = "User";
    String token = "testtoken";

    AuthenticationRequest request = new AuthenticationRequest();
    request.setUserName(username);
    request.setPassword(password);

    User user = new User();
    user.setUserName(username);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);

    when(userRepo.findByUserName(username)).thenReturn(Optional.of(user));
    when(jwtService.generateToken(null)).thenReturn(token);


    verify(userRepo,times(0)).findByUserName(username);
    verify(jwtService,times(0)).generateToken(null);
}


 }

