/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TUser;

/**
 *
 * @author ciesiolk
 */
@Remote
public interface FacadeRemote {
     public Object[][] gettitle_books(); 
 public TTitle_book search_title_book(TTitle_book title_book); 
 public TTitle_book add_title_book(String data[]); 
 public TTitle_book add_book(String data1[], String data2[]); 
 public ArrayList<String> Search_title_book(String data[]); 


 public Object giveBackBook(String[] titleBookInfos, String[] bookInfos, String userName);
 public Object loanBook(String[] titleBookInfos, String loanTimeInDays, String userName);
 public List<String> getUsers();
 public TUser addUser(String userName);
 public String[][] getUserBooks(String userName);
 
 public void Print_books(); 
 
 public void Print_title_books();
 
 public void update_titles() throws Exception; 
 
 public void update_books() throws Exception; 
 
 public void update_data() throws Exception; 
 
 public void add_titles() throws Exception; 
 
 public void add_books() throws Exception; 
 
 public ArrayList<ArrayList<String>> titles() throws Exception; 

}
