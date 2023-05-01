package ma.enset.contole.services;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.contole.entities.Client;
import ma.enset.contole.repositories.ClientRespository;
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

//Pour une application nous avons besoin d'un Controller
@Controller
//Injection des dependances via Constructor
@AllArgsConstructor
public class ClientController {
    private ClientRespository clientRespository;

//acces à la methode patient
    @GetMapping(path="/index")
    // la methode patient return un vue
    //Module de spring MVC
    public String client(Model model,
                          //pagination
                           @RequestParam(name="page",defaultValue = "0") int page,
                          //size de page
                          @RequestParam(name="size",defaultValue = "5") int size,
                          @RequestParam(name="Recherche",defaultValue = "") String Recherche

    ){
        Page<Client> pageClient=clientRespository.findByNomContains(Recherche,PageRequest.of(page, size));
        // stocker dans le modul
        model.addAttribute("listClients",pageClient.getContent());
        model.addAttribute("pages",new int[pageClient.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("Recherche",Recherche);
        return "clients";
   }
@GetMapping("/delete")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public  String delete(long id,String Recherche,int page ){
        clientRespository.deleteById(id);
        return "redirect:/index?page="+page+"&Recherche="+Recherche;
}
@GetMapping("/")
    public  String home(){
        return "redirect:/index";
    }

    @GetMapping("/clients")
    @ResponseBody
    public List<Client> patientList(){
        return  clientRespository.findAll();
    }

    @GetMapping("/formClients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
public String formClient(Model model){
        model.addAttribute("client",new Client());
        return "formClients";
    }
    @PostMapping(path="/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  String save(Model model, @Valid Client client, BindingResult bindingResult,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "")String Recherche){
        if(bindingResult.hasErrors()) return "formClients";
        clientRespository.save(client);
        return "redirect:/index?page="+page+"&Recherche="+Recherche;
    }
    @GetMapping("/editClients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editClients(Model model,Long id,String Recherche,int page){
        Client client=clientRespository.findById(id).orElse(null);
        if(client==null) throw new RuntimeException("client introuvable");
        model.addAttribute("client",client);
        model.addAttribute("page",page);
        model.addAttribute("Recherche",Recherche);

        return "editClients";
    }
}
