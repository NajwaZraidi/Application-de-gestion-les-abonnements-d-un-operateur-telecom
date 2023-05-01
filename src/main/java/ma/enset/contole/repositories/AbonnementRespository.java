package ma.enset.contole.repositories;

import ma.enset.contole.entities.Abonnnement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AbonnementRespository extends JpaRepository<Abonnnement,Long> {
 Page<Abonnnement> findByTypeAbonnement(String Recherche, Pageable pageable);
 Page<Abonnnement> findByClient_Id(Long Id,String Recherche, Pageable pageable);


}
