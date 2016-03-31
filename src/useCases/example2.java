package useCases;

import interfaces.IGameLoader;

public class example2 {

	IGameLoader myExample;

	public example2() {
		// TODO Auto-generated constructor stub
		myExample = new GameLoader();

	}

	public void saveExistingFile() {
		myExample.save();
	}

}
