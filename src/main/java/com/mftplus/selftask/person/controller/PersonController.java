package com.mftplus.selftask.person.controller;

import com.mftplus.selftask.person.model.entity.Person;
import com.mftplus.selftask.person.model.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persons")
@Slf4j
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        personService.save(person);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        personService.update(person);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @DeleteMapping("/{id}")
    public void deleteId(@PathVariable Long id) {
        personService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<Person>> getPersonByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(personService.findByLastName(lastName));
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<Person>> getPersonByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(personService.findByFirstName(firstName));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Person> getPersonByEmail(@PathVariable String email) {
        return ResponseEntity.ok(personService.findByEmail(email));
    }

    @GetMapping("/password/{password}")
    public ResponseEntity<List<Person>> getPersonByPassword(@PathVariable String password) {
        List<Person> personPass = personService.findByUserPassword(password);
        return ResponseEntity.ok(Collections.singletonList(personPass.get(0)));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<Person>> getPersonByUsername(@PathVariable String username) {
        List<Person> personUser = personService.findByUser(username);
        return ResponseEntity.ok(Collections.singletonList(personUser.get(0)));
    }

//    @Autowired
//    private PersonService personService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public String listPersons(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "firstName") String sortBy,
//            @RequestParam(required = false) Boolean fragment,
//            Model model) {
//        if (size <= 0) size = 10;
//        if (!Arrays.asList("firstName", "lastName").contains(sortBy)) {
//            sortBy = "firstName";
//        }
//        // ایجاد صفحه‌بندی با مرتب‌سازی
//        Sort sort = Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        //todo : do it for pageable!!!
////        Page<Person> persons = personService.getAllPerson(pageable);
//        List<Person> persons = personService.findAll();
//
//
//        model.addAttribute("persons", persons);
//        model.addAttribute("currentPage", page);
//        //todo : do it for pageable!!!!
////        model.addAttribute("totalPage", persons.getTotalPages());
//
//        return fragment != null && fragment ?
//                "person/fragments/persons-table :: persons-table" : "person/persons";
//    }
//
////    @GetMapping("/{id}")
////    public String getPersonById(Model model, @PathVariable Long id) {
////        model.addAttribute("person", personService.findById(id));
////        return "persons";        //todo-> return "editPerson"
////
////    }
//
////    @GetMapping("/firstName/{firstName}")
////    public String getPersonByFirstName(Model model, @PathVariable String firstName) {
////        if (personService.findByFirstName(firstName) != null) {
////            log.info("person found by firstName {}", personService.findByFirstName(firstName).get(0));
////            model.addAttribute("person", personService.findByFirstName(firstName).get(0));
////            return "redirect:/persons";
////        } else {
////            log.error("person not found by firstName");
////            return null;
////        }
////
////    }
//
////    @GetMapping("/lastName/{lastName}")
////    public String getPersonByLastName(Model model, @PathVariable String lastName) {
////        if (personService.findByLastName(lastName) != null) {
////            log.info("person found by lastName {}", personService.findByLastName(lastName).get(0));
////            model.addAttribute("person", personService.findByLastName(lastName).get(0));
////            return "redirect:/persons";
////        } else {
////            log.error("person not found by lastName");
////            return null;
////        }
////    }
//
////    @GetMapping("/email/{email}")
////    public String getPersonByEmail(Model model, @PathVariable String email) {
////        if (personService.findByEmail(email) != null) {
////            log.info("person found by email {}", personService.findByEmail(email));
////            model.addAttribute("person", personService.findByEmail(email));
////            return "redirect:/persons";
////        } else {
////            log.error("person not found by email");
////            return null;
////        }
////    }
//
//    @GetMapping("/{username}")
//    public String getPersonByUsername(Model model, @PathVariable String username) {
//        User user = userService.findById(username);
//        Person person = new Person();
//        person.setUser(user);
//        if (personService.findByUser(username) != null) {
//            log.info("person found by username {}", personService.findByUser(username));
//            model.addAttribute("person", personService.findByUser(username));
//            return "redirect:/persons";
//        } else {
//            log.error("person not found by username");
//            return null;
//        }
//    }
//
//
//    @PostMapping
//    @ResponseBody
//    public ResponseEntity<?> savePerson(
//            @Valid @RequestBody Person person,
//            BindingResult bindingResult
//    ) {
//        log.info("person saved {}", person);
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(errors);
//        }
//        try {
//            Person savedPerson = personService.save(person);
//            return ResponseEntity.ok(savedPerson);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("messages", e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
//        try {
//            log.info("person deleted {}", id);
//            personService.delete(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//    }
}
