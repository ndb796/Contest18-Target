package com.mycompany.myschool.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.myschool.web.entity.base.FileMeta;
import com.mycompany.myschool.web.util.Util;
 
@Controller
@RequestMapping("/fileController")
public class FileController {
 
    /***************************************************
     * URL: /rest/controller/upload  
     * upload(): receives files
     * @param request : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody void upload(MultipartHttpServletRequest request, HttpServletResponse response) {
    	String school_id = request.getParameter("school_id");

    	System.out.println(request + "/" + request.getParameter("token_key"));
    	
    	List<FileMeta> files = new ArrayList<FileMeta>();
    	
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
 
             //2.2 if files > 10 remove the first from the list
//             if(files.size() >= 10)
//                 files.pop();
 
             //2.3 create new fileMeta
             FileMeta fileMeta = new FileMeta();
             fileMeta.setFileName(mpf.getOriginalFilename());
             fileMeta.setRealName(school_id + "_" + mpf.getOriginalFilename());
             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
             fileMeta.setFileType(mpf.getContentType());
 
             try {
                fileMeta.setBytes(mpf.getBytes());
 
                File dir = new File(request.getRealPath("/") + "/images/upload/school/temp/");
            	if (!dir.exists()) {
            		dir.mkdirs();
                }
            	
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(new File(dir, Util.PATH_FILTER(school_id) + "_" + mpf.getOriginalFilename())));
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
        // result will be like this
        
        JSONObject obj = new JSONObject();
 		JSONArray jsonArray = new JSONArray();
 		
        for (FileMeta fileMeta : files) {
        	JSONObject fileJson = new JSONObject();
        	fileJson.put("name", fileMeta.getFileName());
        	fileJson.put("real_name", fileMeta.getRealName());
        	fileJson.put("size", fileMeta.getFileSize());
			
			jsonArray.put(fileJson);
        }
        
        obj.put("files", jsonArray);
        try {
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			System.out.println(obj);
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
//        return files;
    }
    
    @RequestMapping(value="/upload2", method = RequestMethod.POST)
    public @ResponseBody void upload2(MultipartHttpServletRequest request, HttpServletResponse response) {
    	JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
			JSONObject rcmmdJson = new JSONObject();
			rcmmdJson.put("no", "1");
			rcmmdJson.put("files", "Chrysanthemum.jpg");
			
			jsonArray.put(rcmmdJson);
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			String data = "{" +  
					  		"\"files\":" + 
					  			"[" + 
					  				"{" + 
					  					"\"name\": \"thumb2.jpg\"," +
					  					"\"size\": 46353," +
					  					"\"delete_url\": \"http://url.to/delete /file/\"," +
					  					"\"delete_type\": \"DELETE\"" +
						      		"}" +
					  			"]" +
							"}";
			out.write(data);
			System.out.println(data);
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    /***************************************************
     * URL: /rest/controller/get/{value}
     * get(): get file as an attachment
     * @param response : passed by the server
     * @param value : value from the URL
     * @return void
     ****************************************************/
    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
     public void get(HttpServletResponse response,@PathVariable String value){
//         FileMeta getFile = files.get(Integer.parseInt(value));
//         try {      
//                response.setContentType(getFile.getFileType());
//                response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
//                FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
//         }catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//         }
     }
}
