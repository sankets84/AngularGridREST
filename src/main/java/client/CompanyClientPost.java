package com.co.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CompanyClientPost {

	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/AngularREST/rest/json/companysvc/post");

			//String input = "{\"companyId\":\"101\",\"name\":\"NEW COMPANY\",\"address\":\"10-A BANDRA(w)\",\"city\":\"MUMBAI\",\"country\":\"INDIA\",\"email\":\"newcompany@test.com\",\"phoneNumber\":\"9980808080\"}";
			String input = "{\"companyId\":\"101\",\"name\":\"NEW COMPANY\",\"address\":\"10-A BANDRA(w)\",\"city\":\"MUMBAI\",\"country\":\"INDIA\",\"email\":\"newcompany@test.com\",\"phoneNumber\":\"9980808080\",\"owners\":\"Mr.Amir,Mr.Test,Mr.ABC\"}";

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
