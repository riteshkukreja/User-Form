/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.controllers;

import com.mettl.learning.userform.models.User;
import com.mettl.learning.userform.services.FileImporterInterface;
import com.mettl.learning.userform.services.UserService;
import java.io.File;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/")
public class UserController {
    
    @Autowired
    public UserService userService;
    
    @Autowired
    public FileImporterInterface fileImporterInterface;
    
    @GetMapping("")
    public String getForm(Model model) throws Exception {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "home";
    }
    
    @GetMapping("addNew")
    public String addNewView(Model model) {
        return "addNew";
    }
    
    @GetMapping("update/{userId}")
    public String updateView(@PathVariable int userId, Model model) throws Exception {
        User user = userService.get(userId);
        model.addAttribute("user", user);
        return "update";
    }
    
    @GetMapping(path = "user/{userId}/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User getUser(@PathVariable int userId) throws Exception {
        User user = userService.get(userId);
        return user;
    }
    
    @GetMapping(path = "user/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<User> getAllUser() throws Exception {
        List<User> user = userService.getAll();
        return user;
    }
    
    @GetMapping("user/{userId}")
    public String getUserHTML(@PathVariable int userId, Model model) throws Exception {
        User user = userService.get(userId);
        model.addAttribute("user", user);
        return "displayUser";
    }
    
    @PostMapping("user")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) throws Exception {
        if(result.hasErrors()) {
            throw new Exception(result.getAllErrors().toString());
        }
        int id = userService.create(user);
        return "redirect:/user/" + id;
    }
    
    @PostMapping("user/{userId}")
    public String updateUser(@PathVariable int userId, @Valid @ModelAttribute("user") User user, BindingResult result, Model model) throws Exception {
        if(result.hasErrors()) {
            throw new Exception(result.getAllErrors().toString());
        }
        userService.update(userId, user);
        return "redirect:/user/{userId}";
    }
    
    @PostMapping("delete/{userId}")
    public String deleteUser(@PathVariable int userId, Model model) throws Exception {
        userService.delete(userId);
        return "redirect:/";
    }
    
    @GetMapping("error")
    public String showError(Model model) {
        return "error";
    }
    
    @ExceptionHandler
    public ModelAndView handleIOException(Exception ex) {
        // prepare responseEntity
        ModelAndView mav = new ModelAndView("error");        
        mav.addObject("error", ex.getMessage());
        
        return mav;
    }
    
    @PostMapping("upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        File convFile = new File( "C:\\Users\\admin\\Documents\\uploads\\" + file.getOriginalFilename());
        file.transferTo(convFile);
        
        List<User> users = fileImporterInterface.readContent(convFile);
        userService.upload(users);
        
        return "redirect:/";
    }

    
}
