package org.sumit.spring.boot.competeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sumit.spring.boot.competeapp.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	@Query("update User oUser set oUser.password=:pwd where oUser.userName=:uname")
	boolean updatePassword(@Param("uname")String userName, @Param("pwd")String password);
}
