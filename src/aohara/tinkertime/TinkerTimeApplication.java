package aohara.tinkertime;

import aohara.common.selectorPanel.SelectorPanel;
import aohara.common.workflows.ProgressPanel;
import aohara.tinkertime.controllers.ModLoader;
import aohara.tinkertime.controllers.ModManager;
import aohara.tinkertime.models.Mod;
import aohara.tinkertime.views.TinkerFrame;
import aohara.tinkertime.views.menus.MenuFactory;
import java.awt.BorderLayout;
import javax.inject.Inject;
import javax.swing.JFrame;

/**
 * Main Class for Tinker Time
 *
 * @author Andrew O'Hara
 */
public class TinkerTimeApplication {

  private final ModManager modManager;
  private final ModLoader modLoader;
  private final ProgressPanel progressPanel;
  private final SelectorPanel<Mod> selectorPanel;

	@Inject TinkerTimeApplication(
	    SelectorPanel<Mod> selectorPanel,
	    ModLoader modLoader,
	    ModManager modManager,
	    ProgressPanel progressPanel) {
	  this.selectorPanel = selectorPanel;
    this.modLoader = modLoader;
	  this.modManager = modManager;
	  this.progressPanel = progressPanel;
	}

  public void initialize() {
    selectorPanel.addListener(modManager);
    modLoader.addListener(selectorPanel);
  }

  public void startTasks() {
    modLoader.init(modManager);  // Load mods (will notify selector panel)
    modManager.start();
  }

  public void showGui() {
    JFrame frame = new TinkerFrame();
    frame.setJMenuBar(MenuFactory.createMenuBar(modManager));
    frame.add(MenuFactory.createToolBar(modManager), BorderLayout.NORTH);
    frame.add(selectorPanel.getComponent(), BorderLayout.CENTER);
    frame.add(progressPanel.getComponent(), BorderLayout.SOUTH);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
