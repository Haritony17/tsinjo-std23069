// HelpRepository.java
package com.exam.tsinjo.repository;

import com.exam.tsinjo.model.Help;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpRepository extends JpaRepository<Help, String> {
  List<Help> findAllByOrderByCreationDatetimeDesc();
}
