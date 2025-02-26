package hotelproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	  @Autowired
	    private ReviewService reviewService;

	    @PostMapping("/add")
	    public boolean addReview(@RequestParam int hotelNo, @RequestParam String userId, 
	                             @RequestParam String comment, @RequestParam int rating) {
	        return reviewService.addReview(hotelNo, userId, comment, rating);
	    }

	    @PutMapping("/update/{reviewNo}")
	    public boolean updateReview(@PathVariable int reviewNo, @RequestParam String userId,
	                                @RequestParam String comment, @RequestParam int rating) {
	        return reviewService.updateReview(reviewNo, userId, comment, rating);
	    }

	    @DeleteMapping("/delete/{reviewNo}")
	    public boolean deleteReview(@PathVariable int reviewNo, @RequestParam String userId) {
	        return reviewService.deleteReview(reviewNo, userId);
	    }
	    
}
