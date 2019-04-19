package com.codegym.cms.controller;

import com.codegym.cms.model.Employee;
import com.codegym.cms.model.Group;
import com.codegym.cms.service.EmployeeService;
import com.codegym.cms.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("view-employee/{id}")
    public ModelAndView viewGroup(@PathVariable("id") Long id){
        Group group = groupService.findById(id);
        if(group==null){
            return new ModelAndView("/error.404");
        }
        Iterable<Employee> employees = employeeService.findAllByGroup(group);

        ModelAndView modelAndView = new ModelAndView("/group/view");
        modelAndView.addObject("group",group);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/groups")
    public ModelAndView listGroupForm(){
        Iterable<Group> groups = groupService.findAll();
        ModelAndView modelAndView = new ModelAndView("/group/list");
        modelAndView.addObject("groups",groups);
        return modelAndView;
    }

    @GetMapping("/create-group")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/group/create");
        modelAndView.addObject("group",new Group());
        return modelAndView;
    }

    @PostMapping("/create-group")
    public ModelAndView saveGroup(@ModelAttribute("group") Group group){
        groupService.save(group);

        ModelAndView modelAndView = new ModelAndView("group/create");
        modelAndView.addObject("group",new Group());
        modelAndView.addObject("message","New Group Created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-group/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Group group = groupService.findById(id);
        if(group!=null){
            ModelAndView modelAndView = new ModelAndView("/group/edit");
            modelAndView.addObject("group",group);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-group")
    public ModelAndView updateGroup(@ModelAttribute("group") Group group){
        groupService.save(group);
        ModelAndView modelAndView = new ModelAndView("/group/eidt");
        modelAndView.addObject("group",group);
        modelAndView.addObject("message","Updated group successfully");
        return modelAndView;
    }

    @GetMapping("/delete-group/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Group group = groupService.findById(id);
        if(group!=null){
            ModelAndView modelAndView = new ModelAndView("/group/delete");
            modelAndView.addObject("group",group);
            return  modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-group")
    public String deleteGroup(@ModelAttribute("group") Group group){
        groupService.remove(group.getId());
        return "redirect:groups";
    }
}
