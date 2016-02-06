
public class KeyDuplicateException extends KDException {

	protected KeyDuplicateException() {
		super("Key already in tree");
	}

	public static final long serialVersionUID = 1L;
}