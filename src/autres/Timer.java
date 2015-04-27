package autres;

import games.Jeux;

public class Timer extends Thread {
	Jeux modele;
	boolean running;
	
	public Timer(Jeux j){
		modele=j;
		running=true;
		start();
	}
	
	public void start(){
		super.start();
		running=true;
	}
	
	public void pause() {
		running=false;
	}
	
	public void run() {
		running=true;
		try{
			while(running){
				sleep(1000);
				if(running) modele.execute();
			}
		}
		catch (InterruptedException e) {}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
