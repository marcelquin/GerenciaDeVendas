package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CLienteRepository extends JpaRepository<Cliente, Long> {

}
