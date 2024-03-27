package one.digitalinnovation.labpadroesprojetospring.model;



import org.springframework.data.repository.CrudRepository;


public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Cliente findByNome(String nome);
}

