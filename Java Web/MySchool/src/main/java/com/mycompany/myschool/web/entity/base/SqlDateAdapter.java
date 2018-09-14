package com.mycompany.myschool.web.entity.base;

import java.sql.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlDateAdapter extends XmlAdapter<java.util.Date, java.sql.Date> {

	@Override
	public Date unmarshal(java.util.Date v) throws Exception {
		if (v == null) {
			return null;
		}
		
		return new java.sql.Date(v.getTime());
	}

	@Override
	public java.util.Date marshal(Date v) throws Exception {
		if (v == null) {
			return null;
		}

		return new java.util.Date(v.getTime());
	}

}
