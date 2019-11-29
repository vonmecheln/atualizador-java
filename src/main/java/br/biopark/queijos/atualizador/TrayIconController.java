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

    public static ActionListener showVersao(TrayIcon trayIcon, String vAtual, List<String> versoes, String novaVersao) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Versao versao = new Versao(Progress.getInstance(), true, vAtual, versoes, trayIcon);
                trayIcon.addActionListener(null);
                versao.setLocationRelativeTo(null);
                versao.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
                versao.getjLabel1().setText("Nova versão " + novaVersao);
                versao.setVisible(true);
            }
        };
    }
}
