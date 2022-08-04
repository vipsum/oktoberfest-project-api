package net.oktoberfest.model.client.request;

import lombok.Data;


@Data
public class ShowTentRequest {
    private int personId;

    public int construct() {
        return personId;
    }
}
