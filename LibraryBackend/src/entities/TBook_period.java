package entities;

import java.io.Serializable;
import java.util.Date;

import facade.TFactory;

public class TBook_period extends TBook implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date period;

	@Override
	public Date getPeriod() {
		return period;
	}

	@Override
	public void setPeriod(Date date) {
		this.period = date;
	}

	@Override
	public boolean period_pass(String data) {
		Date now = TFactory.mdays("0");
		if (getPeriod() == null) {
			return true;
		}
		return now.after(getPeriod());
	}

	@Override
	public void startPeriod(String data) {
		setPeriod(TFactory.mdays(data));
	}

	@Override
	public String toString() {
		String s = super.toString();
		if (period != null) {
			s += " Period: " + period.toString();
		}
		return s;
	}

}
