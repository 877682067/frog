package com.ac.circular.upload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ac.circular.attach.entity.Attach;
import com.ac.circular.attach.entity.AttachMapping;
import com.ac.circular.attach.service.AttachService;
import com.ac.circular.role.service.RoleService;
import com.ac.common.BaseCtronller;
import com.ac.common.util.BeanUtils;

@Controller
public class UploadController  extends  BaseCtronller{
	@Autowired
	private AttachService attachService;
	
	@ResponseBody
	@RequestMapping(value="/upload/editorUploadImg.action",method={RequestMethod.POST})
	public String upload(@RequestParam(value = "upload", required = false) MultipartFile file, HttpServletRequest request)
	{
		 	System.out.println("开始");  
	        String path = request.getSession().getServletContext().getRealPath("common/upload/image/circular");  
	        String  oldName = file.getOriginalFilename();
	        String fileName = new Date().getTime()+oldName.substring(oldName.lastIndexOf("."));  
	        System.out.println(path);  
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	  
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        String callback =request.getParameter("CKEditorFuncNum");   
	        StringBuilder script = new StringBuilder();
	        script.append("<script type=\"text/javascript\">");  
	        script.append("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +getPath(request)+"/common/upload/image/circular/"+ fileName + "','')");   
	        script.append("</script>");  
		
		return script.toString();
	}
	@ResponseBody
	@RequestMapping(value="/upload/upload.action",method={RequestMethod.POST})
	public List<Long> uploadFile(@RequestParam(value = "file_data", required = false) MultipartFile[] files, HttpServletRequest request)
	{
		List<Long> ids = new LinkedList<Long>();
		try {
			int i =0;
			for(MultipartFile  file:files)
			{
				String path = request.getSession().getServletContext().getRealPath("common/upload/attach");  
				String  oldName = file.getOriginalFilename();
				String fileName = new Date().getTime()+""+ i++  +oldName.substring(oldName.lastIndexOf("."));  
				System.out.println(path);  
				File targetFile = new File(path, fileName);  
				if(!targetFile.exists()){  
					targetFile.mkdirs();  
				}  
				
				file.transferTo(targetFile);
				Attach attach = new Attach();
				attach.setCreatetime(new Date());
				attach.setCreator(super.getUser(request).getId());
				attach.setPath("/common/upload/attach/"+fileName);
				attach.setName(oldName);
				this.attachService.save(attach);
				ids.add(attach.getId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}
	@RequestMapping(value="/upload/download.action",method={RequestMethod.POST})
	public void downloadFile( HttpServletRequest request,HttpServletResponse response)
	{
		Attach att = BeanUtils.reflectParameter(Attach.class, request);
		/*try {
			att.setName(new String(att.getName().getBytes("ISO8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
		String path = request.getSession().getServletContext().getRealPath(att.getPath());  
		File file = new File(path);
		InputStream fis;
		try {
			fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();
	        // 设置response的Header
	        response.addHeader("Content-Disposition", "attachment;filename=" + new String(att.getName().getBytes("utf-8"),"ISO-8859-1"));
	        response.addHeader("Content-Length", "" + file.length());
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream;charset=utf-8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
