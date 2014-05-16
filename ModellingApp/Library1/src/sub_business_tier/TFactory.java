package sub_business_tier;
import java.util.Date; 

import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TBook_period;
import sub_business_tier.entities.TLoanData;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TTitle_book_on_tape;
import sub_business_tier.entities.TUser;

public class TFactory {

    private static final long day = 24 * 60 * 60 * 1000;

    public static Date mdays (String data) {	
        return new Date(System.currentTimeMillis() + Integer.parseInt(data)*day);
    }

    public TTitle_book create_title_book (String[] data) {
        switch(Integer.parseInt(data[0])) {
            case 0: {
                if( data.length != 2 )
                    return null;
                TTitle_book titleBook = new TTitle_book();
                titleBook.setISBN(data[1]);
                return titleBook;
            }
            case 1: {
                if( data.length != 5 )
                    return null;
                TTitle_book titleBook = new TTitle_book();
                titleBook.setAuthor(data[1]);
                titleBook.setTitle(data[2]);
                titleBook.setISBN(data[3]);
                titleBook.setPublisher(data[4]);
                return titleBook;
            }
            case 2: {
                if( data.length != 3 )
                    return null;
                TTitle_book_on_tape titleBook = new TTitle_book_on_tape();
                titleBook.setISBN(data[1]);
                titleBook.setActor(data[2]);
                return titleBook;
            }
            case 3: {
                if( data.length != 6 )
                    return null;
                TTitle_book_on_tape titleBook = new TTitle_book_on_tape();
                titleBook.setAuthor(data[1]);
                titleBook.setTitle(data[2]);
                titleBook.setISBN(data[3]);
                titleBook.setPublisher(data[4]);
                titleBook.setActor(data[5]);
                return titleBook;
            }
        }
        return null;
    }

    public TBook create_book (String[] data) {
        switch(Integer.parseInt(data[0])) {
        case 0: {
            TBook book = new TBook();
            book.setNumber(Integer.parseInt(data[1]));
            return book;
        }
        case 1: {
            TBook_period period = new TBook_period();
            period.setNumber(Integer.parseInt(data[1]));
            period.startPeriod(data[2]);
            return period;

        }
    }
    return null;
    }
    
    public TUser createUser( String userName ) {
        TUser u = new TUser();
        u.setName(userName);
        return u;
    }
    
    public TLoanData createLoanData( TBook b, TUser u, String loanInDays ) {
        TLoanData loan = new TLoanData();
        Date loanExpiration = mdays(loanInDays);
        loan.setBook(b);
        loan.setUser(u);
        loan.setExpirationTime(loanExpiration);
        return loan;
    }
}