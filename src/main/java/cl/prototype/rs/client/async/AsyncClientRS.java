package cl.prototype.rs.client.async;

import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cl.prototype.entities.Application;
import cl.prototype.repository.ApplicationRepository;

@Component
public class AsyncClientRS implements Callable<Optional<Application>> {
 
	private ApplicationRepository repository;
	private Integer id;
	
	public AsyncClientRS(@Qualifier("appRepository2") ApplicationRepository repository) {
		super();
		this.repository = repository;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Optional<Application> call() throws Exception {
		Optional<Application> entidad =  Optional.empty();
		System.out.println("Hilo 2:"+Thread.currentThread().getName());
		System.out.println("Inicio Hilo 2:"+ System.currentTimeMillis());
        Boolean band = true;
        while(band){
        	entidad = repository.findById(id);
        	if(entidad.isPresent()){
        		if(entidad.get().getName().equals("BitBucket")) {
        			System.out.println("Salir del bucle");
        			band = false;
        			//TODO para probar una excepcion forzada 
        			//throw new InterruptedException("Interrupcion Forzada por el Padre");
        			
        		}
        	}
        	Thread.sleep(1000);
        	
        }
        System.out.println("Fin Hilo 2:"+ System.currentTimeMillis());
		return entidad;
	}
         
     
}
