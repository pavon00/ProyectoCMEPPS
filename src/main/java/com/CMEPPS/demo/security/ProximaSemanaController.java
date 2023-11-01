package com.CMEPPS.demo.security;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.CMEPPS.demo.model.Todo;
import com.CMEPPS.demo.service.IHorarioService;
import com.CMEPPS.demo.service.IRepartoService;
import com.CMEPPS.demo.service.ITodoService;

@Controller
public class ProximaSemanaController {

    @Autowired
    private ITodoService todoService;
    @Autowired
    private IRepartoService repartoService;
    @Autowired
    private IHorarioService horarioService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    
    @RequestMapping(value = "/proximaSemana", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        String name = getLoggedInUserName(model);
        
        System.out.println("Empezar a programar hijo");
        
        
        ProximaSemana ps = new ProximaSemana(name);
        ps.setTodosUsuario(todoService.getTodosByUser(name));
        ps.setHorarios(horarioService.getHorariosByUser(name));
        ps.setReparto(repartoService.getRepartosByUser(name));
        
        System.out.println(ps);
        
        model.put("todos", todoService.getTodosByUser(name));
        // model.put("todos", service.retrieveTodos(name));
        return "proximaSemana";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}