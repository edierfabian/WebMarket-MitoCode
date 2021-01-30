package com.marketmito.apporder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marketmito.apporder.entity.Customer;
import com.marketmito.apporder.entity.Invoice;
import com.marketmito.apporder.entity.Product;
import com.marketmito.apporder.entity.Users;




@Repository
public interface UserRepo extends JpaRepository<Users, Long>{
	//@Query("SELECT i FROM Invoice i JOIN FETCH i.customer c JOIN FETCH i.items l JOIN FETCH l.product WHERE i.id=?1")
	//@Query("SELECT u FROM Users u,Role r WHERE u.id=r")
	@Query(value="SELECT * FROM role r WHERE r.id=?1",nativeQuery = true)
	List<Users> findUsersByRole(Long id);
	
	
	Users findByUserName(String userName); 
	

	
}
