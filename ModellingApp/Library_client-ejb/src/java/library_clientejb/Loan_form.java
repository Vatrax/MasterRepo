/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_clientejb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import sub_business_tier.entities.TLoanData;
import sub_business_tier.entities.TUser;

/**
 *
 * @author Zofia
 */
public class Loan_form extends Show_book_titles_table implements ActionListener {

    //Client client;
    JComboBox usersCBx;
    JTextField addUserTF;
    JButton addUserBtn;
    JTextField periodTF;
    
    
    JTable books;
    JButton loanBookBtn;

    public Loan_form(Client client_) {
        super(client_);
        usersCBx = new JComboBox();
        add(usersCBx);        
        periodTF = new JTextField("30");
        periodTF.disable();
        add(periodTF);
        loanBookBtn = new JButton("Loan");
        loanBookBtn.addActionListener( this );
        add(loanBookBtn);
        
        //Adding new user
        JLabel laddUser = new JLabel("Add new user");
        add(laddUser);
        addUserTF = new JTextField();
        addUserTF.setSize(20, 120);
        add(addUserTF);
        fillUsers();
        addUserBtn = new JButton("Add user");
        addUserBtn.addActionListener( this );
        add(addUserBtn);
       }

 
    private void fillUsers() {
        usersCBx.removeAllItems();
        List<String> items = client.getFacade().getUsers();
        for( String o : items)
            usersCBx.addItem(o);
    }   


   @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if( b == addUserBtn ) {
            client.getFacade().addUser( addUserTF.getText().toString() );
            fillUsers();
        }
        else if( b == loanBookBtn ) {
            try {
                Object loan = client.getFacade().loanBook(title(), period(),user());
                String[][] books = client.getFacade().getUserBooks(user());
                for(int i = 0; i < books.length; i++) {
                    for(int j = 0; j < books[i].length; j++)
                        System.out.print(books[i][j]+"  ");
                    System.out.println();
                }
            if( loan != null)
                System.out.println(loan.toString());
            else
                JOptionPane.showMessageDialog(this, "Cannot borrow this book");
             }
             catch(Exception ex) {
                 ex.printStackTrace();
             }
        }
    }
    
    public String user() {
        return usersCBx.getSelectedItem().toString();
    }
    
    public String period() {
        return periodTF.getText().toString();
    }
}
