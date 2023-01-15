package org.example.sequence;

import org.springframework.stereotype.Component;

@Component
public class UserIdSequence implements IIdSequence {
    private int id = 0;

    @Override
    public int getId() {
        return ++id;
    }
}
