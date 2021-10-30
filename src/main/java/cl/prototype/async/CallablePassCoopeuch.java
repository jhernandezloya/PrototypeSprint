package cl.prototype.async;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cl.prototype.entities.Application;
import cl.prototype.rs.client.async.AsyncClientRS;

@Component
public class CallablePassCoopeuch implements Callable<ResponseEntity<Application>> {

	private ExecutorService executorService;
	private final AsyncClientRS asyncService;
	
	public CallablePassCoopeuch( AsyncClientRS asyncService) {
		super();
		this.asyncService = asyncService;
	}


	@Override
	public ResponseEntity<Application> call() throws Exception {
		try{
        	Future<Optional<Application>> future = executorService.submit(asyncService);

        	Optional<Application> application = future.get();
        	
        	return application.isPresent() ? ResponseEntity.ok(application.get()) :
       			  ResponseEntity.notFound().build();
			}catch(Exception e) {
				System.out.println("Exception:"+e.getCause().getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	
	public ExecutorService getExecutorService() {
		return executorService;
	}
	
	public void setExecutorService(){
		executorService = Executors.newSingleThreadExecutor();
	}

}
