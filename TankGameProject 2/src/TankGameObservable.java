
import java.util.Observable;

public class TankGameObservable extends Observable {
	/**
	 * 
	 */
	public TankGameObservable() {
	}

	/**
	 * 
	 */
	@Override
	protected synchronized void setChanged() {
		super.setChanged();
	}

}
