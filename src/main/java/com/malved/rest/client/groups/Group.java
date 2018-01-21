package com.malved.rest.client.groups;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Group {

    private final Long id;
    private final String type;
    private final String link;
    private final String created;
    private final String modified;
    private final String name;

    public Group(String name){
        this(null, null, null, null, null, name);
    }
}
