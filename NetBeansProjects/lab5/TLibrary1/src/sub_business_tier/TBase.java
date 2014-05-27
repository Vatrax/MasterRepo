package sub_business_tier;


import integration_tier.TBookController;
import integration_tier.TTitle_bookController;
import java.util.ArrayList;
import java.util.List;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krzaczek
 */
public class TBase {
    private TTitle_bookController titleJpaContoller;
    private TBookController bookJpaController;
    private TFacade facade;
    private TTitle_book titles[];
    private TBook books[];
    
    public TBase(TFacade facade) {
    this.facade = facade;
    titleJpaContoller = new TTitle_bookController();
    bookJpaController = new TBookController();
        update_data();
    }

    public void update_data() {
        update_titles();
        update_books();
        facade.update_data(titles,books);
    }

    public void update_titles() {
        titles = titleJpaContoller.getTTitle_books_();
    }

    public void update_books() {
        books = bookJpaController.getTBooks_();
    }
    
    public void add_titles() {
        titleJpaContoller.addTTitle_books(facade.getmTitle_books());
    }
    
    public void add_books() {
        bookJpaController.addTBooks(facade.getmTitle_books());
    }
    
    public ArrayList<ArrayList<String>> titles() throws Exception { 
 List<TTitle_book> help1 = titleJpaContoller.getTTitle_books(); 
 ArrayList<ArrayList<String>> help2 = new ArrayList(); 
 for (TTitle_book t : help1) { 
 ArrayList<String> help3 = new ArrayList(); 
 help3.add(t.getPublisher()); 
 help3.add(t.getISBN()); 
 help3.add(t.getTitle()); 
 help3.add(t.getAuthor()); 
 help3.add(t.getActor()); 
 help2.add(help3); 
 } 
 return help2; 
 } 
} 
