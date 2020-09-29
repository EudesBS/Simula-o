package controller;
import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class ThreadJogos extends Thread {

	private int idPrato;
	private Semaphore semaforo;
	private double cozimento = 0.0;
	private static int saiuPrato;
	private double porcentagem = 0.0;
	private double soma = 0.0;

	DecimalFormat f = new DecimalFormat("0.00");

	public ThreadJogos(int idPrato, Semaphore semaforo) {

		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}

	public void run () {

		preparaPrato();
		try {
			semaforo.acquire();
			pratoPronto();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			fimPrato();
		}



	}

	public void preparaPrato() {

		if (idPrato % 2 != 0) {
			cozimento = ((Math.random() * 801) + 500);
			porcentagem = 1000 / cozimento;
		} else {
			cozimento = ((Math.random() * 1201) + 600);
			porcentagem = 1000 / cozimento;
		}
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("#" + idPrato + "| Começou ");
	}


	public void pratoPronto() {

		while (soma <= 100.0) {
			soma = soma + porcentagem;
			System.out.println("#" + idPrato + "| está em " + f.format(soma) + "%");
		}

		try	{
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



	public void fimPrato() {
		saiuPrato++;
		System.out.println("#" + idPrato + "| terminou " + saiuPrato);
	}
}
