/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_clientejb;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import sub_business_tier.entities.TLoanData;
import sub_business_tier.entities.TUser;

/**
 *
 * @author Zofia
 */
public class Loan_form extends Show_book_titles_form implements ActionListener {

    //Client client;
    JComboBox usersCBx;
    JTextField addUserTF;
    JButton addUserBtn;
    JTextField periodTF;
    JButton loanBookBtn;
    
    JTable borrowedBooks;
    Show_borrowed_books_table_model borrowedModel;
    JButton giveBackBook;

    public Loan_form(Client client_) {
        super(client_);
        usersCBx = new JComboBox();
        usersCBx.addActionListener(this);
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
        
        borrowedModel = new Show_borrowed_books_table_model();
        borrowedBooks = new JTable(borrowedModel);
        
        borrowedBooks.setPreferredScrollableViewportSize(new Dimension(500, 100));
        borrowedBooks.setFillsViewportHeight(true);
        borrowedBooks.getSelectionModel().addListSelectionListener(new RowListener());
        add(new JScrollPane(borrowedBooks));

        
        giveBackBook = new JButton("Give back");
        giveBackBook.addActionListener(this);
        add(giveBackBook);
        setBorrowedBooks();
    }

 
    private void fillUsers() {
        usersCBx.removeAllItems();
        List<String> items = client.getFacade().getUsers();
        for( String o : items)
            usersCBx.addItem(o);
    }   


   @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if( source == null)
            return;
        if( source == addUserBtn ) {
            client.getFacade().addUser( addUserTF.getText().toString() );
            fillUsers();
        }
        else if( source == loanBookBtn ) {
            try {
                Object loan = client.getFacade().loanBook(title(), period(),user());
                setBorrowedBooks();
            if( loan != null)
                System.out.println(loan.toString());
            else
                JOptionPane.showMessageDialog(this, "Cannot borrow this book");
             }
             catch(Exception ex) {
                 ex.printStackTrace();
             }
        }
        else if ( source == giveBackBook ) {
            if (!borrowedBooks.getSelectionModel().isSelectionEmpty()) {
                int selectedRow = borrowedBooks.getSelectionModel().getLeadSelectionIndex();
                String what, actor;
                actor = (String) borrowedModel.getValueAt(selectedRow, 2);
                if (actor.isEmpty())//what type of title of book
                    what = "0";
                else
                    what = "2";
       
                String titleBookInfos[] = {what, (String) borrowedModel.getValueAt(selectedRow, 0), actor};
                String bookInfos[] = {"0", (String) borrowedModel.getValueAt(selectedRow, 3)};

                System.out.println(client.getFacade().giveBackBook(titleBookInfos, bookInfos, user() ));
                setBorrowedBooks();
            }
        }
        else if( source == usersCBx ) {
            setBorrowedBooks();
        }
    }
    
    public String user() {
        Object selectedItem = usersCBx.getSelectedItem();
        
        if( selectedItem == null)
            return null;
        return selectedItem.toString();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    public String period() {
        return periodTF.getText().toString();
    }
    
    public void setBorrowedBooks() {
        String u = user();
        if( u == null ) {
            System.out.println("user == null!");
            return;
        }
        Object[][] o = client.getFacade().getUserBooks( u );
        if( o == null || borrowedModel == null )
            return;
        borrowedModel.setData( o );      
        borrowedModel.fireTableDataChanged();
    }
}
