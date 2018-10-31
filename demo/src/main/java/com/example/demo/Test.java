package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);		
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private KlantRepository klantRepository;
	
	
	@Autowired
	private CalendarRepository calendarRepository;
	
	@Autowired
	private DataRepository dataRepository;
	
	
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

	
	@GetMapping("/progress")
	public String progressBar() {
		return "progressbar";
	}
	
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
	
	@GetMapping("/test")
	public String list(Model model) {
		List<Klant> list = klantRepository.findAll();
		model.addAttribute("list", list);
		return "klant";
   }
	
	
	
	
	@GetMapping("/listproduct")
	public String listProduct(Model model) {
		List<Product> list = productRepository.findAll();			
		List<Klant>klantlijst = klantRepository.findAll();
		
//		Map<String , RestData> data = new LinkedHashMap<>();
		

//		List<String>listen = new ArrayList<>();
//		String api ="";
//	for (Map.Entry<String, RestData> entry : data.entrySet()) {			
//		     api = entry.getValue().getApi();
//		     listen.add(api);
//		}

//	String t ="";
//	Long userId = null;
//	Long uId = null;
//	
//		
//	ObjectMapper mapper = new ObjectMapper();
//	try {
//		Data datas = mapper.readValue("https://jsonplaceholder.typicode.com/todos/1", Data.class);
//		t = datas.getTitle();
//		userId = datas.getUserId();
//		uId = datas.getId();
//		
//	} catch (JsonParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (JsonMappingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
		//String output = null;
		try {

			URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code:" + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			
			while ((output = br.readLine()) != null) {
				System.out.println(output);	
				dataRepository.save(new Data(output));
				model.addAttribute("data", output);				
				logger.info(output);
			}
			
			conn.disconnect();
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		//return output;
		//}

		Optional<Klant>klant = klantRepository. findById((long) 1);	
		Long id = klant.get().getId();
		String name = klant.get().getName();
		String bedrijf = klant.get().getBedrijf();		
		String postcode = klant.get().getPostcode();		
		String telefoon = klant.get().getTelefoon();		
		String land = klant.get().getLand();
				
		model.addAttribute("list",list);	
		model.addAttribute("klantlijst", klantlijst);
		System.out.println("Commit changes!");
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("bedrijf", bedrijf);
		model.addAttribute("postcode", postcode);
		model.addAttribute("telefoon", telefoon);
		model.addAttribute("land", land);
		
	//	model.addAttribute("data", t);
		//System.out.println(t.toString());
		
		return "listproduct";
	} 
	
	@GetMapping("/zoeken")
	public String search() {
		return "search";
	}
	
	@GetMapping("/tablen")
	public String table() {
		return "tables";
	}

	@GetMapping("/kalender")
	public String Calendar(Model model) {
		List<Calendar>calendarLijst= calendarRepository.findAll();
		
		String title ="";
		String start ="";
		String end ="";
		
		Map<String, Calendar>map = new LinkedHashMap<>();
		for (Map.Entry<String, Calendar> entry : map.entrySet()) {			
			
			title = entry.getValue().getTitle();
			start = entry.getValue().getStart();
			end =   entry.getValue().getEnd();
			//System.out.println("k : " + entry.getKey() + " value : " + entry.getValue());
		}
		
		for(Calendar c : calendarLijst) {	
			title = c.getTitle();
			start = c.getStart();
			end = c.getEnd();
			map.put("calendar", c);	
		}
			
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		 model.addAttribute("title", title);
		 model.addAttribute("start", start);
		 model.addAttribute("end", end);
		 model.addAttribute("currentdate", now);
		 model.addAttribute("calendar", map);
		return "calendar";
	}
	
	@GetMapping("/form")
	public String formKlant(Model model) {
		List<Klant>klantlist = klantRepository.findAll();
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
		model.addAttribute("klant", new Klant());
		model.addAttribute("klantlist", klantlist);
		return "addKlant";
	}
	
	
	/*@RequestMapping(value = "/vacation/getVacation", method = RequestMethod.GET)
    public
    @ResponseBody
    String getVacation(HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 111);
        map.put("title", "event1");
        map.put("start", "2012-4-15");
        map.put("url", "http://yahoo.com/");
        // Convert to JSON string.
        String json = new Gson().toJson(map);
        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return json;
    }*/
	
/*//			
//	@RequestMapping(path="/lijst", method = RequestMethod.GET)
//	public List<Product> lijst(){
//		List<Product> list = productRepository.findAll();
//		return list;
//	}
*/
	}
