/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tlibrary2_client_ejb;

import business_tier.FacadeRemote;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.ejb.EJB;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Zofia
 */
public class Client implements ActionListener {
    @EJB
    private static FacadeRemote facade;

    public static FacadeRemote getFacade() {
        return facade;
    }

    public static void setFacade(FacadeRemote facade) {
        Client.facade = facade;
    }
    

    JPanel cards; //a panel that uses CardLayout
    final static String TITLE = "Title form";
    final static String BOOK = "Book form";
    final static String LOAN = "Loan book";
    final static String GIVE_BACK = "NOTHING1";
    //TFacade facade = new TFacade();

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        menuItem = new JMenuItem(TITLE, KeyEvent.VK_T);
        menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem(BOOK);
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem(LOAN);
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem(GIVE_BACK);
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu.addSeparator();

        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem(GIVE_BACK);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem(GIVE_BACK);
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Another Menu");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        return menuBar;
    }

  

 /*   public FacadeRemote getFacade() {
        return facade;
    }

    public void setFacade(FacadeRemote facade1) {
        Client.facade = facade1;
    }*/

    public Container createContentPane() {
        //Create the content-pane-to-be.

        Give_back_book_form card0 = new Give_back_book_form(this);
        Title_form card1 = new Title_form(this);
        Book_form card2 = new Book_form(this);
        Loan_form card3 = new Loan_form(this);

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, TITLE);
        cards.add(card2, BOOK);
        cards.add(card3, LOAN);
        cards.add(card0, GIVE_BACK);

        JPanel p1 = new JPanel();

        p1.add(cards, BorderLayout.CENTER);
        return p1;
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        CardLayout cl = (CardLayout) (cards.getLayout());
        if (source.getText().equals(TITLE)) {
            cl.show(cards, TITLE);
        } else if (source.getText().equals(BOOK)) {
            cl.show(cards, BOOK);
        } else if (source.getText().equals(GIVE_BACK)) {
            cl.show(cards, GIVE_BACK);
        } else if (source.getText().equals(LOAN)) {
            cl.show(cards, LOAN);
        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 460);
        //Create and set up the content pane.
        Client demo = new Client();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        java.awt.EventQueue.invokeLater(new Runnable() {
            //javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
