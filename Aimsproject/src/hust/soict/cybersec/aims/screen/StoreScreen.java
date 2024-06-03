package hust.soict.cybersec.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.LimitExceededException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.cybersec.aims.cart.Cart;
import hust.soict.cybersec.aims.exception.PlayerException;
import hust.soict.cybersec.aims.media.DigitalVideoDisc;
import hust.soict.cybersec.aims.media.Media;
import hust.soict.cybersec.aims.media.Playable;
import hust.soict.cybersec.aims.store.Store;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;
    private CartScreen cartscreen;

    public StoreScreen(Store store,Cart cart,CartScreen cartScreen2) {
        this.store = store;
        this.cart = cart;
        this.cartscreen = cartScreen2;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");

        // Add action listeners for the menu items
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookScreen();
            }
        });

        addCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCDScreen();
            }
        });

        addDVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDVDScreen();
            }
        });

        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        menu.add(new JMenuItem("View cart"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart1 = new JButton("View cart");
        cart1.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				System.out.println(1);
				CartScreen cartScreen = new CartScreen(cart, store);
				cartScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cartScreen.setSize(1024, 768);
				cartScreen.setVisible(true);
                dispose();
            }
        });
        cart1.setPreferredSize(new Dimension(100, 50));
        cart1.setMaximumSize(new Dimension(100, 50));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart1);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media);
            center.add(cell);
        }
        return center;
    }

    public class MediaStore extends JPanel {
        private Media media;

        public MediaStore(Media media) {
            this.media = media;
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel title = new JLabel(media.getTitle());
            title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
            title.setAlignmentX(CENTER_ALIGNMENT);

            JLabel cost = new JLabel("" + media.getCost() + " $");
            cost.setAlignmentX(CENTER_ALIGNMENT);

            JPanel container = new JPanel();
            container.setLayout(new FlowLayout(FlowLayout.CENTER));
           
            JButton addToCartButton = new JButton("Add to cart");
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
						cart.addMedia(media);
					} catch (LimitExceededException e1) {
						e1.printStackTrace();
					}
                    System.out.println("Added to cart: " + media.getTitle());
                }
            });
            container.add(addToCartButton);


            
            //button play
            if (media instanceof Playable) {
                JButton playButton = new JButton("Play");
                playButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        playMedia(media);
                        try {
							((Playable) media).play();
						} catch (PlayerException e1) {
							// TODO Auto-generated catch block
							System.err.println(e1.getMessage());
							e1.printStackTrace();
							javax.swing.JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
				        }
						
                    }
                });
                container.add(playButton);
            }

            this.add(Box.createVerticalGlue());
            this.add(title);
            this.add(cost);
            this.add(Box.createVerticalGlue());
            this.add(container);

            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        private void playMedia(Media media) {
            JDialog dialog = new JDialog();
            dialog.setLayout(new FlowLayout());
            dialog.add(new JLabel("Playing DVD: " + media.getTitle()));

            JButton stopButton = new JButton("Stop");
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            dialog.add(stopButton);
            dialog.setSize(300, 200);
            dialog.setVisible(true);
        }
    }

    // AddBookScreen class
    public class AddBookScreen extends JFrame {
        public AddBookScreen() {
            setTitle("Add Book");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Add UI components for Add Book screen here
            JPanel panel = new JPanel();
            panel.add(new JLabel("Add Book Screen"));
            getContentPane().add(panel);

            setVisible(true);
        }
    }

    // AddCDScreen class
    public class AddCDScreen extends JFrame {
        public AddCDScreen() {
            setTitle("Add CD");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Add UI components for Add CD screen here
            JPanel panel = new JPanel();
            panel.add(new JLabel("Add CD Screen"));
            getContentPane().add(panel);

            setVisible(true);
        }
    }

    // AddDVDScreen class
    public class AddDVDScreen extends JFrame {
        public AddDVDScreen() {
            setTitle("Add DVD");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Add UI components for Add DVD screen here
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            
            JLabel titleLabel = new JLabel("Title:");
            JTextField titleField = new JTextField();
            JLabel categoryLabel = new JLabel("Category:");
            JTextField categoryField = new JTextField();
            JLabel directorLabel = new JLabel("Director:");
            JTextField directorField = new JTextField();
            JLabel lengthLabel = new JLabel("Length:");
            JTextField lengthField = new JTextField();
            JLabel costLabel = new JLabel("Cost:");
            JTextField costField = new JTextField();
            JButton addButton = new JButton("Add DVD");

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String title = titleField.getText();
                    String category = categoryField.getText();
                    String director = directorField.getText();
                    int length = Integer.parseInt(lengthField.getText());
                    float cost = Float.parseFloat(costField.getText());

                    // Add the new DVD to the store
                    DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                    boolean added = store.addMedia(dvd);
                    if (added) {
                        JOptionPane.showMessageDialog(null, "DVD added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Update the store screen
                        //storeScreen.updateStoreScreen();
                        dispose(); // Close the AddDVD screen
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add DVD. DVD with the same title already exists in the store.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    updateStoreScreen();
                    dispose(); // Close the AddDVD screen
                }
            });

            panel.add(titleLabel);
            panel.add(titleField);
            panel.add(categoryLabel);
            panel.add(categoryField);
            panel.add(directorLabel);
            panel.add(directorField);
            panel.add(lengthLabel);
            panel.add(lengthField);
            panel.add(costLabel);
            panel.add(costField);
            panel.add(addButton);
            
            getContentPane().add(panel);
            
            setVisible(true);
        }
    }
    private void updateStoreScreen() {
        Frame[] frames = JFrame.getFrames();
        for (Frame frame : frames) {
            if (frame instanceof StoreScreen) {
                ((StoreScreen) frame).updateCenterPanel();
                break;
            }
        }
    }
    public void updateCenterPanel() {
        JPanel center = createCenter();
        getContentPane().removeAll();
        getContentPane().add(createNorth(), BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
