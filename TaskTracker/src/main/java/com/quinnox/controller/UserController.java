package com.quinnox.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.model.Result;
import com.quinnox.model.User;

@RestController("/user")
public class UserController {
	@Autowired
	private MongoOperations operations;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json",consumes = "application/json")
	public Result createUser(HttpServletRequest request,@RequestBody User user) {
		if (!validateData(user))
			return new Result("ERROR", "Bad input data");
		try {
			operations.save(user);
			BasicQuery q1 = new BasicQuery("{}");
			 
			return new Result("SUCCESS", "",operations.find(q1, User.class));
		} catch (Exception e) {
			return new Result("ERROR", e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public Result getUser(HttpServletRequest request) {
		Query q1;
		try {
			if (request.getParameter("userId") == null)
				 q1 = new BasicQuery("{}").limit(20);
			else
				 q1 = new BasicQuery("{userId:\""+request.getParameter("userId")+"\"}").limit(20);
			
			return new Result("SUCCESS", operations.find(q1, User.class));
			// return new Result("SUCCESS", );
		} catch (Exception e) {
			return new Result("ERROR", e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE,consumes = "application/json", produces = "application/json")
	public Result deleteUser(HttpServletRequest request,@RequestBody User user) {
		Query q1;
		try {
			if (user.getId() == null)
				return new Result("ERROR", "Bad input data");
			else
				 q1 = new BasicQuery("{_id:\""+user.getId()+"\"}").limit(20);
			 operations.remove(q1, User.class);
			return new Result("SUCCESS", operations.find(new BasicQuery("{}").limit(20), User.class));
			// return new Result("SUCCESS", );
		} catch (Exception e) {
			return new Result("ERROR", e.getMessage());
		}
	}

	
	@RequestMapping(method = RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	public Result updateUser(HttpServletRequest request, @RequestBody User user) {
		Query q1;
		try {
			if (user.getId() == null)
				return new Result("ERROR", "Bad input data");
				
				Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(user.getId()));
			query.fields().include("_id");
			
			Update update = new Update();
			update.set("userId", user.getUserId());
			update.set("name", user.getName());
			 operations.updateFirst(query, update, User.class);
			return new Result("SUCCESS", operations.find(new BasicQuery("{}").limit(20), User.class));
			// return new Result("SUCCESS", );
		} catch (Exception e) {
			return new Result("ERROR", e.getMessage());
		}
	}


	
	
	public boolean validateData(User user) {

		if (user.getName() == null || user.getUserId() == null )
			return false;
		else
			return true;
	}
}
