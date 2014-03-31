
public abstract class Estimation {
	public abstract int getValue();

	public abstract void setValue(int value);

	public abstract int getOperType();

	public abstract void setOperType(int operType);
	
	public abstract boolean isGreatThan(Estimation another);

	public abstract boolean isLessThan(Estimation another);
	
	public abstract boolean isConflict(Estimation another);

	public abstract boolean isEqual(Estimation parentEst);

	public abstract boolean isWin();

	public abstract boolean isLose();
}
