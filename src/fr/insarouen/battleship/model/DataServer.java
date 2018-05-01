package fr.insarouen.battleship.model;

import java.net.InetAddress;
import java.util.ArrayList;

import fr.insarouen.battleship.observer.Observable;
import fr.insarouen.battleship.observer.Observer;

public class DataServer implements Observable{
	private Players players;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public DataServer(){
		this.players = new Players();
	}
	
	public String getPlayersList(){
		return players.toString();
	}
	
	public void newPlayer(InetAddress ip){
		this.players.add(ip);
		notifyObserver(getPlayersList());
	}
	
	public void newPlayer(String name, InetAddress ip){
		this.players.add(name, ip);
		notifyObserver(getPlayersList());
		
	}
	
	public void removePlayer(InetAddress ip){
		this.players.remove(ip);
		notifyObserver(getPlayersList());
	}

	@Override
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		
	}

	@Override
	public void notifyObserver(String str) {
		for (Observer obs : listObserver){
			obs.update(str);
		}
		
	}
}
