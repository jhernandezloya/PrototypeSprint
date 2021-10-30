package cl.prototype.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cl.prototype.entities.Application;
import cl.prototype.repository.custom.level1.ApplicationRepositoryLevel;


@Transactional(readOnly = true)
public class ApplicationRepositoryImpl implements ApplicationRepositoryLevel {
	
	@PersistenceContext
	EntityManager entityManager;
	 
	@Override
	public List<Application> getListApplication(String name) {
		Query query = entityManager.createNativeQuery("select e.* from Application \n" +
                "where name = ? " , Application.class);
        query.setParameter(1, name );
        return query.getResultList();
	}



}
