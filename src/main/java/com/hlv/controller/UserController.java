package com.hlv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.validation.Valid;
import com.hlv.bean.Message;
import com.hlv.bean.UsersBean;
import com.hlv.entity.Roles;
import com.hlv.entity.Users;
import com.hlv.service.RoleService;
import com.hlv.service.UserService;
 
@Controller
@SessionAttributes("user") //if use @SessionAttributes then not get from db and not use copyNonNullProperties
//@RequestMapping("/")
public class UserController {
	
	private static final String get_users = "users";
	private static final String get_user_edit = "userEdit";
     	
	@Autowired
	private UserService userService;
     
	@Autowired
	private RoleService roleService;

    @RequestMapping(value = "/users", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchUsers(@ModelAttribute("bean") @Valid UsersBean bean, Model model) {  
      //  model.addAttribute("user", new UsersBean());    	
    	bean.setLimit(10);
        model.addAttribute("bean", this.userService.findUsers(bean));        
        return  get_users;
    }
    
    //new user
    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public String editUser(Model model){
    	List<Roles> roleListLeft = roleService.listRoles();
    	List<Roles> roleListRight = null;     
        model.addAttribute("user", new UsersBean());
        model.addAttribute("left", roleListLeft);
        model.addAttribute("right", roleListRight);  
        return get_user_edit;
    }
    
  //@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    //@Transactional //keep session many thing, ex: parent have many child, can get many child, if not use @Transactional --> can not get many child
    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") Long id, Model model){
    	List<Roles> roleListLeft = roleService.getRolesByNotUserId(id);
    	List<Roles> roleListRight = roleService.getRolesByUserId(id);     
    	Users user = this.userService.findId(id);
    	UsersBean bean = ConvertEntityToUserBean(user);
        model.addAttribute("user", bean);
        model.addAttribute("left", roleListLeft);
        model.addAttribute("right", roleListRight);  
        return get_user_edit;
    }
    
    //@RequestParam(value="chkRoleRight", required=false), required=false : fix Required List parameter 'chkRoleRight' is not present
    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") @Valid UsersBean userBean, BindingResult bindingResult, Model model,
    		@RequestParam(value="chkRoleRight", required=false) List<Long> chkRoleRight,  RedirectAttributes redirectAttributes ){
    	
    	userBean.clearMessages();
    	if (bindingResult.hasErrors()) {
            //add message
    		userBean.addMessage(Message.ERROR,	bindingResult.getFieldError("entity").getDefaultMessage());
    		model.addAttribute("bean", userBean);
            return get_user_edit;
        }
    	try {
	    	Users user = userBean.getEntity();
	    	List<Roles> roles = new ArrayList<Roles>();
	    	if (chkRoleRight != null)
	    	{
	    		roles = roleService.findRoleByIds(chkRoleRight);
	    	}    	
	        if(user.getId() == null){
	            //new User, add it
	        	user.setRoles(new HashSet<Roles>(roles));
	        	user.setPassword(user.getPassword());
	            this.userService.addUser(user); //run here then insert in DB, not like seam         	            	            
	        }else{
	        	user.setRoles(new HashSet<Roles>(roles));
	        	this.userService.updateUser(user);    	        	
	        }  
	        List<Roles> roleListLeft = roleService.findRoleNotByIds(chkRoleRight);
	        userBean.addMessage(Message.SUCCESS, "Save success");
	        model.addAttribute("left", roleListLeft);
	        model.addAttribute("right", roles);  
	        model.addAttribute("bean", userBean);
			redirectAttributes.addFlashAttribute("bean", userBean);
    	}
    	catch (Exception ex) {
    		userBean.addMessage(Message.ERROR,	"edit user: " + ex.toString());
			model.addAttribute("bean", userBean);
			return get_user_edit;
    	}
        return get_user_edit;       
    }
     
    @RequestMapping(value="/users/remove/{id}", method = RequestMethod.GET)
    public String removeUser(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){         
    	UsersBean userBean =  new UsersBean(); 
    	userBean.clearMessages();
    	try {
    		this.userService.removeUser(id);
    		userBean.addMessage(Message.SUCCESS, "Delete Success" );
    	} catch (Exception ex)
    	{
    		userBean.addMessage(Message.ERROR,	"User in used: " + ex.toString());  		
    		model.addAttribute("bean", userBean);
    		redirectAttributes.addFlashAttribute("bean", userBean);
            return "redirect:/users";
    	}
    	model.addAttribute("bean", userBean);
		redirectAttributes.addFlashAttribute("bean", userBean);
        return "redirect:/users";
    }
    
    private UsersBean ConvertEntityToUserBean(Users user)
    {
    	UsersBean bean = new UsersBean();
    	bean.setEntity(user);
    	return bean;
    }      
}
