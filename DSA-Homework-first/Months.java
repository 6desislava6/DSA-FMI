
public enum Months {
	JANUARY("January"),
	FEBRURY("February"),
	MARCH("March"),
	APRIL("April"),
	MAY("May"),
	JUNE("June"),
	JULY("July"),
	AUGUST("August"),
	SEPTEMBER("September"),
	OCTOBER("October"),
	NOVEMBER("November"),
	DECEMBER("December");
	private Months(String name) {
		this.name = name;
	}
	private final String name;
	private String get_name(){
		return this.name;
	}
	@Override
	public String toString() {
		return this.name;
	}
}
// The enum declaration defines a class (called an enum type).
// The enum class body can include methods and other fields.
// The compiler automatically adds some special methods when it creates an enum. 
// Java requires that the constants be defined first,
// prior to any fields or methods. Also, when there are fields and methods,
// the list of enum constants must end with a semicolon.
// Note: The constructor for an enum type must be package-private or private access.
// It automatically creates the constants that are defined at the beginning of the enum body.
// You cannot invoke an enum constructor yourself. 