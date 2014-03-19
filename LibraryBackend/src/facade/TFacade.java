package facade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import entities.TBook;
import entities.TTitle_book;
import entities.TUser;

public class TFacade implements Serializable {
	private ArrayList<TTitle_book> mTitle_books = new ArrayList<TTitle_book>();
	private ArrayList<TUser> mUsers = new ArrayList<TUser>();

	public static void main(String[] t) {
		TFacade ap = new TFacade();
		String t1[] = { "1", "Author1", "Title1", "ISBN1", "Publisher1" };
		String t2[] = { "1", "Author2", "Title2", "ISBN2", "Publisher2" };
		String t3[] = { "1", "Author3", "Title3", "ISBN3", "Publisher3" };
		String t4[] = { "3", "Author1", "Title1", "ISBN1", "Publisher1", "Actor1" };
		String t5[] = { "3", "Author2", "Title2", "ISBN2", "Publisher2", "Actor2" };
		String t6[] = { "3", "Author4", "Title4", "ISBN4", "Publisher4", "Actor4" };
		ap.add_title_book(t1);
		ap.add_title_book(t2);
		ap.add_title_book(t2);
		ap.add_title_book(t3);
		ap.add_title_book(t4);
		ap.add_title_book(t5);
		ap.add_title_book(t5);
		ap.add_title_book(t6);
		String lan = ap.getmTitle_books().toString();
		System.out.println(lan);
		String d1[] = { "0", "ISBN1" };
		String d2[] = { "0", "ISBN2" };
		String d3[] = { "0", "ISBN5" };
		String d4[] = { "2", "ISBN1", "Actor1" };
		String d5[] = { "2", "ISBN4", "Actor4" };
		String tr1[] = { "0", "1" };
		String tr2[] = { "0", "2" };
		String tr3[] = { "1", "3", "3" };
		String tr4[] = { "1", "2", "-1" };
		TTitle_book pom = ap.add_book(d1, tr1);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d2, tr1);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d2, tr1);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d2, tr2);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d3, tr2);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d4, tr3);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d4, tr3);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d4, tr4);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		pom = ap.add_book(d5, tr2);
		if (pom != null) {
			System.out.print(pom.getmBooks().toString());
		}
		System.out.println();
		ap.Print_title_books();
		ap.Print_books();
		System.out.print("\nSearching of a title");
		System.out.print(ap.Search_title_book(t5).toString());
		System.out.print("\nSearching of an accessible book of a select title");
		System.out.print(ap.Search_accessible_book(d4, "2").toString());
		System.out.println();

		System.out.print("\nLoan");
		ArrayList<TUser> mUsers = ap.getmUsers();
		mUsers.add(new TUser("John"));
		mUsers.add(new TUser("Petr"));
		ap.loan_book(d4, "2", 1);
		ap.loan_book(d4, "5", 0);
		ap.loan_book(d1, "5", 0);
		mUsers.get(1).printBooks();
		mUsers.get(0).printBooks();
	}

	public ArrayList<TTitle_book> getmTitle_books() {
		return mTitle_books;
	}

	public ArrayList<TUser> getmUsers() {
		return mUsers;
	}

	public void setmTitle_books(ArrayList<TTitle_book> mTitle_books) {
		this.mTitle_books = mTitle_books;
	}

	public synchronized ArrayList<String> gettitle_books() {
		ArrayList<String> title_books = new ArrayList<String>();
		Iterator<TTitle_book> iter = mTitle_books.iterator();

		while (iter.hasNext()) {
			TTitle_book book = iter.next();
			title_books.add(book.toString());
		}

		return title_books;
	}

	public synchronized TTitle_book search_title_book(TTitle_book title_book) {
		int idx;
		if ((idx = mTitle_books.indexOf(title_book)) != -1) {
			return mTitle_books.get(idx);
		}

		return null;
	}

	public synchronized TTitle_book add_title_book(String[] data) {
		TFactory factory = new TFactory();
		TTitle_book title_book = factory.create_title_book(data);
		if (search_title_book(title_book) == null) {
			mTitle_books.add(title_book);
		}

		return title_book;
	}

	public synchronized TTitle_book add_book(String[] data1, String[] data2) {
		TFactory factory = new TFactory();
		TTitle_book help1 = factory.create_title_book(data1);
		TTitle_book title_exists;
		if ((title_exists = search_title_book(help1)) != null) {
			title_exists.add_book(data2);
		}

		return title_exists;
	}

	public synchronized TTitle_book Search_title_book(String[] data) {
		TFactory tf = new TFactory();
		TTitle_book title = tf.create_title_book(data);
		TTitle_book title_exists = search_title_book(title);
		return title_exists;
	}

	public synchronized TBook Search_accessible_book(String[] data1, Object data2) {
		TFactory tf = new TFactory();
		TTitle_book help = tf.create_title_book(data1);
		TTitle_book title_exist = search_title_book(help);

		if (title_exist != null) {

			TBook book = title_exist.search_accessible_book(data2);

			if (book != null) {
				return book;
			}
		}

		return null;
	}

	public synchronized void Print_books() {
		System.out.print("\nPrint books");
		for (int i = 0; i < mTitle_books.size(); i++) {
			ArrayList<String> help = mTitle_books.get(i).getBooks();
			for (int j = 0; j < help.size(); j++) {
				System.out.print(help.get(j));
			}
		}
	}

	public synchronized void Print_title_books() {
		System.out.print("\nPrint title books");
		ArrayList<String> titles = gettitle_books();
		for (int i = 0; i < titles.size(); i++) {
			System.out.print(titles.get(i));
		}
	}

	public synchronized TBook loan_book(String[] data1, Object data2, long user_id) {
		TBook book = Search_accessible_book(data1, data2);
		if (book == null) {
			return null;
		}
		int i = 0;

		while (mUsers.get(i).getId() != user_id || mUsers.size() < i) {
			i++;
		}

		if (mUsers.get(i).getId() == user_id) {
			mUsers.get(i).loanBook(book, data2);
		}

		return book;
	}
}
