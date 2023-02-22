package org.example.database.sequence;

import org.springframework.stereotype.Component;

@Component
public class BookIdSequence implements IBookIdSequence{

    private int id=0;


    @Override
    public int getId() {
        return ++id;
    }
}
