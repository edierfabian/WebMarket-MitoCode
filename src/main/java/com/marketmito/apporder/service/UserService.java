package com.marketmito.apporder.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.marketmito.apporder.UserRegister.UsersRegister;
import com.marketmito.apporder.entity.Users;

public interface UserService extends UserDetailsService{
	
	Users save(UsersRegister usersRegister);
	//List<Users> getUsersByuserName(String term) throws Exception;

	List<Users> getAll() throws Exception;

	Page<Users> getAll(Pageable pageable) throws Exception;

	void delete(Long id);
	
	
}
