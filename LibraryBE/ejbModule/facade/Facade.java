/*
 * Copyright (c) 2014 Nokia Solutions and Networks. All rights reserved.
 */
package facade;

import java.util.ArrayList;

import javax.ejb.Remote;

import entities.TBook;
import entities.TTitle_book;
import entities.TUser;

/**
 * @author: Wojciech Krzaczek (wojciech.krzaczek@nsn.com)
 */
@Remote
public interface Facade {

	ArrayList<TTitle_book> getmTitle_books();

	void setmTitle_books(ArrayList<TTitle_book> title_books);

	Object[][] gettitle_books();

	TTitle_book search_title_book(TTitle_book title_book);

	TTitle_book add_title_book(String data[]);

	TTitle_book add_book(String data1[], String data2[]);

	ArrayList<String> Search_title_book(String data[]);

	TBook Search_accessible_book(String data1[], String data2);

	void Print_books();

	void Print_title_books();

	TBook loan_book(String[] data1, String data2, TUser user);

}