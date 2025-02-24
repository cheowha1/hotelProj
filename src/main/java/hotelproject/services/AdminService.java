package hotelproject.services;

import hotelproject.repositories.vo.UserVo;

public interface AdminService {

	 boolean updateUser(String id, UserVo user);
	 boolean modifyUserPoints(String id, int amount, String type);
	 boolean updateUserGrade(String id, String grade);
}