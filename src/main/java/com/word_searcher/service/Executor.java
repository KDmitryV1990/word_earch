package com.word_searcher.service;

import com.word_searcher.config.Configuration;
import com.word_searcher.exception.DocumentNotFoundException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Executor {

    private static Executor executor;

    private Executor() {
    }

    public static Executor instance() {
        if (executor == null) {
            executor = new Executor();
        }
        return executor;
    }

    private DocumentLoader documentLoader = DocumentLoader.instance();
    private WordCounter wordCounter = WordCounter.instance();
    private String url = Configuration.url;

    public static final Logger LOG = LogManager.getLogger(Executor.class);


    public  void execute(){

        try {
            LOG.info("process started");
            String document = documentLoader.getDocument(url);
            wordCounter.searchWord(document);

        } catch (DocumentNotFoundException e) {
            LOG.error("Error loading document");
            e.printStackTrace();
        }
    }
}
