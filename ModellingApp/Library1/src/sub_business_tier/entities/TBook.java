package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;


public class TBook implements Serializable {
	private int number;
	private TTitle_book mTitle_book;
        private TLoanData loan; 
        
	public TTitle_book getmTitle_book() {
		return mTitle_book;
	}

	public void setmTitle_book(TTitle_book mTitle_book) {
		this.mTitle_book = mTitle_book;
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
       return loan != null;
    }

    public void setLoan(TLoanData loan) {
        this.loan = loan;
    }
    
    
}