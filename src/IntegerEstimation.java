
public class IntegerEstimation extends Estimation{
	public static final int NOTSET = 654321;
	public static final int EQUAL = 0;
	public static final int GREAT_EQUAL = 1;
	public static final int LESS_EQUAL = 2;
	public static final int WIN = 10000;
	public static final int LOSE = -10000;
	private int value;
	private int operType;
	
	IntegerEstimation() {
		setValue(NOTSET);
		setOperType(EQUAL);
	}
	
	IntegerEstimation(int val) {
		setValue(val);
		setOperType(EQUAL);
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int getOperType() {
		return operType;
	}

	@Override
	public void setOperType(int operType) {
		this.operType = operType;
	}	

	@Override
	public boolean isEqual(Estimation parentEst) {
		boolean ret = false;
		if ((this.getOperType() == parentEst.getOperType()
				&& (this.getValue() == parentEst.getValue()))) {
			ret = true;
		}
		return ret;
	}

	@Override
	public boolean isGreatThan(Estimation another) {
		boolean ret = false; 
		if (!isConflict(another)) {
			int cValue = another.getValue();
			ret = (value > cValue ? true : false);
		}
		return ret;
	}

	@Override
	public boolean isLessThan(Estimation another) {
		boolean ret = false; 
		if (!isConflict(another)) {
			int cValue = another.getValue();
			ret = (value < cValue ? true : false);
		}
		return ret;
	}

	@Override
	public boolean isConflict(Estimation another) {
		boolean ret = false;
		
		//for example: >=9 VS <=8
		if ((this.getOperType() == GREAT_EQUAL)
				&& (another.getOperType() == LESS_EQUAL)) {
			if (this.getValue() > another.getValue()) {
				ret = true;
			}
		}
		else {
			//for example: <=8 VS >=9
			if ((this.getOperType() == LESS_EQUAL) 
					&& (another.getOperType() == GREAT_EQUAL)) {
				if (this.getValue() < another.getValue()) {
					ret = true;
				}
			}
		}
		return ret;
	}

	@Override
	public boolean isWin() {
		return (getValue() == WIN ? true : false);
	}

	@Override
	public boolean isLose() {
		return (getValue() == LOSE ? true : false);
	}

}
