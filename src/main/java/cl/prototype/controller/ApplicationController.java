package cl.prototype.controller;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import cl.prototype.async.CallablePassCoopeuch;
import cl.prototype.entities.Application;
import cl.prototype.rs.client.async.AsyncClientRS;
import cl.prototype.service.ApplicationService;

@RestController
@RequestMapping("/application")
public class ApplicationController {
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    
	private final ApplicationService serviceApplication;
	private final AsyncClientRS asyncService;
	private final CallablePassCoopeuch callablePassCoopeuch;
	
	

	public ApplicationController(ApplicationService serviceApplication, AsyncClientRS asyncService,
			CallablePassCoopeuch callablePassCoopeuch) {
		super();
		this.serviceApplication = serviceApplication;
		this.asyncService = asyncService;
		this.callablePassCoopeuch = callablePassCoopeuch;
	}

    
    @GetMapping("/read/{applicationId}")
    public ResponseEntity readReadings(@PathVariable String applicationId) {
    	try	{
    	log.info("Ingreso a la busqueda");
    	Optional<Application> readings = serviceApplication.findApplication(Integer.parseInt(applicationId));
        return readings.isPresent()
                ? ResponseEntity.ok(readings.get())
                : ResponseEntity.notFound().build();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    
    @GetMapping("/hello-async")
    public WebAsyncTask<ResponseEntity<Application>> sayHello(@RequestParam String applicationId){
        System.out.println("service start...");
        System.out.println("Hilo: "+Thread.currentThread().getName());
        asyncService.setId(Integer.parseInt(applicationId));
        callablePassCoopeuch.setExecutorService();
		ExecutorService executorService = callablePassCoopeuch.getExecutorService();
    	WebAsyncTask<ResponseEntity<Application>> task = new WebAsyncTask<ResponseEntity<Application>>(15000, callablePassCoopeuch);
        task.onTimeout(()->{
            System.out.println("onTimeout...");
            executorService.shutdown();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        });
        task.onError(()->{
            System.out.println("onError...");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        });
        task.onCompletion(()->{
        	executorService.shutdownNow();
            System.out.println("onCompletion...");
        });
        System.out.println("service end...");
        return task;
   }
}
