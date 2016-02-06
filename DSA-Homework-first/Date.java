
public class Date implements Comparable<Date> {

	private int day;

	private int month;
	private int year;

	// default constructor.. Is it necessary?
	public Date() {
		this.day = 0;
		this.month = 0;
		this.year = 0;
	}

	// Constructor
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	// Copy constructor
	public Date(Date aDate) {
		this.day = aDate.day;
		this.month = aDate.month;
		this.year = aDate.year;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return this.day + "." + this.month + "." + this.year;
	}

	private int compareThings(int first, int second) {
		return first > second ? +1 : first < second ? -1 : 0;
	}

	@Override
    public int compareTo(Date aDate) {
		int yearComparison = compareThings(this.year, aDate.year);
		
		if (yearComparison == 0) {
			int monthComparison = compareThings(this.month, aDate.month);
			if (monthComparison == 0){
				return compareThings(this.day, aDate.day);
			} else {
				return monthComparison;
			}
		} else {
			return yearComparison;
		}
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public static void main(String[] args) {
		Date mydate = new Date(1, 12, 2015);
		System.out.println(mydate);

	}

}
