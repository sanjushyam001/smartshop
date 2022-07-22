package com.smartshop.admin.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.smartshop.common.entity.User;



public class UserCSVExporter {

	public void export(List<User> listUser , HttpServletResponse response) throws IOException {
		
		DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timeStamp=dateFormatter.format(new Date());
		String fileName="user_"+timeStamp+".csv";
		response.setContentType("text/csv");
		String headerKey="Content-Disposition";
		String headerValue="attachment;fileName="+fileName;
		response.setHeader(headerKey, headerValue);
		
		ICsvBeanWriter csvWriter=new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader= {"User ID","Email","First Name","Last Name","Roles","Enabled"};
		String[] fileMapping= {"id","email","firstName","lastName","roles","enabled"};
		csvWriter.writeHeader(csvHeader);
		for(User user:listUser) {
			csvWriter.write(user, fileMapping);
		}
		
		csvWriter.close();
	}
}