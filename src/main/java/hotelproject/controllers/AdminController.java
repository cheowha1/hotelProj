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

	    
	    
	    @PostMapping("/{id}/points/update")
	    public boolean givePoints(@PathVariable String id, @RequestBody UpdateUserPointRequest request) {
	        return adminService.modifyUserPoints(id,request);
	    }

	    @PutMapping("/{id}/grade")
	    public boolean updateUserGrade(@PathVariable String id, @RequestBody UpdateUserGradeRequest request) {
	        return adminService.updateUserGrade(id, request);
	    }
	    
	    @DeleteMapping("/{reviewNo}")
	    public boolean deleteReviewByAdmin(@PathVariable int reviewNo) {
	        return reviewService.deleteReviewByAdmin(reviewNo);
	    }
}

