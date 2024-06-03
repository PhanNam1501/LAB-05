package hust.soict.cybersec.aims.screen;



import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.naming.LimitExceededException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import hust.soict.cybersec.aims.store.Store;
import hust.soict.cybersec.aims.cart.Cart;
import hust.soict.cybersec.aims.exception.PlayerException;
import hust.soict.cybersec.aims.media.Media;
import hust.soict.cybersec.aims.media.DigitalVideoDisc;
import hust.soict.cybersec.aims.media.Playable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
	private Cart cart;
	private CartScreen cartScreen;
	private Store store;
	private int check;
	@FXML
    private MenuItem addDVD;
	 @FXML
	 private MenuItem viewStore;
	
     @FXML
     private MenuBar menuBar;
	
	 @FXML
     private Label btnTotal;
	
	 @FXML
	 protected TableView<Media> tblMedia;

	 @FXML
	 private TableColumn<Media, String> colMediaTitle;

	 @FXML
	 private TableColumn<Media, String> colMediacategory;

	 @FXML
	 private TableColumn<Media, Float> colMediaCost;
	 
	 @FXML
	 private ToggleGroup filterCategory;

	 @FXML
	 private Button btnPlay;

	 @FXML
	 private Button btnRemove;
	 
	 public CartScreenController(Cart cart, Store store,CartScreen cartScreen) {
		 super();
		 this.cart = cart;
		 this.cartScreen = cartScreen;
		 this.store = store;
 
	 }
	 
	 @FXML
	 void btnRemovePressed(ActionEvent event) {
		 Media media = tblMedia.getSelectionModel().getSelectedItem();
		 cart.removeMedia(media);
	 } 
	 @FXML
	 void btnPlaceOrder(ActionEvent event) {
		 Media media = tblMedia.getSelectionModel().getSelectedItem();
		 float total = cart.totalcost();
		 btnTotal.setText(String.valueOf(total));
		 check = 1;
	 }


	 @FXML
	 void btnPlay(ActionEvent event) throws PlayerException {
		 Media media = tblMedia.getSelectionModel().getSelectedItem();
		 playMedia(media);
		 ((Playable) cart).play();
	 }
	 private void playMedia(Media media) {
	        JDialog dialog = new JDialog();
	        dialog.setLayout(new FlowLayout());
	        dialog.add(new JLabel("Playing DVD: " + media.getTitle()));
	        
	        JButton stopButton = new JButton("Stop");
	        stopButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dialog.dispose();
					
				}
	        });
	        dialog.add(stopButton);
	        dialog.setSize(300, 200);
	        dialog.setVisible(true);
	    }
	 
	 
	@FXML
	 private void initialize() throws LimitExceededException {
		viewStore.setOnAction(event -> cartScreen.showStoreScreen());
		 colMediaTitle.setCellValueFactory(
				 new PropertyValueFactory<Media, String>("title"));
		 colMediacategory.setCellValueFactory(
				 new PropertyValueFactory<Media, String>("category"));
		 colMediaCost.setCellValueFactory(
				 new PropertyValueFactory<Media, Float>("cost"));
	     //cart.addMedia(new DigitalVideoDisc("messi", "football", 15.0f));
	     //cart.addMedia(new Media("neymar", "football", 16.0f));
	     //cart.addMedia(new Media("suarez", "football", 17.0f));
		 tblMedia.setItems(this.cart.getItemsOrdered());

		 
		 btnPlay.setVisible(false);
		 btnRemove.setVisible(false);
		 
		 tblMedia.getSelectionModel().selectedItemProperty().addListener(
				 (ChangeListener<? super Media>) new ChangeListener<Media>() {

					@Override
					public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
						if(newValue != null) {
							 updateButtonBar(newValue);
						 }
						
					}

					public void updateButtonBar(Media media) {
						btnRemove.setVisible(true);
						if(media instanceof Playable) {
							btnPlay.setVisible(true);
						}
						else {
							btnPlay.setVisible(false);
						}
					}

				
				 });
	 }
	 
}
