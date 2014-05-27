package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import sub_business_tier.TFactory;

@Entity
public class TTitle_book implements Serializable {
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String publisher;
	private String ISBN;
	private String title;
	private String author;
        @OneToMany(mappedBy = "mTitle_book")
        private Collection<TBook> books;
        public Collection<TBook> getBooks() {return books;}
        public void setBooks(Collection<TBook> b) { this.books = b;}

        public TTitle_book() {
            id = null;
        }
        
        @Transient 
        private ArrayList<TBook> mBooks = new ArrayList<TBook>();
        public ArrayList<TBook> getmBooks() {
		return mBooks;
	}
        public void setmBooks(ArrayList<TBook> mBooks) {
		this.mBooks = mBooks;
	}

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



	public ArrayList<String> getTBooks() {
		ArrayList<String> books = new ArrayList<String>();
		Iterator<TBook> iter = mBooks.iterator();
		while (iter.hasNext()) {
			TBook book = iter.next();
			books.add(book.toString());
		}
		return books;
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
                book.setmTitle_book(this);
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

    public TBook getBook(String[] bookInfos) {
            TFactory factory = new TFactory();
            TBook book = factory.create_book(bookInfos);
            book = this.search_book(book);
            if( book == null ) 
                System.out.println("No such book");
            return book;   
            
    }
}
