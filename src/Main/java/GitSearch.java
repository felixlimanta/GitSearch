package org.felixlimanta.gitsearch;

import org.felixlimanta.gitsearch.view.GitSearchView;

/**
 * Main application.
 *
 * <p>Create new CliView object for running in terminal or a new GitSearchView object for
 * running with GUI.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-28
 */
public class GitSearch {
  public static void main(String args[]) throws Exception {
    //new CliView();
    new GitSearchView();
  }
}
