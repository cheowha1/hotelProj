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

import hotelproject.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	  @Autowired
	    private ReviewService reviewService;

	    @PostMapping("/add")
	    public boolean addReview(@RequestParam int hotelNo, @RequestBody AddReviewRequest request) {
	        return reviewService.addReview(hotelNo, request);
	    }

	    @PutMapping("/update/{reviewNo}")
	    public boolean updateReview(@PathVariable int reviewNo, @RequestBody UpdateReviewRequest request  ) {
	        return reviewService.updateReview(reviewNo, request);
	    }

	    @DeleteMapping("/delete/{reviewNo}/{userId}")
	    public boolean deleteReview(@PathVariable int reviewNo, @PathVariable String userId) {
	        return reviewService.deleteReview(reviewNo, userId);
	    }
	    
}
