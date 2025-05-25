package com.mftplus.selftask.person.controller;

import com.mftplus.selftask.person.model.entity.Person;
import com.mftplus.selftask.person.model.service.PersonService;
import com.mftplus.selftask.user.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/newPersons")
@Slf4j
public class NewPersonController {
    private final PersonService personService;

    public NewPersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public String getPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("persons", personService.findAll());

        return "newPersons";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        log.info("person found by id {}", id);
        return "redirect:/newPersons";
    }

    @GetMapping("/username/{username}")
    public String getPersonByUsername(@PathVariable String username, Model model) {
        model.addAttribute("personUser", personService.findByUser(username));
        log.info("person found by username {}", username);
        return "redirect:/newPersons";
    }
    @GetMapping("/password/{password}")
    public String getPersonByPassword(@PathVariable String password, Model model) {
        model.addAttribute("personUserPass", personService.findByUserPassword(password));
        log.info("person found by password {}", password);
        return "redirect:/newPersons";
    }

    @PostMapping
    public String personAdd(Person person) {
        personService.save(person);
        System.out.println("person Saved"+person);
        return "redirect:/newPersons";

    }
    @DeleteMapping("/{id}")
    public String deletePersonById(@PathVariable Long id) {
        personService.delete(id);
        return "redirect:/newPersons";
    }
}
