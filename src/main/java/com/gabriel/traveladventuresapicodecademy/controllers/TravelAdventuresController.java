package com.gabriel.traveladventuresapicodecademy.controllers;

import com.gabriel.traveladventuresapicodecademy.entities.Adventure;
import com.gabriel.traveladventuresapicodecademy.repositories.AdventureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        this.adventureRepository = adventureRepo;
    }

    @GetMapping()
    public Iterable<Adventure> getAdventures() {
        return this.adventureRepository.findAll();
    }

    @GetMapping("/bycountry/{country}")
    public List<Adventure> getAdventuresByCountry(@PathVariable String country) {
        return this.adventureRepository.findByCountry(country);
    }

    @GetMapping("/bystate")
    public List<Adventure> getAdventuresByState(@RequestParam String state) {
        return this.adventureRepository.findByState(state);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Adventure addAdventure(@RequestBody Adventure adventure) {
        return this.adventureRepository.save(adventure);
    }

    @PutMapping("/{id}")
    public Adventure updateAdventure(@RequestBody Adventure adventure,
                                @PathVariable int id) {
        Optional<Adventure> adventureToUpdate = adventureRepository.findById(id);
        if (!adventureToUpdate.isPresent()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID not found."
        );

        adventureToUpdate.get().setBlogCompleted(adventure.getBlogCompleted());
        return adventureRepository.save(adventureToUpdate.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdventure(@PathVariable int id) {
        adventureRepository.deleteById(id);
    }
}
