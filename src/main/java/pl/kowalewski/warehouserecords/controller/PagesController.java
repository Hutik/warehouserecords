package pl.kowalewski.warehouserecords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

	@GetMapping("")
	public String toHomePage() {		
		return "home";
	}

	@GetMapping("/login")
	public String toLoginPage(){
		return "login";
	}

	@GetMapping("/warehouse")
	public String toWarehousePage(){
		return "warehouse";
	}

	@GetMapping("/userPanel/settings")
	public String toUserSettings(){
		return "userPanel/settings";
	}

}
