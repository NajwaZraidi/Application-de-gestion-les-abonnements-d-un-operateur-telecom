package ma.enset.contole;

import ma.enset.contole.entities.Abonnnement;
import ma.enset.contole.entities.Client;
import ma.enset.contole.entities.TypeAbonnement;
import ma.enset.contole.repositories.AbonnementRespository;
import ma.enset.contole.repositories.ClientRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ControleArchitectureJeeEtMiddlmewareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControleArchitectureJeeEtMiddlmewareApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(ClientRespository clientRespository,AbonnementRespository abonnementRespository){
        //CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository){
        return args -> {
            //Insertion des client
            clientRespository.save(new Client(null,"Boussairi","ikram@gmail.com","ikram1",null));
            clientRespository.save(new Client(null,"Kamis","Kamis@gmail.com","kamis1",null));
            clientRespository.save(new Client(null,"alami","alami@gmail.com","alami23",null));
            clientRespository.save(new Client(null,"ZRAIDI","ZRAIDI@gmail.com","zraidi74",null));
            clientRespository.save(new Client(null,"Islam","Islam@gmail.com","islam74",null));
            clientRespository.save(new Client(null,"ALAM","ALAM@gmail.com","alam2",null));

            abonnementRespository.save(new Abonnnement(null, new Date(),TypeAbonnement.GSM,30100.2, 45522.00,null));
            abonnementRespository.save(new Abonnnement(null, new Date(),TypeAbonnement.INTERNET,50600.1, 74485.1,null));
            abonnementRespository.save(new Abonnnement(null, new Date(),TypeAbonnement.TELEPHONE_FIXE,170600.00,74851.2,null));
            abonnementRespository.save(new Abonnnement(null, new Date(),TypeAbonnement.INTERNET,600.1, 148745.2,null));

            Client client1 = clientRespository.findById(1L).orElse(null);
            Client client2 = clientRespository.findById(2L).orElse(null);

            Abonnnement abonnnement= abonnementRespository.findById(21L).orElse(null);
            abonnnement.setClient(client1);
            abonnementRespository.save(abonnnement);


            Abonnnement abonnement2= abonnementRespository.findById(22L).orElse(null);
            abonnement2.setClient(client2);
            abonnementRespository.save(abonnement2);


        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
