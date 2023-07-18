package org.sumit.boot.rest.bootresthiber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.sumit.boot.rest.bootresthiber.entity.Contacts;

public interface ContactsRepository extends CrudRepository<Contacts, Integer> {

	Contacts findByFirstName(String firstName);

	@Query("select objContact from Contacts objContact where objContact.firstName LIKE :fName")
	List<Contacts> getContactsLikeName(@Param("fName") String fName);

	@Query(value = "select * from contacts where first_name='Geraldine'", nativeQuery = true)
	public List<Contacts> customQuery();
}