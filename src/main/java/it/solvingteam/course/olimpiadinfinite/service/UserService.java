package it.solvingteam.course.olimpiadinfinite.service;


import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.solvingteam.course.olimpiadinfinite.dto.messages.InsertNationRepresentativeMessageDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.UserSignupMessageDto;
import it.solvingteam.course.olimpiadinfinite.model.Role;
import it.solvingteam.course.olimpiadinfinite.model.UserPrincipal;
import it.solvingteam.course.olimpiadinfinite.model.user.User;
import it.solvingteam.course.olimpiadinfinite.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }
  
    public Optional<User> findUserByUSername(String username) {
        return userRepository.findByUsername(username);
    }
    
	public Optional<User> findById(Long id) {
		return this.userRepository.findById(id);
	}


    public void signup(InsertNationRepresentativeMessageDto insertNationRepresentativeMessageDto) {
        String username = insertNationRepresentativeMessageDto.getUserSignupMessageDto().getUsername();
        String passwordEncoded = passwordEncoder.encode(insertNationRepresentativeMessageDto.getUserSignupMessageDto().getPassword());
    
        User user = new User();
        user.setName(insertNationRepresentativeMessageDto.getName());
        user.setSurname(insertNationRepresentativeMessageDto.getSurname());
        user.setFiscalCode(insertNationRepresentativeMessageDto.getFiscalCode());
        user.setRole(Role.RAPPRESENTANTE_NAZIONE);
        user.setUsername(username);
        user.setPassword(passwordEncoded);
       
        this.userRepository.save(user);
    }
//	
	//per organizzatore
//	 public void signup(UserSignupMessageDto userSignupMessageDto)  {
//	        String username = userSignupMessageDto.getUsername();
//	        String passwordEncoded = passwordEncoder.encode(userSignupMessageDto.getPassword());
//	        
//
//	        User user = new User();
//	        user.setUsername(username);
//	        user.setPassword(passwordEncoded);
//          user.setRole(Role.ORGANIZZATORE);
//	       
//
//	        this.userRepository.save(user);
//	    }



}
