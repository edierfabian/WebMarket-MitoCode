package com.marketmito.apporder.service.impl;



import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.marketmito.apporder.UserRegister.UsersRegister;

import com.marketmito.apporder.entity.Role;
import com.marketmito.apporder.entity.Users;
import com.marketmito.apporder.repository.UserRepo;
import com.marketmito.apporder.service.UserService;




@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo UserRepo;
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	@Override
	public Users save(UsersRegister usersRegister) {
		
		
		Users user=new Users();
				
		user.setUserName(usersRegister.getUserName());
		user.setPassword(PasswordEncoder.encode(usersRegister.getPassword()));
		user.setEnabled(Boolean.valueOf("true"));
		//(user.setRoles(Arrays.asList(new Role(null, "ROLE_USER")));
		user.setRoles(Arrays.asList(new Role(null, usersRegister.getRoles())));
			
		return UserRepo.save(user);
		
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = UserRepo.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
	}


	@Override
	public List<Users> getAll() throws Exception {
		return UserRepo.findAll();
	}

	@Override
	public Page<Users> getAll(Pageable pageable) throws Exception {
		return UserRepo.findAll(pageable);
	}

	@Override
	public void delete (Long id) {
		UserRepo.deleteById(id); 
		
	}
	
}

	

