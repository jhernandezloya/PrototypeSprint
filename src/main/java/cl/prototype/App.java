package cl.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import cl.prototype.repository.ApplicationRepository;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(App.class);
	@Autowired
    private ApplicationRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
 
}
