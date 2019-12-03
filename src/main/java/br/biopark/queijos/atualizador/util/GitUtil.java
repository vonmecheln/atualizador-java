/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.biopark.queijos.atualizador.util;

import br.biopark.queijos.atualizador.Progress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

/**
 *
 * @author Renato
 */
public class GitUtil {

    Util util = new Util();

    public GitUtil() {
    }

    public List<String> buscaVersoes(String urlBase) {
        try {

            final Map<String, Ref> map = Git.lsRemoteRepository()
                    .setHeads(false)
                    .setTags(true)
                    .setRemote(urlBase + ".git")
                    .callAsMap();

            return readVersions(map);

        } catch (GitAPIException ex) {
            Logger.getLogger(GitUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private List<String> readVersions(Map<String, Ref> map) {

        List<String> versoes = new ArrayList<>();

        util.sleep(5000);

        for (Map.Entry<String, Ref> entry : map.entrySet()) {
            if (entry.getKey().contains("refs/tags/v")) {

                String versao = entry.getKey().replace("refs/tags/v", "");
                versoes.add(versao);

            }

        }
        
        Collections.sort(versoes);
        
        return versoes;
    }

}