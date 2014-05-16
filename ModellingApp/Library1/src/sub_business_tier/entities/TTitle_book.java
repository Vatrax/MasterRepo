package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import sub_business_tier.TFactory;

public class TTitle_book implements Serializable {
	private String publisher;
	private String ISBN;
	private String title;
	private String author;
	private ArrayList<TBook> mBooks = new ArrayList<TBook>();


	private Long id;

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
		return "Title: " + title + " Author: " + author + " ISBN: " + ISBN + " Publisher: " + publisher;
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
                book.setmTitle_book(this); //?!?!? NIE WIEM CZY NIE PSUJE, ALE CHYBA TAK TRZERBA
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
			if ( !book.period_pass(data) && !book.isBorrowed() ) {
				return book;
			}
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
