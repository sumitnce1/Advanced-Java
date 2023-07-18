package org.sumit.spring.boot.competeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sumit.spring.boot.competeapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
