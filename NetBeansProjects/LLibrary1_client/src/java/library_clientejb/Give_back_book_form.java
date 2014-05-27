/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_clientejb;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * @author Zofia
 */
public class Give_back_book_form extends JPanel{
    JComboBox usersCBx;
    Client client;
    public Give_back_book_form(Client client_) {
        this.client = client_;
        usersCBx = new JComboBox();
        usersCBx.setSize(200, 30);
        add(usersCBx);
    }
    
    
    private void fillUsers() {
        usersCBx.removeAllItems();
        List<String> items = client.getFacade().getUsers();
        for( String o : items)
            usersCBx.addItem(o);
    }   
}