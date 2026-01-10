package com.github.pareronia.projecteuler.geometry;

public record Position(int x, int y) {

    public static Position ORIGIN = Position.of(0, 0);

    public static Position of(final int x, final int y) {
        return new Position(x, y);
    }

    public long distanceSquaredTo(final Position other) {
        return (long) (this.x - other.x) * (long) (this.x - other.x)
                + (long) (this.y - other.y) * (long) (this.y - other.y);
    }
}
