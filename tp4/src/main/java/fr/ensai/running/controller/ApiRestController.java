package fr.ensai.running.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.service.AthleteService;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Get all athletes
     */
    @GetMapping("/athlete")
    public List<Athlete> allAthletes() {

        return athleteService.findAll();
    }

    @GetMapping("/athlete/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable long id) {
        Athlete athlete = athleteService.findById(id);
        if (athlete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(athlete);
    }

    @DeleteMapping("/athlete/{id}")
    public ResponseEntity<String> deleteAthleteById(@PathVariable long id) {
        Athlete athlete = athleteService.findById(id);
        if (athlete == null) {
            return ResponseEntity.status(404).body("Athlete not found");
        }
        athleteService.deleteById(id);
        return ResponseEntity.ok("Athlete deleted sucessfully");
    }

    @PostMapping("/athlete")
    public ResponseEntity<Athlete> addAthlete(@RequestBody Athlete athlete) {
        Athlete savedAthlete = athleteService.save(athlete);
        return ResponseEntity.ok(savedAthlete);
    }

}
