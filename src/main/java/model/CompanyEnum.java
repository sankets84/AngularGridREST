package com.co.model;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CompanyEnum {

		instance;

		private Map<String, Company> cList = new LinkedHashMap<String, Company>();

		private CompanyEnum() {
		}

		public Map<String, Company> getModel() {
			return cList;
		}



}
