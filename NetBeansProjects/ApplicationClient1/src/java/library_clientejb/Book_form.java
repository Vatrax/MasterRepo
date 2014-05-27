/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_clientejb;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Book_form extends JPanel implements ActionListener {

    private JTable table;
    private JTextField number;
    private JTextField period;
    private JButton add_book;
    int row;
    Client client;
    Show_books_table_model model;
    JComboBox books;

    public Book_form(Client client) {
        super();
        this.client = client;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new Show_books_table_model();
        table_content();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());

        add(new JScrollPane(table));
        JLabel lnumber = new JLabel("Number of a book");
        add(lnumber);
        number = new JTextField();
        add(number);
        JLabel lperiod = new JLabel("Period of a book");
        add(lperiod);
        period = new JTextField();
        add(period);
        add_book = new JButton("Add book");
        add(add_book);
        add_book.addActionListener(this);
        JLabel lbooks = new JLabel("Books");
        add(lbooks);
        books = new JComboBox();
        add(books);
    }

    private void list_content(ArrayList<String> col,
            JComboBox list) {
        String s;
        list.removeAllItems();
        Iterator<String> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }


    public void actionPerformed(ActionEvent event) {
        if (!table.getSelectionModel().isSelectionEmpty()) {
            String what_book_type;
            if (number.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "required value");
                return;
            }
            if (period.getText().equals("")) {
                what_book_type = "0";
            } else {
                what_book_type = "1";
            }
            String data2[] = {what_book_type, (String) number.getText(),
                (String) period.getText()};
            ArrayList<String> help3 = client.getFacade().add_book(title(), data2).getTBooks();
            if (help3 != null) {
                list_content(help3, books);
            }
        }
    }

    
    private String[] title() {
        String what, actor;
        actor = (String) model.getValueAt(row, 4);
        if (actor.isEmpty())//what type of title of book
        {
            what = "0";
        } else {
            what = "2";
        }
        String data[] = {what, (String) model.getValueAt(row, 1), actor};
        return data;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        table_content();
    }
    
    private void table_content() {
        Object[][] titles = client.getFacade().gettitle_books();
        model.setData(titles);
    }
    
    private void print_books() {
        ArrayList<String> help3 = client.getFacade().Search_title_book(title());
        if (help3 != null) {
            list_content(help3, books);
        }
    }

    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            row = table.getSelectionModel().getLeadSelectionIndex();
            print_books();
        }
    }

   
}
