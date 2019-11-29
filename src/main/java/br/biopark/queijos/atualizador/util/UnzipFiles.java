/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.biopark.queijos.atualizador.util;

import br.biopark.queijos.atualizador.enumerator.EPropertie;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FileUtils;

public class UnzipFiles {

    public UnzipFiles() {
    }

    public void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);

                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            Logger.getLogger(UnzipFiles.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void deleteDownloadedContent(String zipFilePath, String destDir) {

        File zip = new File(zipFilePath);
        zip.delete();

        File dir = new File(destDir);
        deleteDir(dir);
    }

    public void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

    public void copiar(String origem, String destino) {
        destino = destino.replace("\"", "");

        try {
            
            PropFile prop = new PropFile();
            String app = prop.readPropertie(EPropertie.APLICATION_NAME);
            FileUtils.copyFile(new File(origem), new File(destino));
//            FileUtils.copyFile(new File(prop.readPropertie(EPropertie.LOCAL_ORIGEM) +
//                                        "/" + app),
//                    new File(prop.readPropertie(EPropertie.LOCAL_DESTINO) +
//                                        "/" + app));
        
        } catch (IOException ex) {
            Logger.getLogger(UnzipFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public String createDir(String destDir){
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }
}
