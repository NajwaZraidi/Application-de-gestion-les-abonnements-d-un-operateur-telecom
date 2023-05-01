package ma.enset.contole.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abonnnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE) //garder la forme de date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAbonnement;
    @Enumerated(EnumType.STRING)
    private TypeAbonnement typeAbonnement;
    private Double solde;
    private Double montant;
    @ManyToOne
    private Client client;
}
