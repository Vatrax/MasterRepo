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
	public ArrayList<TTitle_book> getmTitle_books();
	public void setmTitle_books(ArrayList<TTitle_book> title_books);
	public Object[][] gettitle_books();
	public TTitle_book search_title_book(TTitle_book title_book);
	public TTitle_book add_title_book(String data[]);
	public TTitle_book add_book(String data1[], String data2[]);
	public ArrayList<String> Search_title_book(String data[]);
	public TBook Search_accessible_book(String data1[], String data2);
	public void Print_books();
	public void Print_title_books();
        public TBook giveBackBook(String[] titleBookInfos, String[] bookInfos, String userName);
	public TLoanData loanBook(String[] titleBookInfos, String loanTimeInDays, String userName) throws Exception;
        public TUser getUser(String userName);
        public TUser addUser( String userName );
        public List<TUser> getmUsers();
}