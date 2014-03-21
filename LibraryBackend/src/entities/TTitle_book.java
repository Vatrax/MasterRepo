package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import facade.TFactory;

@Entity
public class TTitle_book implements Serializable {

	private static final long serialVersionUID = 1L;
	private String publisher;
	private String ISBN;
	private String title;
	private String author;
	private ArrayList<TBook> mBooks = new ArrayList<TBook>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

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
		return "";
	}

	public void setActor(String actor) {
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
		}
	}

	public TBook search_book(TBook book) {
		int idx = mBooks.indexOf(book);
		if (idx != -1) {
			return mBooks.get(idx);
		}
		return null;
	}

	public TBook search_accessible_book(String data) {
		Iterator<TBook> iter = mBooks.iterator();
		while (iter.hasNext()) {
			TBook book = iter.next();
			if (book.period_pass(data)) {
				return book;
			}
		}
		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
