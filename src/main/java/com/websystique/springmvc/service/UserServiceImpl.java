package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.websystique.springmvc.model.RestApiFactory;
import com.websystique.springmvc.model.Task;
import com.websystique.springmvc.model.User;



@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		 final String username = "praveenreddy.velcon@gmail.com";
	       final String password = "APRA4584@reddy";
	       try {
		
		RallyRestApi restApi = RestApiFactory.getRestApi();
        restApi.setApplicationName("Sample Project");
		 QueryRequest storyRequest = new QueryRequest("HierarchicalRequirement");
	        storyRequest.setFetch(new Fetch("FormattedID","Name","Owner","UserName", "EmailAddress","Tasks"));
		 QueryResponse storyQueryResponse;
		
			storyQueryResponse = restApi.query(storyRequest);
			Gson gson = new Gson();
			int totalTasks = 0;
         for(int i=0; i< storyQueryResponse.getResults().size(); i++){
         JsonObject storyJsonObject = storyQueryResponse.getResults().get(i).getAsJsonObject();

         System.out.println("Name: " + storyJsonObject.get("Name"));
         System.out.println("FormattedID: " + storyJsonObject.get("FormattedID"));
         System.out.println("UserName: " + storyJsonObject.get("EmailAddress"));
         String requestID = gson.toJson( storyJsonObject.get("FormattedID"));
	       String requestBean = gson.toJson( storyJsonObject.get("Name"));
	       
	       JsonObject userObject = storyJsonObject.get("Owner").getAsJsonObject().getAsJsonObject();
	       //System.out.println(userObject.get("UserName"));
	    String owner=   gson.toJson(userObject.get("EmailAddress").getAsString()); 
	    String EmailAddress=   gson.toJson(userObject.get("EmailAddress").getAsString()); 
	       System.out.println(owner);
	       int numberOfTasks = storyJsonObject.getAsJsonObject("Tasks").get("Count").getAsInt();
	       List<Task> task=new ArrayList<Task>();
           if(numberOfTasks > 0) {
               totalTasks += numberOfTasks;
               QueryRequest taskRequest = new QueryRequest(storyJsonObject.getAsJsonObject("Tasks"));
               taskRequest.setFetch(new Fetch("Name","FormattedID"));
               //load the collection
               JsonArray tasks = restApi.query(taskRequest).getResults();
              
               for (int j=0;j<numberOfTasks;j++){
                   System.out.println("Name: " + tasks.get(j).getAsJsonObject().get("Name") + tasks.get(j).getAsJsonObject().get("FormattedID").getAsString()+ storyJsonObject.get("FormattedID"));
              Task task1=new Task();
              task1.setTaskPriority(tasks.get(j).getAsJsonObject().get("FormattedID").getAsString());
              task1.setTaskName(tasks.get(j).getAsJsonObject().get("Name").getAsString());
              //task1.setTaskStatus(taskStatus);
              task.add(task1);
               }
               
           }

	      // int requestBean = mapper.writeValueAsString( storyJsonObject.get("Name"));
	       users.add(new User(counter.incrementAndGet(),requestID,requestBean,EmailAddress,  owner,task));
        //JsonObject userObject = storyJsonObject.get("Owner").getAsJsonObject();
         //System.out.println(userObject.get("EmailAddress")); 
     }
		}
         catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
    

	       
	
		return users;
	}

}
