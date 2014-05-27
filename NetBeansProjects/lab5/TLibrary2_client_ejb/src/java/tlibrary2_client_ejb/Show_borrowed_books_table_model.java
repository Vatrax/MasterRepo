package tlibrary2_client_ejb;


import javax.swing.table.AbstractTableModel;

 class Show_borrowed_books_table_model extends AbstractTableModel {

        private String[] columnNames = {"ISBN",
            "Title",
            "Actor",
            "Book Number"};
        private Object[][] data;
        
        public Show_borrowed_books_table_model() {
            data = new Object[0][4];
        }
        
        
        public void setData(Object[][] val) {
            data = val;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 0) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }