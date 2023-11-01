package proximaSemana;

import java.util.Date;
import java.util.Random;

public class Todo {

    private long id;
    private String userName;
    private String description;
    private Date targetDate;
    private int horasEstimadas;
    private int prioridad;

    public Todo() {
        super();
    }

    public Todo(String user, String desc, Date targetDate, int horasEstimadas, int prioridad, boolean isDone) {
        super();
        this.userName = user;
        this.description = desc;
        this.targetDate = targetDate;
        this.horasEstimadas = horasEstimadas;
        this.prioridad = prioridad;
		this.id = generarNumeroAleatorio();
    }
	
	public static long generarNumeroAleatorio() {
        Random rand = new Random();
        long numeroAleatorio = rand.nextLong() % 10001;
        return Math.abs(numeroAleatorio); // Para asegurarse de que sea positivo
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

	public int getHorasEstimadas() {
		return horasEstimadas;
	}

	public void setHorasEstimadas(int horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	//devuelve true si esta clase tiene el atributo targetDate mayor a la fecha actual 
	public boolean esPendiente() {
        Date fechaActual = new Date();
        return this.targetDate.after(fechaActual);
    }
}    