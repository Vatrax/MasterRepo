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
    public Object giveBackBook(String[] titleBookInfos, String[] bookInfos, String userName) {
       return facade.giveBackBook_r(titleBookInfos, bookInfos, userName);
    }
    @Override
    public Object loanBook(String[] titleBookInfos, String loanTimeInDays, String userName) {
        return facade.loanBook_r(titleBookInfos, loanTimeInDays, userName);
    }

    @Override
    public TUser addUser(String userName) {
        return facade.addUser(userName);
    }

    @Override
    public List<String> getUsers() {
       return facade.getUsers_r();
    }

    @Override
    public String[][] getUserBooks(String userName) {
        return facade.getUserBooks(userName);
    }
}