package com.assessment.bytedeskapp.auth;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assessment.bytedeskapp.model.User;
import com.assessment.bytedeskapp.repository.UserRepository;
import com.assessment.bytedeskapp.securityconfig.JwtService;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepo;
	

	@Autowired
	private JwtService jwtService;

	public AuthenticationResponse authenticate(AuthenticationRequest request){
	
		User user=userRepo.findByUserName(request.getUserName())
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		if(user.getPassword().equals(request.getPassword())) {
			var jwtToken=jwtService.generateToken(user);
		    var userName=user.getUserName();
		    var password=user.getPassword();
		    var firstName=user.getFirstName();
		    var lastName=user.getLastName();
			AuthenticationResponse authResponse = new AuthenticationResponse();
			authResponse.setFirstName(firstName);
			authResponse.setLastName(lastName);
			authResponse.setUserName(userName);
			authResponse.setPassword(password);
			authResponse.setToken(jwtToken);
			return authResponse;
		}
	return null;
		
	}

}
