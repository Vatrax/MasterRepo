/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tlibrary2_client_ejb;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Show_book_titles_form extends JPanel {

    protected JTable table;
    protected int row;
    protected Show_books_table_model model;

    public Show_book_titles_form() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new Show_books_table_model();
        table_content();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());

        add(new JScrollPane(table));
    }

    
    protected String[] title() {
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
    
    protected void table_content() {
        Object[][] titles = Client.getFacade().gettitle_books();
        model.setData(titles);
    }
    

    protected class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            row = table.getSelectionModel().getLeadSelectionIndex();
        }
    }

   
}
