package cl.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.prototype.entities.Application;
import cl.prototype.repository.ApplicationRepository;
import cl.prototype.repository.ReleaseRepository;
import cl.prototype.repository.custom.ApplicationRepositoryCustom;
import cl.prototype.repository.custom.level1.ApplicationRepositoryLevel;
import cl.prototype.repository.dummy.ApplicationRepositoryDummy;

@Configuration
public class ApplicationDataConfiguration {
	private static final Logger log = LoggerFactory.getLogger(ApplicationDataConfiguration.class);

	/* En esta parte se crea el bean y con la etiqueta @Primary le indicas */
	/*
	 * @Bean(name="appRepository10") public ApplicationRepository
	 * getBeanApplication3( ApplicationRepositoryDummy repository) { return
	 * repository; }
	 */
	
	/* No se pueden hacer Bean de Clases Abstractas , ni Interfaces*/
	
	/* Interfaces se pueden instanciar solamente una vez con un metodo set*/

	@Bean(name = { "appRepository2", "appRepository1" })
	public ApplicationRepository getBeanApplication(ApplicationRepository repository) {
		return repository;
	}



	/*
	 * @Bean(name="appRepository1") public ApplicationRepository
	 * getBeanApplication1( ApplicationRepository repository1) { return repository1;
	 * }
	 */
	//

	@Bean
	public CommandLineRunner demo(@Qualifier("appRepository1") ApplicationRepository repository) {

		log.info("Ingreso a este metodo");
		log.error("Error a este metodo");
		log.debug("Debug a este metodo");
		return (args) -> {
			repository.save(new Application("Trackzilla", "kesha.williams", "Application for tracking bugs."));
			repository.save(new Application("Expenses", "mary.jones", "Application to track expense reports."));
			repository.save(new Application("Notifications", "karen.kane",
					"Application to send alerts and notifications to users."));

			for (Application application : repository.findAll()) {
				log.info("The application is: " + application.toString());
			}
		};
	};

	@Bean

	public ReleaseRepository getReleaseRepository(ReleaseRepository repository) {
		return repository;
	}

}
