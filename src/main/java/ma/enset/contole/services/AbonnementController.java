package ma.enset.contole.services;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.contole.entities.Abonnnement;
import ma.enset.contole.entities.Client;
import ma.enset.contole.repositories.AbonnementRespository;
import ma.enset.contole.repositories.ClientRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.lang.Long;
//Pour une application nous avons besoin d'un Controller
@Controller
//Injection des dependances via Constructor
//@AllArgsConstructor
public class AbonnementController {
    @Autowired
    private ClientRespository clientRespository;
    @Autowired
    private AbonnementRespository abonnementRespository;
    private Long id_C;
    //acces à la methode patient
    @GetMapping(path = "/abonnements")
    // la methode patient return un vue
    //Module de spring MVC
    public String abonnements(Model model, Long id, String Recherche, int page) {
        Client client = clientRespository.findById(id).orElse(null);
        if (client == null) throw new RuntimeException("client introuvable");
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("Recherche", Recherche);
        id_C=id;
        return "abonnements";
    }

@GetMapping("/abonnement/delete")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public  String delete(long id,long id_client,String Recherche,int page ){
        abonnementRespository.deleteById(id);
        return "redirect:/abonnements?id="+id_client+"&page="+page+"&Recherche="+Recherche;

}
@GetMapping("/formAbonnements")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public String formAbonnement(Model model){
    model.addAttribute("abonnnement",new Abonnnement());
    Client client = clientRespository.findById(id_C).orElse(null);
    model.addAttribute("client", client);
    return "formAbonnements";
}
@PostMapping(path="/Abonnement/save")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public  String save(Model model, @Valid Abonnnement abonnnement, BindingResult bindingResult,
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "")String Recherche){
    Client client = clientRespository.findById(id_C).orElse(null);
    abonnnement.setClient(client);
    if(bindingResult.hasErrors()) return "formAbonnements";
    System.out.println("id"+abonnnement.getId());
    abonnementRespository.save(abonnnement);
    return "redirect:/abonnements?id="+id_C+"&page="+page+"&Recherche="+Recherche;
}
    @GetMapping("/Abonnement/editAbonnements")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editAbonnements(Model model,Long id,String Recherche,int page){
        Client client = clientRespository.findById(id_C).orElse(null);
        if (client == null) throw new RuntimeException("client introuvable");
        model.addAttribute("client", client);
        Abonnnement abonnnement=abonnementRespository.findById(id).orElse(null);
        abonnnement.setClient(client);
        if(abonnnement==null) throw new RuntimeException("Abonnement introuvable");
        model.addAttribute("abonnnement",abonnnement);
        model.addAttribute("page",page);
        model.addAttribute("Recherche",Recherche);
        return "editAbonnements";
    }
}
