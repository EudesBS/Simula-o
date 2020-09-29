package view;
import java.util.concurrent.Semaphore;
import  controller.ThreadJogos;

public class Principal {
	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		
		for (int idPrato = 1; idPrato <= 5; idPrato++) {
			
			Thread prato = new ThreadJogos(idPrato, semaforo);
			prato.start();
		}
	}
}
