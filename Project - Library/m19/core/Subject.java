package m19.core;



public abstract class Subject {

    
	public abstract void registerObserverEntrega(Observer observer);
	
	public abstract void registerObserverRequisicao(Observer observer);

	public abstract void notifyObserversEntrega();

	public abstract void notifyObserversRequisicao();
	
}