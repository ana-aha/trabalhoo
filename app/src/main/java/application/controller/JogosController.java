package application.controller;
 
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
import application.model.Genero;
import application.model.Jogos;
import application.repository.GeneroRepository;
import application.repository.JogosRepository;
 
@Controller
@RequestMapping("/jogos")
public class JogosController {
    @Autowired
    private JogosRepository JogosRepo;
    @Autowired
    private GeneroRepository generoRepo;
 
    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("jogos", JogosRepo.findAll());
        return "/jogos/list";
    }
 
    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("generos", generoRepo.findAll());
        return "/jogos/insert";
    }
 
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
        @RequestParam("titulo") String titulo,
        @RequestParam("genero") long generoId
    ) {
        Optional<Genero> resultGenero = generoRepo.findById(generoId);
        if(resultGenero.isPresent()) {
            Jogos Jogos = new Jogos();
            Jogos.setTitulo(titulo);
            Jogos.setGenero(resultGenero.get());
 
            JogosRepo.save(Jogos);
        }
        return "redirect:/jogos/list";
    }
 
    @RequestMapping("/update")
    public String update(Model ui, @RequestParam("id") long id) {
        Optional<Jogos> resultJogos = JogosRepo.findById(id);
        if(resultJogos.isPresent()) {
            ui.addAttribute("jogos", resultJogos.get());
            ui.addAttribute("generos", generoRepo.findAll());
            return "/jogos/update";
        }
        return "redirect:/jogos/list";
    }
 
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
        @RequestParam("id") long id,
        @RequestParam("titulo") String titulo,
        @RequestParam("genero") long generoId
    ) {
        Optional<Jogos> resultJogos = JogosRepo.findById(id);
        if(resultJogos.isPresent()) {
            Optional<Genero> resultGenero = generoRepo.findById(generoId);
            if(resultGenero.isPresent()) {
                resultJogos.get().setTitulo(titulo);
                resultJogos.get().setGenero(resultGenero.get());
 
                JogosRepo.save(resultJogos.get());
            }
        }
        return "redirect:/jogos/list";
    }
 
    @RequestMapping("/delete")
    public String delete(Model ui, @RequestParam("id") long id) {
        Optional<Jogos> resultJogos = JogosRepo.findById(id);
        if(resultJogos.isPresent()) {
            ui.addAttribute("jogos", resultJogos.get());
            return "/jogos/delete";
        }
        return "redirect:/Jogos/list";
    }
 
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") long id) {
        JogosRepo.deleteById(id);
        return "redirect:/jogos/list";
    }
}