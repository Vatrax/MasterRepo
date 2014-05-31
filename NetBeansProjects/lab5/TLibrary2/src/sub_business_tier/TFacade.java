/*
 * TFacade.java
 *
 * Created on 2 marzec 2007, 22:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package sub_business_tier;

import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TUser;
import sub_business_tier.entities.TBook;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sub_business_tier.entities.TLoanData;

public class TFacade implements Serializable {
      
	private static final long serialVersionUID = 1L;
	private ArrayList<TTitle_book> mTitle_books = new ArrayList<TTitle_book>();
        private ArrayList<TUser> mUsers = new ArrayList<TUser>();
	private TFactory factory = new TFactory();
	

	public ArrayList<TTitle_book> getmTitle_books() {
            //System.setOut(null);
            return mTitle_books;
	}

	public void setmTitle_books(ArrayList<TTitle_book> title_books) {
		mTitle_books = title_books;
	}

	public synchronized Object[][] gettitle_books() {
		Object[][] title_books = new Object[mTitle_books.size()][];
		int i = 0;
		for (TTitle_book next : mTitle_books) {
			String[] title = new String[5];
			title[0] = next.getPublisher();
			title[1] = next.getISBN();
			title[2] = next.getTitle();
			title[3] = next.getAuthor();
			title[4] = next.getActor();
			title_books[i++] = title;
		}
		return title_books;
	}

	
	public synchronized TTitle_book search_title_book(TTitle_book title_book) {
		int idx = mTitle_books.indexOf(title_book);
		if (idx != -1) {
			title_book = mTitle_books.get(idx);
			return title_book;
		}
		return null;
	}

	
	public synchronized TTitle_book add_title_book(String data[]) {
		TTitle_book title_book = factory.create_title_book(data);
		if (search_title_book(title_book) == null) {
			mTitle_books.add(title_book);
			return title_book;
		}
		return null;
	}

	
	public synchronized TTitle_book add_book(String data1[], String data2[]) {
        	TTitle_book help1, help2 = null;
		help1 = factory.create_title_book(data1);
		if ((help2 = search_title_book(help1)) != null) {
			help2.add_book(data2);
		}
		return help2;
	}

	
	public synchronized ArrayList<String> Search_title_book(String data[]) {
		TTitle_book title_book = factory.create_title_book(data);
		TTitle_book title_book_ = search_title_book(title_book);
		if (title_book_ != null) {
			return title_book_.getTBooks();
		}
		return null;
	}

        
	
	public synchronized TBook Search_accessible_book(String data1[], String data2) {
		TTitle_book title_book_help = factory.create_title_book(data1);
		TTitle_book title_exist = search_title_book(title_book_help);
		if (title_exist != null) {
			return title_exist.search_accessible_book(data2);
		}
		return null;
	}
        
	
	public synchronized void Print_books() {
		System.out.print("\nBooks");
		for (int i = 0; i < mTitle_books.size(); i++) {
			ArrayList<String> help_list = mTitle_books.get(i).getTBooks();
			for (int j = 0; j < help_list.size(); j++) {
				System.out.print(help_list.get(j).toString());
			}
		}
	}

	
	public synchronized void Print_title_books() {
		System.out.println("\n\nTitles of book");
		Object[][] help_list = gettitle_books();
		for (int i = 0; i < help_list.length; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(help_list[i][j] + " ");
			}
			System.out.println();
		}
	}
       
        public TTitle_book istitles(String[] titleBookInfos)
        {
            
            TTitle_book title_book = factory.create_title_book(titleBookInfos);
            title_book = search_title_book(title_book);
            if ( title_book == null ) 
                System.err.println("No such title");
            
            return title_book;
        }
        
        public synchronized TLoanData giveBackBook(String[] titleBookInfos, String[] bookInfos, String userName) {
            TUser user = getUser(userName);
            if ( user == null )
                return null; 
            TTitle_book title_book;
            if ( (title_book = this.istitles(titleBookInfos)) == null )
                return null;

            TBook book = title_book.getBook(bookInfos);
            if( book == null )
                return null;
            
            TLoanData loan = user.giveBackBook(book);
            if( loan == null )
                return null;
            
            return loan;
        }
	
	public synchronized TLoanData loanBook(String[] titleBookInfos, String loanTimeInDays, String userName) {
		TBook book = Search_accessible_book(titleBookInfos, loanTimeInDays); //Book is avaiable for sure
		if (book == null) {
                    return null;
		}
                
                TUser user = getUser(userName);
                if ( user == null ) {
                   return null;
                }

                
                TLoanData loan = user.loanBook(book, loanTimeInDays);
                if ( loan == null ) {
                   return null;
                }
                
                book.setLoan(loan);
		return loan;
	}

	public static void main(String t[]) {
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
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d2, tr1);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d2, tr1);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d2, tr2);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d3, tr2);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d4, tr3);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d4, tr3);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d4, tr4);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		pom = ap.add_book(d5, tr2);
		if (pom != null) {
			System.out.println(pom.getmBooks().toString());
		}
		ap.Print_title_books();
		ap.Print_books();
		System.out.print("\nSearching of a title");
		System.out.print(ap.Search_title_book(t6));
		System.out.print("\nSearching of an accessible book of a select title");
		System.out.println(ap.Search_accessible_book(d4, "2").toString());
		System.out.println();
                ap.addUser("a");
                ap.addUser("a");
                ap.addUser("b");
                ap.addUser("a");
                ap.addUser("a");
                ap.addUser("a");
                System.out.println(ap.getmUsers().size());
                ap.loanBook(d2, "2", "a");
                ap.loanBook(d2, "2", "a");
                ap.loanBook(d4, "2", "a");
                
                ap.loanBook(d5, "2", "b");
                ap.loanBook(d1, "2", "b");
                ap.loanBook(d1, "2", "b");
                ap.loanBook(d1, "2", "b");
                ap.getUser("a").printBooks();
                ap.getUser("b").printBooks();
             
                ap.giveBackBook(d2, tr1, "a");
                ap.giveBackBook(d2, tr2, "a");
                ap.giveBackBook(d2, tr3, "a");
                ap.giveBackBook(d2, tr4, "a");
                ap.getUser("a").printBooks();                
	}
        
        
        
        

    public synchronized TUser getUser(String userName) {
        TUser user = null;
        user = factory.createUser(userName);
        int idx = this.mUsers.indexOf(user);
        if ( idx != -1 ) {
            user = this.mUsers.get(idx);
            return user;
        }
        System.out.println("No such user");
        return null;
    }
    

    
    public synchronized TUser addUser( String userName ) {
        TUser user = null;
        user = getUser( userName );
        if( user != null ) //user exists
            return null;
        else {
            user = factory.createUser(userName);
            mUsers.add(user);
            return user;
        }
    }
    
    public synchronized List<TUser> getmUsers() {
        return mUsers;        
    }
    
    public synchronized Object loanBook_r(String[] titleBookInfos, String loanTimeInDays, String userName) {
        TLoanData loan = loanBook(titleBookInfos, loanTimeInDays, userName);
        if(loan == null)
            return null;
        loan.getBook();
        loan.getExpirationTime();
        loan.getUser();
        return loan.getBook().toString();
    }

    public synchronized Object giveBackBook_r(String[] titleBookInfos, String[] bookInfos, String userName) {
       TLoanData loan = giveBackBook(titleBookInfos, bookInfos, userName);
       if(loan == null)
           return null;
       loan.getBook();
       loan.getExpirationTime();
       loan.getUser();
       return loan.getBook().toString();
    }
    
    public synchronized List<String> getUsers_r() {
        List<String> l = new ArrayList<String>();
        for( TUser u : mUsers ) 
            l.add(u.getName());
        return l;
    }
    
    public String[][] getUserBooks(String username) {
        TUser u = getUser(username);
        List<TLoanData> loans = u.getmLoans();
        String[][] books = new String[loans.size()][];
	int i = 0;
	for (TLoanData next : loans) {
		String[] book = new String[4];
		book[0] = next.getBook().getmTitle_book().getISBN();
		book[1] = next.getBook().getmTitle_book().getTitle();
		book[2] = next.getBook().getmTitle_book().getActor();
                book[3] = String.valueOf(next.getBook().getNumber());   
		books[i++] = book;
	}
        return books;
    }

    public synchronized void update_data(TTitle_book titles[], TBook books[]) {
        mTitle_books.clear();
        for(TTitle_book title : titles) {
         mTitle_books.add(title);
        }
        for(TTitle_book title: mTitle_books) {
            for(TBook book: books) {
            TTitle_book title1 = book.getmTitle_book();
            if(title1 != null) {
                if(title1.equals(title)){
                    title.getmBooks().add(book);
                }
            }
            }
        }
    }
}
