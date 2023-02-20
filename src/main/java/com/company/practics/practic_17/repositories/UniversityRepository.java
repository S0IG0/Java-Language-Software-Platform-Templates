package com.company.practics.practic_17.repositories;

import com.company.practics.practic_17.models.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {
}
