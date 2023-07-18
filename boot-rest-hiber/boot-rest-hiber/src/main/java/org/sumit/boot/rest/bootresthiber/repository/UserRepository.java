package org.sumit.boot.rest.bootresthiber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sumit.boot.rest.bootresthiber.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
