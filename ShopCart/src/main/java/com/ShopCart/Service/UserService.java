package com.ShopCart.Service;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopCart.Repository.UserRepository;
import com.ShopCart.beans.Color;
import com.ShopCart.beans.User;




@Service
public class UserService {
	
	@Autowired
	private UserRepository repo ;
	

	
public Boolean sendEmail(String message,String subject,String to ) {
		

	Boolean flag= false;
      String from="bhattsatish777@gmail.com";
      
				Properties properties = System.getProperties();
		        System.out.println( "Properties" + properties );
		        
		        
		        //set important info to properties object
		        
		        properties.put("mail.smtp.host","smtp.gmail.com");
		        properties.put("mail.smtp.port", "465");
		        properties.put("mail.smtp.ssl.enable", "true"); //ssl :secure security layer
		        properties.put("mail.smtp.auth", "true");
		        
		        
		        //step1: to get the session object
		        Session session = Session.getInstance(properties, new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						
						return new PasswordAuthentication("bhattsatish777@gmail.com", "ipbsqquujquyrbnd");
					}
		        	
		        	
				});
		        
		        
		        session.setDebug(true);
		        
		        
		        //step 2: Compose the message
		        MimeMessage m = new MimeMessage(session);
		        
		        try {

		            //from email
					m.setFrom(from);
					//adding recipient to message
					m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					//adding subject to message
					m.setSubject(subject);
					
					//adding text to message
					m.setText(message);
					
					
					
					
			    //step 3:sent message using transport class
					Transport.send(m);
					 System.out.println( "send success....." );
					flag=true;
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return flag;
	}



public List<User> getAllUser(){
	return repo.findAll();
	
	
}

//public User saveUser(User user) {
//	return repo.save(user);
//}



//to get color by its id
public User getUserById(Long id) {
	return repo.findById(id).get();
}

}
