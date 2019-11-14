/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.biopark.queijos.atualizador.util;

import br.biopark.queijos.atualizador.Progress;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.SwingUtilities;

/**
 *
 * @author Renato
 */
public class Downloader {

    Util util = new Util();
    private long downloadFileSize = 0l;
    
    public Downloader() {
    }
    
    public void download(String urlBase, String versao, String nomeApp) {
        
        Progress janela = Progress.getInstance();
        janela.getJpProgress().setIndeterminate(false);
        janela.repaint();

        try {

            URL url = new URL(urlBase + versao + nomeApp);
            HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
            long completeFileSize = httpConnection.getContentLength();

            java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
            java.io.FileOutputStream fos = new java.io.FileOutputStream(nomeApp);
            java.io.BufferedOutputStream bout = new BufferedOutputStream(
                    fos, 1024);
            byte[] data = new byte[1024];

            janela.getLbStatus().setText("Tamanho do Arquivo " + util.getMB(completeFileSize) + " Mb");
            janela.repaint();
            util.sleep(2000);

            downloadFileSize = 0;
            int x = 0;
            while ((x = in.read(data, 0, 1024)) >= 0) {
                downloadFileSize += x;

                // calcula o progresso do dowload
                final int currentProgress = (int) ((((double) downloadFileSize) / ((double) completeFileSize)) * 100000d);

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        
                        janela.getLbStatus().setText("Baixando " + util.getMB(downloadFileSize) + " de " + util.getMB(completeFileSize) + " ");
                        janela.getJpProgress().setValue(currentProgress / 1000);
                        janela.getJpProgress().setString(currentProgress / 1000 + "%");
                        janela.repaint();
                    }
                });

                bout.write(data, 0, x);
            }
            bout.close();
            in.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }
    
}
