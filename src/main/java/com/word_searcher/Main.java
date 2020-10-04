package com.word_searcher;

import com.word_searcher.service.Executor;


public class Main {

    public static void main(String[] args) {
        Executor executor = Executor.instance();
        executor.execute();
    }
}
