package hust.soict.cybersec.test.store;

import hust.soict.cybersec.aims.media.DigitalVideoDisc;
import hust.soict.cybersec.aims.store.Store;

public class StoreTest {
	public static void main(String[] args) {
		Store store = new Store();
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 85, 19.95f);
		store.addMedia(dvd1);
		
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
		store.addMedia(dvd2);
		
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		store.addMedia(dvd3);
		
		
		System.out.println(store.getItemsInStore().get(0).toString());
		System.out.println(store.getItemsInStore().get(1).toString());
		System.out.println(store.getItemsInStore().get(2).toString());
		
		store.removeMedia(dvd2);
		
		System.out.println(store.getItemsInStore().get(0).toString());
		System.out.println(store.getItemsInStore().get(1).toString());
	}
}
