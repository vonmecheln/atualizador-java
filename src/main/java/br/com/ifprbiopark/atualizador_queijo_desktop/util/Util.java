/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifprbiopark.atualizador_queijo_desktop.util;

import br.com.ifprbiopark.atualizador_queijo_desktop.Progress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Renato
 */
public class Util {

    public Util() {
    }
    
    public void sleep(int time) {
        try {
            Thread.sleep(time == 0 ? 500 : time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getMB(long tam) {
        double tamanho = Double.parseDouble(String.valueOf(tam));
        tamanho = (tamanho / 1024) / 1024;

        return round(tamanho, 2);
    }

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
}