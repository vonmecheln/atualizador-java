package br.biopark.queijos.atualizador;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.JRootPane;

/**
 *
 * @author Lucas Oliveira
 */
public class TrayIconController {

    private static SystemTray tray;
    public static boolean processandoVersao = false;

    public static ActionListener showVersao(TrayIcon trayIcon, String vAtual, List<String> versoes, String novaVersao) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showVersionFrame(trayIcon, vAtual, versoes, novaVersao);
            }
        };
    }

    public static void showVersionFrame(TrayIcon trayIcon, String vAtual, List<String> versoes, String novaVersao) {

        Versao versao = new Versao(Progress.getInstance(), true, vAtual, versoes, trayIcon);
        trayIcon.addActionListener(null);
        versao.setLocationRelativeTo(null);
        versao.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        versao.getjLabel1().setText("Nova versão " + novaVersao);
        processandoVersao = true;
        versao.setVisible(true);

    }

    public static void notifyVersion(TrayIcon tIcon, String vAtual, List<String> versoes, String vNova) {
        URL url = TrayIconController.class.getClass().getResource("/queijo-alert.png");
        Image imgQueijo = Toolkit.getDefaultToolkit().getImage(url);
        tIcon.setImage(imgQueijo);
        tIcon.displayMessage("Atualizador Queijos", "Uma nova versão foi encontrada. \nClique aqui para atualizar.", TrayIcon.MessageType.INFO);
        tIcon.addActionListener(TrayIconController
                .showVersao(tIcon, vAtual, versoes, vNova));
    }
    
    private static ActionListener quitItem_onClick() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Main.checarVersao(true);
            }
        };
    }
    
    public static void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        TrayIconController.tray = SystemTray.getSystemTray();

        PopupMenu menu = new PopupMenu("Atualizador");
        menu.add("Atualizador Queijos - Versão 1.0.0");
        menu.addSeparator();
        MenuItem quitItem = new MenuItem("Verificar Atualizações");
        quitItem.addActionListener(quitItem_onClick());
        
        menu.add(quitItem);

        //If the icon is a file
        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        Image image = Toolkit.getDefaultToolkit()
                .createImage(TrayIconController.class.getClass().getResource("/queijo.png"));

        Main.getMain().setTrayIcon(new TrayIcon(image, "Atualizador Queijos", menu));
        //Let the system resize the image if needed
        Main.getMain().getTrayIcon().setImageAutoSize(true);

        //Set tooltip text for the tray icon
        Main.getMain().getTrayIcon().setToolTip("Atualizador Queijos");
        tray.add(Main.getMain().getTrayIcon());

        Main.getMain().getTrayIcon().displayMessage("Atualizador Queijos", "Atualizador sendo executado em segundo plano.", TrayIcon.MessageType.INFO);
    }
}
