package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import facade.TFactory;

public class TTitle_book implements Serializable {

	private static final long serialVersionUID = 1L;
	private String publisher;
	private String ISBN;
	private String title;
	private String author;
	private ArrayList<TBook> mBooks = new ArrayList<TBook>();

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ArrayList<TBook> getmBooks() {
		return mBooks;
	}

	public ArrayList<String> getBooks() {
		ArrayList<String> books = new ArrayList<String>();
		Iterator<TBook> iter = mBooks.iterator();
		while (iter.hasNext()) {
			TBook book = iter.next();
			books.add(book.toString());
		}
		return books;
	}

	public void setmBooks(ArrayList<TBook> mBooks) {
		this.mBooks = mBooks;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		if (getISBN().equals(((TTitle_book) obj).getISBN())) {
			if (getActor().equals(((TTitle_book) obj).getActor())) {
				return true;
			}

		}
		return false;
	}

	public String getActor() {
		try {
			return ((TTitle_book_on_tape) this).getActor();
		} catch (Exception e) {
			return "";
		}
	}

	public void setActor(String actor) {

		try {
			((TTitle_book_on_tape) this).setActor(actor);
		} catch (Exception e) {
			;// this = new TTitle_book_on_tape();
		}
	}

	@Override
	public String toString() {
		return "\nTitle: " + title + " Author: " + author + " ISBN: " + ISBN + " Publisher: " + publisher;
	}

	public synchronized void add_book(String[] data) {
		TFactory tf = new TFactory();
		TBook newbook = tf.create_book(data);
		newbook.setmTitle_book(this); // Or change the Tbook.equals method
		if (search_book(newbook) == null) {
			mBooks.add(newbook);
			return;
		}
	}

	public TBook search_book(TBook book) {
		int idx;
		if ((idx = mBooks.indexOf(book)) != -1) {
			return mBooks.get(idx);
		}
		return null;
	}

	public TBook search_accessible_book(Object data) {
		Iterator<TBook> iter = mBooks.iterator();
		while (iter.hasNext()) {
			TBook book = iter.next();
			if (book.period_pass(data)) {
				return book;
			}
		}
		return null;
	}

}
