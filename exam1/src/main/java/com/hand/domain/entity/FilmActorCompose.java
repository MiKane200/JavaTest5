package com.hand.domain.entity;

import java.util.List;

public class FilmActorCompose extends Film {
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    private List<Actor> actors;
}
