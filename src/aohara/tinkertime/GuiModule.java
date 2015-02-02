package aohara.tinkertime;

import java.awt.Dimension;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.JPopupMenu;

import aohara.common.selectorPanel.SelectorPanel;
import aohara.common.workflows.ProgressPanel;
import aohara.tinkertime.controllers.ModLoader;
import aohara.tinkertime.controllers.ModManager;
import aohara.tinkertime.models.Mod;
import aohara.tinkertime.models.ModComparator;
import aohara.tinkertime.views.ModImageView;
import aohara.tinkertime.views.ModListCellRenderer;
import aohara.tinkertime.views.ModView;
import aohara.tinkertime.views.menus.MenuFactory;

@Module(includes = {
    MenuFactory.class,
    TinkerConfig.Module.class
})
class GuiModule {

  @Provides @Named("tinkertime.selector.splitRatio") float splitRatio() {
    return 0.4f;
  }

  @Provides @Named("tinkertime.selector.dimension") Dimension initialDimension() {
    return new Dimension(500, 600);
  }

  @Provides @Singleton SelectorPanel<Mod> modSelectorPanel(
      ModView modView,
      ModComparator comparator,
      @Named("tinkertime.selector.dimension") Dimension initialDemension,
      @Named("tinkertime.selector.splitRatio") float splitRatio,
      ModImageView imageView,
      ModListCellRenderer listCellRenderer,
      JPopupMenu popupMenu) {
    SelectorPanel<Mod> panel =
        new SelectorPanel<Mod>(modView, comparator, initialDemension, splitRatio);
    panel.addControlPanel(true, imageView);
    panel.setListCellRenderer(listCellRenderer);
    panel.addPopupMenu(popupMenu);
    return panel;
  }

  // Provide ProgressPanel so we don't need to JSR-330-ize Common classes.
  @Provides ProgressPanel progressPanel() {
    return new ProgressPanel();
  }

  @Provides ModManager modManager(TinkerConfig config, ModLoader loader, ProgressPanel progressPanel) {
    return ModManager.createDefaultModManager(config, loader, progressPanel);
  }
}
