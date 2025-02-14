package com.studyportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyportal.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long>
{

}
