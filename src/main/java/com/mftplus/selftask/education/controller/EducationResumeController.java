package com.mftplus.selftask.education.controller;

import com.mftplus.selftask.education.model.entity.EducationResume;
import com.mftplus.selftask.education.model.service.EducationResumeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/educates")
@Slf4j
public class EducationResumeController {

    private final EducationResumeService educationResumeService;

    public EducationResumeController(EducationResumeService educationResumeService) {
        this.educationResumeService = educationResumeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EducationResume> createEducationResume(@RequestBody EducationResume educationResume) {
        educationResumeService.save(educationResume);
        return ResponseEntity.status(HttpStatus.OK).body(educationResume);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EducationResume> updateEducationResume(@RequestBody EducationResume educationResume) {
        educationResumeService.update(educationResume);
        return ResponseEntity.status(HttpStatus.OK).body(educationResume);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationResumeService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<EducationResume>> getAllEducations() {
        return ResponseEntity.ok(educationResumeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResume> getEducationById(@PathVariable Long id) {
        EducationResume educationResume = educationResumeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(educationResume);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<EducationResume>> getEducationByPersonId(@PathVariable Long id) {
       List<EducationResume> educationResume = educationResumeService.findByPerson(id);
        return ResponseEntity.ok(educationResume);
    }


//    @GetMapping
//    public String listEducations(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "firstName") String sortBy,
//            @RequestParam(required = false) Boolean fragment,
//            Model model) {
//        if (size <= 0) size = 10;
//        if (!Arrays.asList("firstName").contains(sortBy)) {
//            sortBy = "firstName";
//        }
//        Sort sort = Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<EducationResume> educationResumes = educationResumeService.getAllEducationResume(pageable);
//        model.addAttribute("educationResumes", educationResumes);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", educationResumes.getTotalPages());
//
//        return fragment != null && fragment ?
//                "education/fragments/educates-table :: educates-table" : "education/educates";
//    }
//
//    //    @GetMapping("/{id}")
////    public String getEducationResumeById(Model model, @PathVariable Long id) {
////        model.addAttribute("educationResume", educationResumeService.findById(id));
////        return "educates";   //todo->return editEducates
////    }
////
////    @GetMapping("/firstName/{firstName}")
////    public String getEducationResumeByFirstName(Model model, @PathVariable String firstName) {
////        if (educationResumeService.findByFirstName(firstName) != null) {
////            log.info("education resume found by firstName {}", educationResumeService.findByFirstName(firstName).get(0));
////            model.addAttribute("educationResume", educationResumeService.findByFirstName(firstName));
////            return "redirect:/educates";
////        } else {
////            log.error("Education resume by firstName not found");
////            return null;
////        }
////    }
////
////    @GetMapping("/lastName/{lastName}")
////    public String getEducationResumeByLastName(Model model, @PathVariable String lastName) {
////        if (educationResumeService.findByLastName(lastName) != null) {
////            log.info("education resume found by lastName {}", educationResumeService.findByLastName(lastName).get(0));
////            model.addAttribute("educationResume", educationResumeService.findByLastName(lastName));
////            return "redirect:/educates";
////        } else {
////            log.error("Education resume by lastName not found");
////            return null;
////        }
////    }
////
////    @GetMapping
////    public String listProducts(
////            @RequestParam(defaultValue = "0") int page,
////            @RequestParam(defaultValue = "10") int size,
////            @RequestParam(defaultValue = "firstName") String sortBy,
////            @RequestParam(required = false) Boolean fragment,
////            Model model) {
////        if (size <= 0) size = 10;
////        if (!Arrays.asList("firstName", "lastName").contains(sortBy)) {
////            sortBy = "firstName";
////        }
////        Sort sort = Sort.by(sortBy).ascending();
////        Pageable pageable = PageRequest.of(page, size, sort);
////        Page<EducationResume> educationResumes = educationResumeService.getAllEducationResume(pageable);
////        model.addAttribute("educationResumes", educationResumes);
////        model.addAttribute("currentPage", page);
////        model.addAttribute("totalPage", educationResumes.getTotalPages());
////
////        return fragment != null && fragment ?
////                "educationResume/fragments/educationResumes-table :: educationResumes-table" : "educationResumes";
////    }
////
//    @PostMapping
//    @ResponseBody
//    public ResponseEntity<?> saveEducate(
//            @Valid @RequestBody EducationResume educationResume, BindingResult bindingResult) {
//        log.info("saved educationResume {}",educationResumeService.save(educationResume));
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(errors);
//        }
//        try {
//            EducationResume savedEducation = educationResumeService.save(educationResume);
//            return ResponseEntity.ok(savedEducation);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//
//    }
//    @PutMapping
//    public String updateEducationResume(EducationResume educationResume) {
//        educationResumeService.update(educationResume);
//        log.info("Updated EducationResume {}", educationResume);
//        return "redirect:/educates";
//
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity<?> deleteEducation(@PathVariable Long id) {
//        try {
//            System.out.println("delete education resume");
//            educationResumeService.delete(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//    }
}
