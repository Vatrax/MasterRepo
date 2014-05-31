package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import sub_business_tier.TFactory;
@Entity
public class TUser implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private static final long serialVersionUID = 1L;
    
    @OneToMany(mappedBy = "user")
    private Collection<TLoanData> loans;
    public Collection<TLoanData> getLoans() {return loans;}
    public void setLoans(Collection<TLoanData> l) { this.loans =l;}
        
    @Transient
    private ArrayList<TLoanData> mLoans = new ArrayList<TLoanData>();

    public ArrayList<TLoanData> getmLoans() {
        return mLoans;
    }

    public void setmLoans(ArrayList<TLoanData> mLoans) {
        this.mLoans = mLoans;
    }
        
        
        private String name;
        

        public TUser() {
            id = null;
        }
        
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
                book.setLoan(null);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}