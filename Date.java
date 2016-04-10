public class Date {
	int day;
	int month;
	int year;
		
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public int dayOfWeek() {
		return 0; // fix
	}
	@Override
	public boolean equals(Object o) {
		if (o == null || !o.getClass().equals(this.getClass())) {
			return false;
		}
		Date other = (Date) o;
		return (this.day == other.day && this.month == other.month && this.year == other.year);
	}
	@Override
	public String toString() {
		return "" + this.day + "/" + this.month + "/" + this.year;
	}
	@Override
	public int hashCode() {
		return (10000000 * day) + (10000 * month) + year;
	}
}