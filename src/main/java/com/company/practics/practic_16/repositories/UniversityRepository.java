package com.company.practics.practic_16.repositories;

import com.company.practics.practic_16.models.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {
}
