package hotelproject.services;

import hotelproject.controllers.UpdateUserGradeRequest;
import hotelproject.controllers.UpdateUserPointRequest;
import hotelproject.repositories.vo.UserVo;

public interface AdminService {

	 boolean updateUser(String id, UserVo user);
	 boolean modifyUserPoints(String id, UpdateUserPointRequest request);
	 boolean updateUserGrade(String id, UpdateUserGradeRequest request);
}