package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TBook implements Serializable {
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
	private int number;
        @ManyToOne
	private TTitle_book mTitle_book;
        @OneToOne
        private TLoanData mLoan_data; 

        public TLoanData getmLoan_data() {
            return mLoan_data;
        }

        public void setmLoan_data(TLoanData mLoan_data) {
            this.mLoan_data = mLoan_data;
        }
        
	public TTitle_book getmTitle_book() {
		return mTitle_book;
	}

	public void setmTitle_book(TTitle_book mTitle_book) {
		this.mTitle_book = mTitle_book;
	}
        
        public TBook() {
            id = null;
        }

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getPeriod() {
		return null;
	}

	public void setPeriod(Date period) {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mTitle_book == null) ? 0 : mTitle_book.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public String toString() {
		return mTitle_book.toString() + " Number: " + number;
	}

	public boolean period_pass(String data) {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TBook other = (TBook) obj;
		if (mTitle_book == null) {
			if (other.mTitle_book != null) {
			    return false;
			}
		} else if (!mTitle_book.equals(other.mTitle_book)) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		return true;
	}

	public void startPeriod(String data) {
	}

    boolean isBorrowed() {
       return mLoan_data != null;
    }

    public void setLoan(TLoanData loan) {
        this.mLoan_data = loan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}