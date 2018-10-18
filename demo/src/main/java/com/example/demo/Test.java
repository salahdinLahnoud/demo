package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class Test {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);		
//	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private KlantRepository klantRepository;
	
//	@Autowired
//	private UserService userService;
//	
//	public Test(UserService userService) {
//		this.userService = userService;
//	}
//
//	http://gturnquist-quoters.cfapps.io/api/random"
//	@RequestMapping("/user")
//	public List<User> users(){
//		return userService.list();
//	}
	
//	//http://api.nytimes.com/svc/books/v2/lists/overview.json?published_date=2013-01-01&api-key=76363c9e70bc401bac1e6ad88b13bd1d
//	@RequestMapping("/home")
//	public String welcome(Model model) {
//		List<Product> list = productRepository.findAll();
//		model.addAttribute("list", list);
//		model.addAttribute("message","message : test");
//		return "home";
//	}

	
	/*@GetMapping("/home")
	public String welcome() {
		return "home";
	}*/
	
	@GetMapping("/displayBarGraph")
	public String barGraph(Model model) {
		List<Product> list = productRepository.findAll();	
		Map<String, Double> surveyMap = new LinkedHashMap<>();
		String name = null;
		double price = 0.0;
		
		list.stream().map(p -> new Product(p.getName(), p.getPrice())).collect(Collectors.toList());
		for(Product p : list) {
			name = p.getName();
			price = p.getPrice();
			surveyMap.put(name , price);
		}
		
		model.addAttribute("surveyMap", surveyMap);				
		return "barGraph";
   }
	
	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
		model.addAttribute("pass", 40);
		model.addAttribute("fail", 60);
		return "pieChart";
   }
	
	@GetMapping("/listproduct")
	public String listProduct(Model model) {
		List<Product> list = productRepository.findAll();					
		Optional<Klant>klant = klantRepository. findById((long) 1);	
		Long id = klant.get().getId();
		String name = klant.get().getName();
		String bedrijf = klant.get().getBedrijf();		
		String postcode = klant.get().getPostcode();		
		String telefoon = klant.get().getTelefoon();		
		String land = klant.get().getLand();
				
		model.addAttribute("list",list);		
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("bedrijf", bedrijf);
		model.addAttribute("postcode", postcode);
		model.addAttribute("telefoon", telefoon);
		model.addAttribute("land", land);
		
		return "listproduct";
	} 
	
/*//			
//	@RequestMapping(path="/lijst", method = RequestMethod.GET)
//	public List<Product> lijst(){
//		List<Product> list = productRepository.findAll();
//		return list;
//	}
*/
	}
