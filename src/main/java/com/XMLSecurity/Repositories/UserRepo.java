package com.XMLSecurity.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import com.XMLSecurity.Models.*;

public interface UserRepo extends JpaRepository<User,Long>{

	User findByEmailAndPassword(String email,String password);
	
	User findByActiveSession(String session);
	
	User findByEmail(String email);
	
	List<User> findAllByActive(Boolean active);
}
