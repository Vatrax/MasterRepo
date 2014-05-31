/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import java.util.ArrayList;
import java.util.List;
import sub_business_tier.TBase;
import javax.ejb.Stateless;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TLoanData;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author krzaczek
 */
@Stateless
public class Facade implements FacadeRemote {

    TFacade facade = new TFacade();
    TBase base = new TBase(facade);

    // definition of methods for application data service 
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
    public void update_titles() throws Exception {
        base.update_titles();
    }

    @Override
    public void update_books() throws Exception {
        base.update_books();
    }

    @Override
    public void update_data() throws Exception {
        base.update_data();
    }

    @Override
    public void add_titles() throws Exception {
        base.add_titles();
    }

    @Override
    public void add_books() throws Exception {
        base.add_books();
    }

    @Override
    public ArrayList<ArrayList<String>> titles() throws Exception {
        return base.titles();
    }

    @Override
    public List<String> getUsers() {
        return facade.getUsers_r();
    }

    @Override
    public void addUser(String toString) {
        facade.addUser(toString);
    }

    @Override
    public TLoanData loanBook(String[] title, String period, String user) {
        return facade.loanBook(title, period, user);
    }

    @Override
    public Object giveBackBook(String[] titleBookInfos, String[] bookInfos, String user) {
        return facade.giveBackBook_r(titleBookInfos, bookInfos, user);
    }

    @Override
    public String[][] getUserBooks(String u) {
        return facade.getUserBooks(u);
    }

}
