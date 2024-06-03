package hust.soict.cybersec.aims.store;

import hust.soict.cybersec.aims.media.*;
import java.util.ArrayList;

public class Store {
	private ArrayList<Media> itemsInStore = new ArrayList<Media>();
	int numMedia;
	
	
	public ArrayList<Media> getItemsInStore() {
		return itemsInStore;
	}
	public int getNumMedia() {
		return numMedia;
	}
	public boolean addMedia(Media media) {
		if (itemsInStore.contains(media)) {
			System.out.println("The media " + media.getTitle() +  " is already in store");
			return false;
		} else {
			itemsInStore.add(media);
			numMedia++;
			System.out.println("Successfully added " + media.getTitle() + " to store");
			return true;
		}
	}
		
	public void removeMedia(Media media) {
		if (itemsInStore.contains(media)) {
			itemsInStore.remove(media);
			System.out.println("Successfully removed " + media.getTitle() + " from store");
		} else {
			System.out.println("The media " + media.getTitle() + " is not in store");
		}
	}
	public void print() {
		System.out.println("*****************STORE*****************");
		System.out.println("Items In Store:");
		for (Media media: itemsInStore) {
			System.out.println(media.toString());
		}
		System.out.println("***************************************");
	}
}
