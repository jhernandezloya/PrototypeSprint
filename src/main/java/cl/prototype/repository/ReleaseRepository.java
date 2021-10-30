package cl.prototype.repository;

import org.springframework.data.repository.CrudRepository;

import cl.prototype.entities.Release;

public interface ReleaseRepository extends CrudRepository<Release, Integer> {

}
