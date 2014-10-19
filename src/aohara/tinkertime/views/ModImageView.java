package aohara.tinkertime.views;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import aohara.common.content.ImageManager;
import aohara.common.selectorPanel.ControlPanel;
import aohara.tinkertime.Config;
import aohara.tinkertime.models.Mod;

/**
 * Component which displays the Mod's image from a given URL.
 *
 * @author Andrew O'Hara
 */
public class ModImageView extends ControlPanel<Mod> {
	
	private final ImageManager imageManager = new ImageManager();
	private final JLabel label = new JLabel();
	private final Config config;
	
	public ModImageView(Config config){
		this.config = config;
		panel.add(label);
	}
	
	@Override
	public void display(Mod element){
		BufferedImage image = null;
		if (element != null){
			try {
				super.display(element);
				image = imageManager.getImage(element.getCachedImagePath(config));
			} catch(IOException ex){
				// Do Nothing
			}
			if (image != null){
				Dimension size = imageManager.scaleToFit(image, new Dimension(panel.getWidth(), panel.getWidth()));
				try{
					image = imageManager.resizeImage(image, size);
				} catch (IllegalArgumentException e){
					
				}
			}
		}
		label.setIcon(image != null ? new ImageIcon(image) : null);
	}
}
