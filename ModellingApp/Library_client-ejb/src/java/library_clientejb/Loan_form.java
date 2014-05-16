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
        List<TUser> items = client.getFacade().getmUsers();
        for( TUser o : items)
            usersCBx.addItem(o.getName());
    }   
                TUser u = client.getFacade().addUser( addUserTF.getText().toString() );

   @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if( b == addUserBtn ) {
            TUser u = client.getFacade().addUser( addUserTF.getText().toString() );
            fillUsers();
        }
        else if( b == loanBookBtn ) {
            try {
                TLoanData loan = client.getFacade().loanBook(title(), period(),user());
            if( loan != null)
                System.out.println(loan.toString());
            else
                System.out.println("null");
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
