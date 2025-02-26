package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.UserVo;
import hotelproject.services.AdminService;
import hotelproject.services.ReviewService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	 @Autowired
	    private AdminService adminService;
	 
	 @Autowired
	    private ReviewService reviewService;

	    @PutMapping("/{id}")
	    public boolean updateUser(@PathVariable String id, @RequestBody UserVo user) {
	        return adminService.updateUser(id, user);
	    }

	    @PostMapping("/{id}/points/give")
	    public boolean givePoints(@PathVariable String id, @RequestParam int amount) {
	        return adminService.modifyUserPoints(id, amount, "지급");
	    }

	    @PostMapping("/{id}/points/deduct")
	    public boolean deductPoints(@PathVariable String id, @RequestParam int amount) {
	        return adminService.modifyUserPoints(id, -amount, "차감");
	    }

	    @PutMapping("/{id}/grade")
	    public boolean updateUserGrade(@PathVariable String id, @RequestParam String grade) {
	        return adminService.updateUserGrade(id, grade);
	    }
	    
	    @DeleteMapping("/{reviewNo}")
	    public boolean deleteReviewByAdmin(@PathVariable int reviewNo) {
	        return reviewService.deleteReviewByAdmin(reviewNo);
	    }
}

