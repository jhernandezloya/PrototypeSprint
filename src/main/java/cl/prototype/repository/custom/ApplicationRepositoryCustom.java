package cl.prototype.repository.custom;

import java.util.List;

import cl.prototype.entities.Application;

public interface ApplicationRepositoryCustom {
	List<Application> getListApplication(String name);
}
