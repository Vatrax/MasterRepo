/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business_tier;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TLoanData;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TUser;
@Stateless
public class Facade implements FacadeRemote {
TFacade facade = new TFacade();
@Override
public Object[][] gettitle_books() {
return facade.gettitle_books();
}
@Override
public TTitle_book search_title_book(TTitle_book title_book) {
return facade.search_title_book(title_book);
}
@Override
public TTitle_book add_title_book(String data[]) {
return facade.add_title_book(data);
}
@Override
public TTitle_book add_book(String data1[], String data2[]) {
return facade.add_book(data1, data2);
}
@Override
public ArrayList<String> Search_title_book(String data[]) {
return facade.Search_title_book(data);
}
@Override
public TBook Search_accessible_book(String data1[], String data2) {
return facade.Search_accessible_book(data1, data2);
}
@Override
public void Print_books() {
facade.Print_books();
}
@Override
public void Print_title_books() {
facade.Print_title_books();
}

@Override
    public ArrayList<TTitle_book> getmTitle_books() {
       return facade.getmTitle_books();
    }
@Override
    public void setmTitle_books(ArrayList<TTitle_book> title_books) {
       facade.setmTitle_books(title_books);
    }
@Override
    public TBook giveBackBook(String[] titleBookInfos, String[] bookInfos, String userName) {
       return facade.giveBackBook(titleBookInfos, bookInfos, userName);
    }
@Override
    public TLoanData loanBook(String[] titleBookInfos, String loanTimeInDays, String userName) throws Exception {
       return facade.loanBook(titleBookInfos, loanTimeInDays, userName);
    }
@Override
    public TUser getUser(String userName) {
       return facade.getUser(userName);
    }
@Override
    public TUser addUser(String userName) {
       return facade.addUser(userName);
    }
    
@Override
    public List<TUser> getmUsers() {
       return facade.getmUsers();
    }
}