package com.assessment.bytedeskapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	


@SuppressWarnings("unchecked")
@PostMapping("/login")
public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
	AuthenticationResponse details=authenticationService.authenticate(request);

	if(details!=null) {
		return ResponseEntity.ok(details);
	}
	return (ResponseEntity<AuthenticationResponse>) ResponseEntity.internalServerError();
		
	
}
}
