package net.oktoberfest.model.client.response;

import java.util.List;

import net.oktoberfest.model.Response;

import net.oktoberfest.model.entities.Tent;

public class ShowTentResponse implements Response {

    public List<Tent> tents;

    public ShowTentResponse(List<Tent> tents) {
        this.tents = tents;
    }

}
