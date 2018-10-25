package com.example.demo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {

	private UsersService usersService;

	@Autowired
	private KlantRepository klantRepo;
	
	 @Autowired
	 public void setUsersService(UsersService usersService) {
		 this.usersService = usersService;
	 }
	
	 @PostMapping("/api/search")
	 public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {
	        AjaxResponseBody result = new AjaxResponseBody();
	        if (errors.hasErrors()) {
	            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
	            return ResponseEntity.badRequest().body(result);
	        }
	       // List<Users> users =  usersService.findByUserNameOrEmail(search.getName());
	       // List<Klant> klant = klantRepo.findAll();       
	        List<Klant> klant1 = usersService.findByName(search.getName());
	        
	        //List<Klant>klantzoeken = usersService.findByNameOrBedrijf(search.getName(), search.getBedrijf());
	        
	        if (klant1.isEmpty()) {
	            result.setMsg("no user found!");
	        } else {
	            result.setMsg("success");
	        }
//	        result.setResult(users);
	        result.setKlant(klant1);
//	        result.setKlant(klantzoeken);
//	        result.setKlant(klant);
	        return ResponseEntity.ok(result);
	    }
	 
	 @GetMapping("/api/all")
	 public ResponseEntity<?> getAll(Model model) {
	        KlantAjaxBody result = new KlantAjaxBody();
	        List<Klant> klant = klantRepo.findAll();       
	        model.addAttribute("klant", klant);
	        result.setKlant(klant);
	        return ResponseEntity.ok(result);
	    }
	
}
