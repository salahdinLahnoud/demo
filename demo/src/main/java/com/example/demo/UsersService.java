package com.example.demo;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	import javax.annotation.PostConstruct;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.stream.Collectors;

	@Service
	public class UsersService {
	    private List<Users> users;
	    
	    @Autowired
	    private KlantRepository klantRepo; 
	    private List<Klant> klants;

	    public List<Klant> findByName(String name) {
	    	List<Klant>klant = klantRepo.findAll();
	    	List<Klant> result = klant.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	        return result;
	    }
	    
	    public List<Klant>findByNameOrBedrijf(String name, String bedrijf){
	    	List<Klant>klant = klantRepo.findAll();
	    	List<Klant> result = klant.stream().filter(x -> x.getName().equalsIgnoreCase(name) && x.getBedrijf().equalsIgnoreCase(bedrijf)).collect(Collectors.toList());
	    	return result;
	    }
	    
//	    
//	    public List<Users> findByUserNameOrEmail(String username) {
//	        List<Users> result = users.stream()
//	            .filter(x -> x.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());
//	        return result;
//
//	    }

	    // Init some users for testing
//	    @PostConstruct
//	    private void iniDataForTesting() { 	
//	        users = new ArrayList<Users>();
//	        Users user1 = new Users("salahdin", "password111", "salahdin@yahoo.com");
//	        Users user2 = new Users("salim", "password222", "salim@yahoo.com");
//	        Users user3 = new Users("sami", "password333", "sami@yahoo.com");
//	        users.add(user1);
//	        users.add(user2);
//	        users.add(user3);
//	    }
//	    
	    
	    

	}
