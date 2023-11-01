package proximaSemana;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
	public static void main(String[] args) {
        ProximaSemana f = new ProximaSemana("user");
        Todo t1 = new Todo("user", "tarea1", new Date(), 9, 1, true);
        Todo t2 = new Todo("user", "tarea2", new Date(), 9, 1, true);
        Todo t23 = new Todo("use1r", "tarea2", new Date(), 9, 1, true);
        Todo t3 = new Todo("user", "tarea3", new Date(), 9, 1, true);
        Todo t4 = new Todo("user", "tarea4", new Date(), 9, 1, true);
        Todo t5 = new Todo("user", "tarea5", new Date(), 9, 1, true);
        Todo t6 = new Todo("user", "tarea6", new Date(), 9, 1, true);
        Todo t7 = new Todo("user", "tarea7", new Date(), 9, 1, true);
        ArrayList<Todo> listaTodos = new ArrayList<Todo>();
        listaTodos.add(t1);
        listaTodos.add(t2);
        listaTodos.add(t23);
        listaTodos.add(t3);
        listaTodos.add(t4);
        listaTodos.add(t5);
        listaTodos.add(t6);
        listaTodos.add(t7);
        f.setTodosUsuario(listaTodos);
        f.setDisponible(2, false);
        f.setHorasDisponible(3, 3);
        f.addTodo(t1);
        f.addTodo(t2);
        f.addTodo(t23);
        f.addTodo(t3);
        f.addTodo(t4);
        f.addTodo(t5);
        f.addTodo(t6);
        f.addTodo(t7);
        f.deleteTodo(t3);
        f.addTodo(t5);
        List<Reparto> listaReparto = f.getReparto();
        List<Horario> listaHorario = f.getHorarios();
        f = new ProximaSemana("user");
        f.setTodosUsuario(listaTodos);
        f.setHorarios(listaHorario);
        f.setReparto(listaReparto);
        f.deleteTodo(t5);
        f.addTodo(t6);
        System.out.println(f);
    }
}
