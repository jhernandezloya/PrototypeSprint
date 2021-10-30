package cl.prototype.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.prototype.entities.Application;
import cl.prototype.repository.ApplicationRepository;

@Service
public class ApplicationService {

	
	private ApplicationRepository repository;
	
	
	public ApplicationService(@Qualifier("appRepository2") ApplicationRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Optional<Application> findApplication(Integer id) {
		return repository.findById(id);
	}
	
	public Application saveApplication(Application app) {
		return repository.save(app);
	}
	
	public void deleteApplication(Application app) {
		repository.delete(app);
		return;
	}
	
	public List<Application> getListApplication(String name) {
		return repository.getListApplication(name);
	}

}
