package fr.ensai.running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.service.AthleteService;

@Controller //pour transformer une requête html en java
@RequestMapping("/athlete") //pour dire que toutes les commandes commençant par /athlete viennent taper dans ce controller là
public class AthleteController {

    @Autowired
    private AthleteService athleteService; //fait appel à ce service là

    /**
     * Display all Athletes
     */
    @GetMapping()
    public String findAllAthletes(Model model) {
        model.addAttribute("athletes", athleteService.findAll());
        return "allAthletes"; // allAthlete.html
    }

    /**
     * Redirection to the add form
     */
    @GetMapping("/addNew") //quand il y a /athlete/addNew, c'est cette fonction qui est appliquée
    public String addNewAthlete(Model model) {
        Athlete athlete = new Athlete();
        model.addAttribute("athlete", athlete);
        return "createAthlete"; // createAthlete.html
    }

    /**
     * Save the created Athlete
     */
    @PostMapping("/save")
    public String saveAthlete(@ModelAttribute("athlete") Athlete athlete) {
        athleteService.save(athlete);
        return "redirect:/athlete"; // redirection to endpoint /athlete
    }

    /**
     * Delete an Athlete by id
     */
    @GetMapping("/delete/{id}")
    public String deleteAthleteById(@PathVariable(value = "id") long id) {
        Athlete athlete = athleteService.findById(id);
        if (athlete != null) {
            athleteService.deleteById(id);
        }
        return "redirect:/athlete"; // redirection to endpoint /athlete

    }
}