/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_tier;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import sub_business_tier.entities.*;
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
}