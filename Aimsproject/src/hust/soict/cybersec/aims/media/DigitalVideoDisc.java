package hust.soict.cybersec.aims.media;

import hust.soict.cybersec.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable{

    public DigitalVideoDisc(){}

    public DigitalVideoDisc(String title){
        super(title);
    }

    public DigitalVideoDisc(String title, float cost){
        super(title, cost);
    }

    public DigitalVideoDisc(String title, String category, float cost){
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost){
        super(title, category, director, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost){
        super(title, category, director, length, cost);
    }

    public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("Playing DVD: " + super.getTitle());
			System.out.println("DVD length: " + super.getLength());
		} else {
			throw new PlayerException("ERROR: DVD length is non-positive!");
			
		}
	}
}