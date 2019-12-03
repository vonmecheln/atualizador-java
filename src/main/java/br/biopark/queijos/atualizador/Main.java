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
    
    public void setTrayIcon(TrayIcon trayIcon) {
        Main.trayIcon = trayIcon;
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static void main(String[] args) {
        main = new Main();

        try {
            updateUI();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            TrayIconController.displayTray();
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

    public static void checarVersao(boolean menu) {
        if (!TrayIconController.processandoVersao) {
            versaoAtual = prop.readPropertie(EPropertie.APLICATION_VERSION);
            GitUtil git = new GitUtil();
            versoes = git.buscaVersoes(prop.readPropertie(EPropertie.URL_BASE_GIT));

            if (main.checkNewVersion()) {

                if (menu) {
                    TrayIconController.showVersionFrame(trayIcon, versaoAtual, versoes, novaVersao);
                } else {
                    TrayIconController.notifyVersion(main.getTrayIcon(), versaoAtual, versoes, novaVersao);
                }
            }else{
                URL url = main.getClass().getResource("/queijo.png");
                Image imgQueijo = Toolkit.getDefaultToolkit().getImage(url);
                main.getTrayIcon().setImage(imgQueijo);
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
}