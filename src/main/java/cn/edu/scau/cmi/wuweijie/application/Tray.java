package cn.edu.scau.cmi.wuweijie.application;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import javafx.application.Platform;

public class Tray {

	private static final Log log = LogFactory.getLog(Tray.class);

	private final SystemTray systemTray = SystemTray.getSystemTray();

	private final TrayIcon trayIcon;

	private final PopupMenu popupMenu;

	private final MenuItem showItem;

	private final MenuItem exitItem;

	private static Tray tray;

	private Tray() throws Exception {
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("flowerdance.png"));

		popupMenu = new PopupMenu();
		exitItem = new MenuItem("exit");
		showItem = new MenuItem("show");
		popupMenu.add(showItem);
		popupMenu.add(exitItem);

		exitItem.addActionListener(i -> {
			log.info("exit clicked");
			System.exit(0);
		});

		showItem.addActionListener(i -> {
			log.info("show clicked");
			Platform.runLater(() -> {
				Bootstrap.mainStage.show();
			});
		});

		trayIcon = new TrayIcon(image, "System Tray", popupMenu);

		systemTray.add(trayIcon);
	}

	public static void enableTray() throws Exception {
		if (tray == null) {
			tray = new Tray();
		}
	}

}
