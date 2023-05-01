package ma.enset.contole.repositories;

import ma.enset.contole.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClientRespository extends JpaRepository<Client,Long> {
 Page<Client> findByNomContains(String Recherche, Pageable pageable);


}
