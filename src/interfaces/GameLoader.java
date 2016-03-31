package interfaces;

public class GameLoader implements IGameLoader {

	IData myData;
	public GameLoader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		myData.getData();
		//save to xml w/xstream		

	}

}
