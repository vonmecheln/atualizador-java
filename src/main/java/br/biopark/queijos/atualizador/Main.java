/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.biopark.queijos.atualizador;

import java.util.logging.Level;
import java.util.logging.Logger;
import br.biopark.queijos.atualizador.util.PropFile;
import br.biopark.queijos.atualizador.util.Util;
import br.biopark.queijos.atualizador.enumerator.EPropertie;
import br.biopark.queijos.atualizador.util.GitUtil;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 *
 * @author Renato
 */
public class Main {

    private static Util util = new Util();
    private static PropFile prop = new PropFile();
    private static String versaoAtual = "";
    private static List<String> versoes = new ArrayList();
    private static String novaVersao = "";
    private SystemTray tray;
    private static TrayIcon trayIcon;
    private static Main main;

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }

    public static void main(String[] args) {
        main = new Main();

        try {
            updateUI();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            main.displayTray();
        } catch (AWTException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        int interval = prop.readPropertie(EPropertie.UPDATE_CHECK_FREQUENCY) != null ? Integer.parseInt(prop.readPropertie(EPropertie.UPDATE_CHECK_FREQUENCY)) * 60000 : 60 * 60000;  // intervalo configurado.
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                checarVersao(false);

            }

        }, interval, interval);

    }

    private static void checarVersao(boolean menu) {
        if (!TrayIconController.processandoVersao) {
            versaoAtual = prop.readPropertie(EPropertie.APLICATION_VERSION);
            GitUtil git = new GitUtil();
            versoes = git.buscaVersoes(prop.readPropertie(EPropertie.URL_BASE_GIT));

            if (main.checkNewVersion()) {

                if (menu) {
                    TrayIconController.showVersionFrame(trayIcon, versaoAtual, versoes, novaVersao);
                } else {
                    URL url = main.getClass().getResource("/queijo.png");
                    Image imgQueijo = Toolkit.getDefaultToolkit().getImage(url);
                    main.getTrayIcon().setImage(imgQueijo);
                    main.getTrayIcon().displayMessage("Atualizador Queijos", "Uma nova versão foi encontrada. \nClique aqui para atualizar.", MessageType.INFO);
                    main.getTrayIcon().addActionListener(TrayIconController
                            .showVersao(trayIcon, versaoAtual, versoes, novaVersao));
                }
            }
        }
    }

    private static void updateUI() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.put("ProgressBar.background", Color.gray);
        UIManager.put("ProgressBar.foreground", Color.orange);
        UIManager.put("ProgressBar.selectionBackground", Color.white);
        UIManager.put("ProgressBar.selectionForeground", Color.white);
    }

    private boolean checkNewVersion() {

        int vAtual = Integer.parseInt(versaoAtual.replace(".", ""));

        for (String v : versoes) {

            int vRem = Integer.parseInt(v.replace(".", ""));

            if (vRem > vAtual) {
                novaVersao = v;
                return true;
            }

        }

        return false;
    }

    static class MyProgressUI extends BasicProgressBarUI {

        Rectangle r;

        @Override
        protected void paintIndeterminate(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            if (r == null) {
                r = new Rectangle();
            }

            r = getBox(r);
            g.setColor(progressBar.getForeground());
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        tray = SystemTray.getSystemTray();

        PopupMenu menu = new PopupMenu("Atualizador");
        menu.add("Atualizador Queijos - Versão 1.0.0");
        menu.addSeparator();
        MenuItem quitItem = new MenuItem("Verificar Atualizações");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                checarVersao(true);
            }
        });
        
        menu.add(quitItem);

        //If the icon is a file
        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/queijo.png"));

        trayIcon = new TrayIcon(image, "Atualizador Queijos", menu);
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);

        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Atualizados Queijos");
        tray.add(trayIcon);

        trayIcon.displayMessage("Atualizador Queijos", "Atualizador sendo executado em segundo plano.", MessageType.INFO);
    }

}
