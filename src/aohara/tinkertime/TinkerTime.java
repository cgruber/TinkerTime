package aohara.tinkertime;

import javax.inject.Singleton;


/**
 * Main Class for Tinker Time
 *
 * @author Andrew O'Hara
 */
public class TinkerTime {

	public static final String
		NAME = "Tinker Time",
		VERSION = "1.1",
		AUTHOR = "Andrew O'Hara";

	@Singleton
	@dagger.Component(modules = { ServicesModule.class, GuiModule.class })
	interface Component {
	  TinkerTimeApplication application();
	}

	public static void main(String[] args) {
    // Set HTTP User-agent
    System.setProperty("http.agent", "TinkerTime Bot");

	  Component c = Dagger_TinkerTime_Component.create();
	  TinkerTimeApplication application = c.application();
	  application.initialize();
	  application.startTasks();
	  application.showGui();
	}

	private TinkerTime() {}
}
