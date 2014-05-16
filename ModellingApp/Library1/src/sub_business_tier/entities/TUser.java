package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sub_business_tier.TFactory;

public class TUser implements Serializable {
	private ArrayList<TLoanData> mLoans = new ArrayList<TLoanData>();
        private String name;
        

	public void setName(String userName) {
            this.name = userName;
        }

	public synchronized TLoanData loanBook(TBook book, String loanTimeInDays) {
                if ( !checkLoanPossibility() )
                    return null;
            
                TFactory f = new TFactory();
                TLoanData loanData = f.createLoanData(book, this, loanTimeInDays);
                this.mLoans.add(loanData);
                return loanData;
	}

	public synchronized TLoanData giveBackBook(TBook book) {
            TFactory factory = new TFactory();
            TLoanData ref = factory.createLoanData(book, this, "0");
            int idx = mLoans.indexOf(ref);
            if( idx != -1 ) {
                TLoanData loan = mLoans.remove(idx);
                return loan;
                
            }
            return null;
	}

	public synchronized void printBooks() {
		System.out.println("\nUser " + name+" has "+mLoans.size()+ " books:");
		Iterator<TLoanData> iter = mLoans.iterator();
		TLoanData l;
		while (iter.hasNext()) {
			l = iter.next();
                        System.out.print("Book: ");
			System.out.println(l.toString());
		}
	}

	@Override
	public boolean equals(Object obj) {
		TUser other = (TUser) obj;
		return this.name.equals(other.name);
	}
        
        public List<TLoanData> getmLoans() {
            return mLoans;
        }

    private boolean checkLoanPossibility() {
        return mLoans.size() < 5;
    }
    
    @Override
    public String toString() {
        return "User "+getName()+"";
    }

    public String getName() {
        return name;
    }
}