package org.lbg.c4.board;

import org.lbg.c4.entities.IEntity;

import java.awt.*;

public class Tile {


    private IEntity entity;

    private final Point location;

    public Tile(Point location, IEntity entity) {
        this.location = location;
        this.entity = entity;
    }

    public Point getLocation() {
        return location;
    }

    public IEntity getEntity() {
        return entity;
    }

    public void setEntity(IEntity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return entity == null ? "  " : entity.toString();
    }

}
