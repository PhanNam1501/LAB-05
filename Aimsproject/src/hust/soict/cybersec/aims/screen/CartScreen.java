package hust.soict.cybersec.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hust.soict.cybersec.aims.cart.Cart;
import hust.soict.cybersec.aims.media.Book;
import hust.soict.cybersec.aims.media.DigitalVideoDisc;
import hust.soict.cybersec.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame{
	private Cart cart;
	private Store store;
	private CartScreenController cartscreen; 
	private JFXPanel fxPanel;
	
	public CartScreen(Cart cart, Store store) {
		super();
		this.cart = cart;
		this.store = store;
		
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		
		this.setTitle("Cart");
		this.setVisible(false);
		Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/cybersec/aims/screen/cart.fxml"));
                loader.setController(new CartScreenController(cart, store,this));
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
	}
	public void showStoreScreen() {
        // Dispose current JFrame (Cart Screen)
        dispose();

        SwingUtilities.invokeLater(() -> {
    		Cart cart = new Cart(); 
        	Book book1 = new Book("LionelMessi's Book",1,"football",(float) 150.30);
        	Book book2 = new Book("CR7's Book",1,"football",(float) 60.30);
        	Book book3 = new Book("Neymar's Book",1,"football",(float) 70.30);
        	Book book4 = new Book("Benzema's Book",1,"football",(float) 80.30);

        	DigitalVideoDisc DVD1 = new DigitalVideoDisc("CR7's DVDs","football","MU",1000,(float) 150.00);
        	DigitalVideoDisc DVD2 = new DigitalVideoDisc("Neymar's DVDs","football","PSG",1000,(float) 200.00);
        	DigitalVideoDisc DVD3 = new DigitalVideoDisc("Benzema's DVDs","football","Real",1000,(float) 250.00);
        	DigitalVideoDisc DVD4 = new DigitalVideoDisc("Kane's DVDs","football","TOT",1000,(float) 120.00);
        	DigitalVideoDisc DVD5 = new DigitalVideoDisc("Bell's DVDs","football","Real",1000,(float) 130.00);
        	
        	store.addMedia(book1);
        	store.addMedia(DVD1);
        	store.addMedia(DVD2);
        	store.addMedia(DVD3);
        	store.addMedia(DVD4);
          	store.addMedia(DVD5);
        	store.addMedia(book2);
        	store.addMedia(book3);
        	store.addMedia(book4);

        	
            StoreScreen storeScreen = new StoreScreen(store,cart,this);
            storeScreen.setVisible(true);
            
        });
    }
	
	//public static void main(String[] args) {
		//CartScreen cartScreen = new CartScreen(new Cart(), new Store());
		//cartScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//cartScreen.setSize(1024, 768);
		//cartScreen.setVisible(true);

	//}

}
