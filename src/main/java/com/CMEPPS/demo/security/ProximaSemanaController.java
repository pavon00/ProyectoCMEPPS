package com.CMEPPS.demo.security;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.CMEPPS.demo.model.Horario;
import com.CMEPPS.demo.model.Reparto;
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

		System.out.println(getProximaSemana(model));
		ProximaSemana ps = getProximaSemana(model);
		model.put("todos", todoService.getTodosPendientesByUser(name));
		model.put("horarios", ps.getHorarios());
		model.put("repartos", ps.getProximaSemanaReparto());
		model.put("horasString", ps.getProximaSemanaHorasString());
		model.put("fechasString", ps.getProximaSemanaFechasString());
		model.put("estatusString", ps.getProximaSemanaEstatusString());
		// model.put("todos", service.retrieveTodos(name));
		return "proximaSemana";
	}

	@RequestMapping(value = "/list-horarios", method = RequestMethod.GET)
	public String showHorariosProximaSemana(ModelMap model) {
		model.put("horarios", getProximaSemana(model).getHorarios());
		// model.put("todos", service.retrieveTodos(name));
		return "list-horarios";
	}

	@RequestMapping(value = "/update-horario", method = RequestMethod.GET)
	public String showUpdateHorarioPage(@RequestParam long id, ModelMap model) {
		LocalDate fecha = getProximaSemana(model).getHorarios().get((int) id).getFecha();
		System.out.println(fecha);
		model.put("horario", getProximaSemana(model).getHorarios().get((int) id));
		return "horario";
	}

	@RequestMapping(value = "/update-todo-introducido-proximaSemana", method = RequestMethod.GET)
	public String showUpdateIntroducidoProximaSemanaTodoPage(@RequestParam long id, ModelMap model) {
		Todo todo = todoService.getTodoById(id).get();
		todo.cambiarIntroduccion();
		ProximaSemana ps = getProximaSemana(model);
		for (Reparto r : ps.getReparto()) {
			r.setUserName(todo.getUserName());
			repartoService.deleteReparto(r.getId());
		}
		if (todo.isIntroducidaEnProximaSemana()) {
			ps.addTodo(todo);

		} else {
			ps.deleteTodo(todo);
		}
		for (Reparto r : ps.getReparto()) {
			r.setUserName(todo.getUserName());
			repartoService.updateReparto(r);
		}
		todoService.updateTodo(todo);
		return "redirect:/proximaSemana";
	}

	@RequestMapping(value = "/update-horario-disponible", method = RequestMethod.GET)
	public String showUpdateHorarioDisponiblePage(@RequestParam String date, ModelMap model) {
		Horario h = getProximaSemana(model).getHorario(date);
		h.cambiarDisponibilidad();
		System.out.println(h);
		h.setUserName(getLoggedInUserName(model));
		horarioService.updateHorario(h);
		model.put("horario", h);
		return "horario";
	}

	@RequestMapping(value = "/update-horario", method = RequestMethod.POST)
	public String updateHorario(@RequestParam String date, ModelMap model, @Validated Horario horario,
			BindingResult result) {

		if (result.hasErrors()) {
			return "horario";
		}

		Horario h = getProximaSemana(model).getHorario(date);
		h.setHoras(horario.getHoras());
		System.out.println(h);
		h.setUserName(getLoggedInUserName(model));
		horarioService.saveHorario(h);
		return "redirect:/list-horarios";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	private synchronized ProximaSemana getProximaSemana(ModelMap model) {
		String name = getLoggedInUserName(model);
		ProximaSemana ps = new ProximaSemana(name);
		ps.setTodosUsuario(todoService.getTodosByUser(name));
		ps.setHorarios(horarioService.getHorariosByUser(name));
		ps.setReparto(repartoService.getRepartosByUser(name));
		return ps;
	}
}