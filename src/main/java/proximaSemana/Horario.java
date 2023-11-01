package proximaSemana;

import java.time.LocalDate;
import java.util.Random;

public class Horario {

    private long id;
	private String userName;
	private LocalDate fecha;
	private boolean disponible;
	private int horas;

	public Horario(String userName, LocalDate fecha, boolean disponible, int horas) {
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.fecha = fecha;
		this.horas = horas;
		this.disponible = disponible;
		this.id = generarNumeroAleatorio();
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
