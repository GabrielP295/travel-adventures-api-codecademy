package com.gabriel.traveladventuresapicodecademy.controllers;

import com.gabriel.traveladventuresapicodecademy.entities.Adventure;
import com.gabriel.traveladventuresapicodecademy.repositories.AdventureRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
