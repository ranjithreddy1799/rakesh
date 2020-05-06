package com.cg.inventory.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.inventory.dao.InventoryDao;
import com.cg.inventory.entity.User;
import com.cg.inventory.exceptions.LoginException;
import com.cg.inventory.util.InventoryConstants;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	private InventoryDao dao;

	Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);
	
	private Map<String, User> umap = new HashMap<>();
	
	@PostConstruct
	public void postConstruct() {
		umap.put("1001",new User("1001", "abc", "Rakesh_4", "user"));
		umap.put("1001",new User("1002", "abcd", "Ranjith@24", "admin"));
		umap.put("1001",new User("1003", "abcde", "ArvindKondeti", "user"));
	}

	@Override
	public User doLogin(String userId, String password) throws LoginException {
		User user = dao.viewUser(userId);
		logger.debug("doing login process");

		if (user != null && user.getPassword().contentEquals(password)) {
			logger.info("User Authenticated for " + userId);
			return user;
		}
		throw new LoginException(InventoryConstants.UNAUTHENTICATED_USERS);
	}

	@Override
	public String encryptUser(User user) {
		return encryptString(user.getUserID()) + "-" + encryptString(user.getUserName()) + "-"
				+ encryptString(user.getRole());
	}

	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch;
		for (int idx = 0; idx < arr.length; ++idx) {
			ch = arr[idx] + 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch;
		for (int idx = 0; idx < arr.length; ++idx) {
			ch = arr[idx] - 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}
}
