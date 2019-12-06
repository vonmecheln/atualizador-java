/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifprbiopark.atualizador_queijo_desktop.enumerator;

/**
 *
 * @author Renato
 */
public enum EPropertie {
    
    URL_BASE_GIT            ("git.url"),
    LOCAL_DESTINO           ("down.destino"),
    DATABASE_CONNECTOR      ("db.connector"),
    DATABASE_URL            ("db.url"),
    DATABASE_PASSWORD       ("db.password"),
    DATABASE_USER           ("db.user"),
    DATABASE_SCRIPT_LOCATION("db.scriptSource"),
    DATABASE_DATASCRIPT_LOCATION("db.dataScriptSource"),
    APLICATION_NAME         ("app.name"),
    APLICATION_VERSION      ("app.version"),
    UPDATE_CHECK_FREQUENCY  ("update.frequency"),
    LOCAL_ORIGEM            ("down.origem"),
    ARQUIVO_ZIP             ("zip.file");
    
    private String descricao;
 
    EPropertie(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
}