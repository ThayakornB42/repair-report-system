package com.cp.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private HistoryService historyService;
    
    @Autowired
    private RequestService requestService;
    
    @Autowired
    private RepairmanService repairmanService;
    
    @Autowired
    private UserService userService;
    
    ///=============================== THIS IS SHOW INDEX ===========================================
	@RequestMapping("/")
	public String showIndex(Model model, @RequestParam(value = "id", required = false) Integer id) {
		List<Request> request = (List<Request>) requestService.findAll();
		model.addAttribute("repair_request", request);
		return "index";
	}
	
	///FOR ADMIN
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<Request> request = (List<Request>) requestService.findAll();
		model.addAttribute("repair_request", request);
		return "admin";
	}	
	
	/// ==============================================================================================
	
	/// ============================== THIS IS SHOW HISTORY ==========================================
    @GetMapping("/request/{id}")
    public String showRequestDetails(@PathVariable Integer id, Model model) {
        Request request = requestService.getRequestById(id);
        History history = historyService.getHistoryById(id);
        if (request == null) {
            return "redirect:/";
        }
        if (history == null) {
        	model.addAttribute("repair_request", request);
        	return "history";
        }
        model.addAttribute("repair_request", request);
        model.addAttribute("history_request", history);
        return "history_responsed";
    }
    
    @GetMapping("/admin/request/{id}")
    public String showRequestDetailsforAdmin(@PathVariable Integer id, Model model) {
        Request request = requestService.getRequestById(id);
        History history = historyService.getHistoryById(id);
        if (request == null) {
            return "redirect:/admin";
        }
        if (history == null) {
        	model.addAttribute("repair_request", request);
        	return "history_admin";
        }
        model.addAttribute("repair_request", request);
        model.addAttribute("history_request", history);
        return "history_responsed_admin";
    }
    
    @GetMapping("/admin/delete/{id}")
    public String deleteRequest(@PathVariable Integer id) {
        // ดำเนินการลบรายการที่มี ID ตามที่ระบุ
        requestService.deleteRequestById(id);
        // ส่งกลับไปที่หน้ารายการแจ้งซ่อมหลัก
        return "redirect:/admin";
    }
    /// =============================================================================================
    
    /// ========================================REGISTER=============================================
    
    @GetMapping("/register/user")
    public String showRegisterForm(Model model) {
    	
    	model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register/user")
    public String processRegistrationForm(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
    ///THIS IS FOR REPAIRRMAN
    @GetMapping("/register/repairman")
    public String showRegisterREpairman(Model model) {
    	model.addAttribute("repairman", new Repairman());
        return "repairman-register";
    }
    @PostMapping("/register/repairman")
    public String processRegisterRepairman(@ModelAttribute Repairman repairman) {
    	repairmanService.updateRepairman(repairman);
        return "redirect:/";
    }
  
    ///==============================================================================================
    /// =============================== THIS IS PROFILE =============================================
    
    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "profile";
    }
    
    @PostMapping("/edit_profile/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-profile";
    }
    
	@PostMapping("/update_profile/{id}")
	public String updateProfile(@PathVariable Integer id, @ModelAttribute User newuser,
		BindingResult result, Model model) {
    	User user = userService.getUserById(id);
	  	if (result.hasErrors()) {
		  	newuser.setUser_id(id);   
		    return "update-profile";
	    }
	  	System.out.println("This is newuser : " + newuser);
	  	user.setUsername(newuser.getUsername());
	  	user.setEmail(newuser.getEmail());
	  	user.setTel(newuser.getTel());
	  	user.setAddress(newuser.getAddress());
	  	userService.updateUser(user);
	    return "redirect:/profile/{id}";
	}
    ///==============================================================================================
    
    
    /// ================================ THIS IS FOR REQUEST =========================================
	@PostMapping("/repair_request")
	public String requesttRepair(@Validated Request request, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "requestForm";
		}
		User user = request.getUser();
		if (user.getUser_id() == null) {
			userService.updateUser(user);
		}
		requestService.updateRequest(request);
		return "redirect:/";
	}
	@GetMapping("/repair_request")
	public String showForm(Model model) {
		Request request = new Request();
		model.addAttribute("repair_request", request);
		
	    List<User> user =  userService.getAlluser();
	    model.addAttribute("user", user);
	  
		return "requestForm";
	}
	///===============================================================================================
	
	///===================================THIS IS FOR RESPONSE =======================================
    @GetMapping("/response/{id}")
    public String viewHistoryDetails(@PathVariable Integer id, Model model) {
        Request request = requestService.getRequestById(id);
        History history = historyService.getHistoryById(id);
        List<Repairman> repairman = repairmanService.getAllRepairman();
        
        if (request == null) {
            return "redirect:/admin";
        }
        ///THIS GO TO CREATE HISTORY
        if (history == null) {
        	///CREATE NEW CLEAN HISTORY TO FILL YOUR DATA
            History newHistory = new History();
            ///GET ID ID FROM REQUEST TO MAKE IT FOREIGNKEY
            model.addAttribute("repair_request", request);
            System.out.println("This request : " +request);
            model.addAttribute("history_request", newHistory);
            model.addAttribute("repairman", repairman);
            System.out.println("This is add responsed");
            return "add-response";
        }
        ///THIS GO TO UPDATE HISTORY
        model.addAttribute("history_request", history);
        System.out.println("This is edit responsed");
        return "response";
    } 
    /// UPDATE RESPONSE
	@PostMapping("/responsed/{id}")
	public String updateHistory(@PathVariable Integer id, @ModelAttribute History newhistory) {
		History history = historyService.getHistoryById(id);
	    Request request = requestService.getRequestById(id);
	    if (history == null) {
	    	System.out.println("This is history is null");
	        return "redirect:/admin";
	    }
	    
        String status = newhistory.getRequest().getRq_status();
	    history.setHr_date(newhistory.getHr_date());
	    history.setHr_solve(newhistory.getHr_solve());

        request.setRq_status(status);
        requestService.updateRequest(request);
	    historyService.updateHistory(history);
	    return "redirect:/admin";
	}
	/// CREATE NEW RESPONSE
	@PostMapping("/add-responsed/{rq_id}")
	public String createHistory(@PathVariable Integer rq_id, @ModelAttribute History newHistory) {

	    Request request = requestService.getRequestById(rq_id);

	    if (request == null) {
	        return "redirect:/admin";
	    }

	    User user = request.getUser();
	    if (user == null) {
	        return "redirect:/admin";
	    }

	    String status = newHistory.getRequest().getRq_status();
	    newHistory.setRequest(request);
	    request.setRq_status(status);

	    requestService.updateRequest(request);
	    historyService.updateHistory(newHistory);

	    return "redirect:/admin";
	}
	///=================================================================================================


}
