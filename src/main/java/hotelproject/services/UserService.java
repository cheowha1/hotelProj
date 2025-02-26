package hotelproject.services;

import hotelproject.repositories.vo.UserVo;

public interface UserService {
	  boolean registerUser(UserVo user);
	  UserVo loginUser(String id, String password);
}
