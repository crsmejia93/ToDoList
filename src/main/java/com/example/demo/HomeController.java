package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listToDos(Model model){
        model.addAttribute("tasks", toDoRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String toDoForm(Model model){
        model.addAttribute("task", new Task());
        return "todoform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Task task, BindingResult result){
        if(result.hasErrors()){
            return "todoform";
        }
        toDoRepository.save(task);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showToDo(@PathVariable("id") long id, Model model){
        model.addAttribute("task", toDoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateToDo(@PathVariable("id") long id, Model model){
        model.addAttribute("task", toDoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/delete/{id}")
    public String delTask(@PathVariable("id") long id){
        toDoRepository.deleteById(id);
        return "redirect:/";
    }
}
