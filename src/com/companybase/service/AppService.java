package com.companybase.service;

import java.sql.Connection;

import javax.swing.JOptionPane;

import com.companybase.dao.AboutDao;
import com.companybase.dao.AboutDaoImpl;
import com.companybase.dao.ApplicationStatusDao;
import com.companybase.dao.ApplicationStatusDaoImpl;
import com.companybase.dao.CommentsDao;
import com.companybase.dao.CommentsDaoImpl;
import com.companybase.dao.CompanyDao;
import com.companybase.dao.CompanyDaoImpl;
import com.companybase.dao.ContactDao;
import com.companybase.dao.ContactDaoImpl;
import com.companybase.dao.CountryDao;
import com.companybase.dao.CountryDaoImpl;
import com.companybase.dao.DetailsDao;
import com.companybase.dao.DetailsDaoImpl;
import com.companybase.model.AboutText;
import com.companybase.model.ApplicationStatus;
import com.companybase.model.Company;
import com.companybase.model.ContactPerson;
import com.companybase.model.Country;
import com.companybase.model.Details;
import com.companybase.model.UserComments;

public class AppService {

	private Connection conn;
	
	public CompanyDao companyDao;
	public DetailsDao detailsDao;
	public AboutDao aboutDao;
	public CommentsDao commentsDao;
	public ContactDao contactDao;
		
	public ApplicationStatusDao statusDao;
	public CountryDao countryDao;
	
	public java.util.List<Company> companies;
	public java.util.List<Details> details;
	public java.util.List<AboutText> aboutList;
	public java.util.List<UserComments> comments;
	public java.util.List<ContactPerson> persons;
	
	public java.util.List<ApplicationStatus> statusList;
	public java.util.List<Country> countryList;
	
	public AppService(Connection conn) {
		
		this.conn = conn;		
		this.initVars();
		
	}	
	
	private void initVars() {
		
		this.companyDao = new CompanyDaoImpl(conn);
		this.detailsDao = new DetailsDaoImpl(conn);
		this.aboutDao = new AboutDaoImpl(conn);
		this.commentsDao = new CommentsDaoImpl(conn);
		this.contactDao = new ContactDaoImpl(conn);
		
		this.statusDao = new ApplicationStatusDaoImpl(conn);
		this.countryDao = new CountryDaoImpl(conn);
		
		this.companies = companyDao.findAll();
		this.details = detailsDao.findAll();
		this.aboutList = aboutDao.findAll();
		this.comments = commentsDao.findAll();
		this.persons = contactDao.findAll();
		
		this.statusList = statusDao.findAll();
		this.countryList = countryDao.findAll();
		
	}
	
	public long saveCompany(Company company) {
		
		String name = company.getName();
		String email = company.getEmail();
		String website = company.getWebsite();
		
		if (name == null || name.isEmpty()) {
			
			String msg = "The value Companyname must not be empty";
			JOptionPane.showMessageDialog(null, msg);
						
		}
		
		if (!email.isEmpty() && !isValidEmail(email)) {
			
			String emailMsg = "Invalid e-mail address";
			JOptionPane.showMessageDialog(null, emailMsg);			
			return 0;
			
		}
		
		if (!website.isEmpty() && !isValidWebsite(website)) {
			
			String websiteMsg = "Invalid website address";
			JOptionPane.showMessageDialog(null, websiteMsg);			
			return 0;
			
		}
		
		if (!name.isEmpty() || isValidEmail(email) 
			|| isValidWebsite(website)) {
			
			this.companyDao.insert(company);
			return company.getId();
						
		}
		
		return 0;
		
	}
	
	public void updateCompany(long id, Company company) {
		
		String name = company.getName();
		if (name.isEmpty() || name.isBlank()) return;
		this.companyDao.update(id, company);
		
	}
	
	public void updateDetails(long id, Details details) {
		
		details.setId(id);		
		this.detailsDao.update(details);
		
		
	}
		
	public void saveDetails(Details details) {
		
		String linkedin = details.getLinkedinAddress();
		String facebook = details.getFacebookAddress();
		
		if (!linkedin.isEmpty() && !isValidWebsite(linkedin)) {
			
			String msg = "Invalid Linkedin address";
			JOptionPane.showMessageDialog(null, msg);
			return;
			
		}
		
		if (!facebook.isEmpty() && !isValidWebsite(facebook)) {
			
			String msg = "Invalid Facebook address";
			JOptionPane.showMessageDialog(null, msg);
			return;
			
		}
		
		this.detailsDao.insert(details);
		
	}

	public void saveAboutText(AboutText about) {
		
		String text = about.getAboutText();
		
		if (text.length() > 750) {
			
			String msg = "Max number of about text is 750";
			JOptionPane.showConfirmDialog(null, msg);
			return;
			
		}		
			
		this.aboutDao.insert(about);
				
	}
	
	public void saveComments(UserComments comments) {
		
		String text = comments.getComments();
		
		if (text.length() > 750) {
			
			String msg = "Max length of comments text is 750";
			JOptionPane.showConfirmDialog(null, msg);
			
		}		
		
		else if (text.length() <= 750) {
			
			this.commentsDao.insert(comments);
			
		}		
	}
	
	public void saveContacts(long companyId, 
		java.util.List<ContactPerson> list) {
				
		for (int i = 0; i < list.size(); i++) {
        	
        	ContactPerson person = list.get(i);
        	person.setId(companyId);
        	this.contactDao.insert(person);
        	
        }
		
	}
	
	public boolean isValidWebsite(String website) {

	    if (website == null || website.trim().isEmpty()) {
	        return true;
	    }

	    website = website.trim();

	    return website.matches(
	        "^(https?://)?([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}(/.*)?$"
	    );
	}
		
	private boolean isValidEmail(String email) {

	    if (email == null || email.trim().isEmpty()) {
	        return false;
	    }

	    return email.matches(
	    	"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
	    );
	}
}
