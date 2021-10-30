package cl.prototype.repository;

import org.springframework.data.repository.CrudRepository;

import cl.prototype.entities.Application;
import cl.prototype.repository.custom.level1.ApplicationRepositoryLevel;


public interface ApplicationRepository extends CrudRepository<Application, Integer>,ApplicationRepositoryLevel {
	
	  Iterable<Application> nameAndDescription( String name,
	  String description );
	 

}
