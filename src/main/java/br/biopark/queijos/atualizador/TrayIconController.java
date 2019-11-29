package br.biopark.queijos.atualizador;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JRootPane;

/**
 *
 * @author Lucas Oliveira
 */
public class TrayIconController {

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
}
