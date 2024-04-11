package de.dhbw.softwareengineering.anbauplaner.plugins.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.anbauplaner.domain.example.EntityExample;
@Repository
public interface springDataForBridge extends JpaRepository<EntityExample, String> {

}
