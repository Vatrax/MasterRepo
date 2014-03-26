package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import facade.TFactory;

public class TUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private static long lastUserId = 0;

	private ArrayList<TBook> mBooks = new ArrayList<TBook>();
	private final long id;
	private String name;

	public TUser(String name) {
		this.id = lastUserId++;
		this.name = name;
	}

	public synchronized long getId() {
		return id;
	}

	public synchronized void loanBook(TBook book, String period) {
		if (!book.period_pass(period) && book.getLoanUntil() == null) {
			mBooks.add(book);
			book.setLoanUntil(TFactory.mdays(period));
		}
	}

	public synchronized void giveBackBook(TBook book) {
		Iterator<TBook> iter = mBooks.iterator();
		TBook search = iter.next();
		while (iter.hasNext() || !book.equals(search)) {
			search = iter.next();
		}
		if (book.equals(search)) {
			search.setLoanUntil(null);
			iter.remove();
		}
	}

	public synchronized void printBooks() {
		System.out.print("\nUser " + name + " id " + id);
		Iterator<TBook> iter = mBooks.iterator();
		TBook b;
		while (iter.hasNext()) {
			b = iter.next();
			System.out.println(b.toString());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		TUser other = (TUser) obj;
		return this.id == other.id;
	}

}
