package com.co.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.co.model.Company;
import com.co.model.CompanyEnum;


@Path("/json/companysvc")
public class CompanyService {

	//URL - http://localhost:8080/AngularREST/rest/json/companysvc/getlist
	@GET
	@Path("/getlist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanyList() {
		List <Company> cData = new ArrayList<Company>(); 
		cData.addAll(CompanyEnum.instance.getModel().values());
		return Response.status(201).entity(cData).header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
	
	//URL - http://localhost:8080/AngularREST/rest/json/companysvc/getcdtls/101
	@GET
	@Path("/getcdtls/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompanyDtls(@PathParam("id") String cid) {
		Company cData = CompanyEnum.instance.getModel().get(cid);
		return cData;
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCompany(Company company) {
		String result = "Company saved : " + company;
		CompanyEnum.instance.getModel().put(company.getCompanyId(), company);
		return Response.status(201).entity(result).header("Access-Control-Allow-Origin", "*")
		        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateCompanyData(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("address") String address,
			@FormParam("city") String city,
			@FormParam("country") String country,
			@FormParam("email") String email,
			@FormParam("phoneNumber") String phoneNumber,
			@FormParam("owners") String owners) {
		
		Company updData = CompanyEnum.instance.getModel().get(id);
		String result = "";
		if(updData!=null){
		updData.setName(name);
		updData.setAddress(address);
		updData.setCity(city);
		updData.setCountry(country);
		updData.setEmail(email);
		updData.setPhoneNumber(phoneNumber);
		updData.setOwners(owners);
		
		CompanyEnum.instance.getModel().put(id, updData);
			result = "Update Succesfull : " + updData;
		}else{
			result = "Update Fail No Data Found : " + updData;
		}
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addCompany(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("address") String address,
			@FormParam("city") String city,
			@FormParam("country") String country,
			@FormParam("email") String email,
			@FormParam("phoneNumber") String phoneNumber,
			@FormParam("owners") String owners) {
		
		Company updData = new Company();
		String result = "";
		
		Map<String, Company> data = CompanyEnum.instance.getModel();
		
		if(id!=null){		
		updData.setCompanyId(id);
		updData.setName(name);
		updData.setAddress(address);
		updData.setCity(city);
		updData.setCountry(country);
		updData.setEmail(email);
		updData.setPhoneNumber(phoneNumber);
		updData.setOwners(owners);
		CompanyEnum.instance.getModel().put(id, updData);
			result = "Company Added Succesfull : ";
		}else{
			result = "Company Added Fail : ";
		}


		return Response.status(201).entity(result).build();
	}

}
