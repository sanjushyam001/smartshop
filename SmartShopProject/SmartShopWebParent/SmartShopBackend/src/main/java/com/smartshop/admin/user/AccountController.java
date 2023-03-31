package com.smartshop.admin.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smartshop.admin.FileUploadUtil;
import com.smartshop.admin.security.SmartShopUserDetails;
import com.smartshop.common.entity.User;

@Controller
public class AccountController {

	@Autowired
	private UserService service;
	
	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal SmartShopUserDetails loggedUser
			, Model model) {
		
		String email = loggedUser.getUsername();
		User user = service.getByEmail(email);
		model.addAttribute("user",user);
	
		return "account_form";
	}
	
	@PostMapping("/account/update")
	public String updateUserDetails(User user,RedirectAttributes redirectAttributes,@RequestParam("image") 
	MultipartFile multipartFile,
	@AuthenticationPrincipal SmartShopUserDetails loggedUser) throws IOException {
		
		System.out.println("USER: ###### "+user);
		System.out.println("FILE : ###### "+multipartFile.getOriginalFilename());
		if(!multipartFile.isEmpty()) {
			String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
//			if(user.getPhotos()==null) {
//				user.setPhotos(fileName);
//			}
			user.setPhotos(fileName);
			User savedUser = service.updateAccount(user);
			String uploadDir="user-photos/"+savedUser.getId();
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			
			
		}else {
			if(user.getPhotos().isEmpty())
				user.setPhotos(null);
			service.updateAccount(user);
		}
		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setLastName(user.getLastName());
		//service.saveUser(user);
		//redirectAttributes.addFlashAttribute("mesg", "The user has been updated successfully..");
		//return "account_form";
		//System.out.println(user);
		return "redirect:/account";
	}
}
