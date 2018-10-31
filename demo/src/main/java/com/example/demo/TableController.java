package com.example.demo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);	

	private UsersService usersService;

	@Autowired
	private KlantRepository klantRepo;
	
	@Autowired
	private CalendarRepository calendarRepository;
	
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
	            
			String output ="";
			try {

				URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code:" + conn.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					System.out.println(output);	
			
					result.setMsg(output);
					
					//model.addAttribute("data", output);
					logger.info(output);
				}
				
				conn.disconnect();
			  } catch (MalformedURLException e) {
				e.printStackTrace();
			  } catch (IOException e) {
				e.printStackTrace();
			  }

	        //List<Klant>klantzoeken = usersService.findByNameOrBedrijf(search.getName(), search.getBedrijf());
	        if (klant1.isEmpty()) {
	            result.setMsg("no user found!");
	        } else {
	            result.setMsg("success");	           
	        }
	        
//	        result.setResult(users);
	        result.setKlant(klant1);
	      //  result.setKlant();
	        	
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
	 
	 @GetMapping("/api/calendar")
	 public List<Calendar>getAllEvent(Model model) {
	 List<Calendar>calendarLijst= calendarRepository.findAll();
	 return calendarLijst;
	 }
	
}
