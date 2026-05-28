package com.gabriel.traveladventuresapicodecademy.controllers;

import com.gabriel.traveladventuresapicodecademy.entities.Adventure;
import com.gabriel.traveladventuresapicodecademy.repositories.AdventureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        adventureRepository = adventureRepo;
    }

    @GetMapping()
    public Iterable<Adventure> getAdventures() {
        return adventureRepository.findAll();
    }

    @GetMapping("/bycountry/{country}")
    public List<Adventure> getAdventuresByCountry(@PathVariable String country) {
        return adventureRepository.findByCountry(country);
    }

    @GetMapping("/bystate")
    public List<Adventure> getAdventuresByState(@RequestParam String state) {
        return adventureRepository.findByState(state);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Adventure addAdventure(@RequestBody Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    @PutMapping("/{id}")
    public Adventure updateAdventure(@RequestBody Adventure adventure,
                                     @PathVariable int id) {
        Optional<Adventure> adventureToUpdate = adventureRepository.findById(id);
        if (!adventureToUpdate.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found.");
        }

        Adventure currentAdventure = adventureToUpdate.get();
        currentAdventure.setBlogCompleted(adventure.getBlogCompleted());
        return adventureRepository.save(currentAdventure);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdventure(@PathVariable int id) {
        adventureRepository.deleteById(id);
    }
}
