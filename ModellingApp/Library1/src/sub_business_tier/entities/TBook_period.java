package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import sub_business_tier.TFactory;


public class TBook_period extends TBook implements Serializable {
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
		Date periodEnd = TFactory.mdays(data);
		if (getPeriod() == null) {
			return true;
		}
		return periodEnd.after(getPeriod());
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