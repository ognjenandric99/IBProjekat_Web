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

public interface AuthRepo extends JpaRepository<Authority,Long>{
	Authority findByName(String name);
}
