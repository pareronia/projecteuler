package com.github.pareronia.projecteuler.grid;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface Grid<T> {

    Cell ORIGIN = Cell.at(0, 0);

    T getValue(final Cell cell);

    int getHeight();

    int getWidth();

    default boolean isInBounds(final Cell cell) {
        return 0 <= cell.row()
                && cell.row() < this.getHeight()
                && 0 <= cell.col()
                && cell.col() < this.getWidth();
    }

    default Stream<T> getRowValues(final int row) {
        return new GridIterator<>(this, Cell.at(row, 0), GridIterator.IterDir.RIGHT)
                .stream().map(this::getValue);
    }

    default Stream<T> getColValues(final int col) {
        return new GridIterator<>(this, Cell.at(0, col), GridIterator.IterDir.DOWN)
                .stream().map(this::getValue);
    }

    default Stream<Stream<T>> getAllRowsValues() {
        return IntStream.range(0, this.getHeight()).mapToObj(row -> getRowValues(row));
    }

    default Stream<T> getDiagonalDownValues(final Cell cell) {
        return new GridIterator<>(this, cell, GridIterator.IterDir.RIGHT_AND_DOWN)
                .stream().map(this::getValue);
    }

    default Stream<T> getDiagonalUpValues(final Cell cell) {
        return new GridIterator<>(this, cell, GridIterator.IterDir.RIGHT_AND_UP)
                .stream().map(this::getValue);
    }

    default Stream<Stream<T>> getAllColsValues() {
        return IntStream.range(0, this.getWidth()).mapToObj(col -> getColValues(col));
    }

    default Stream<Stream<T>> getAllDiagsUpValues() {
        return Stream.concat(
                        IntStream.range(0, this.getHeight()).mapToObj(row -> Cell.at(row, 0)),
                        IntStream.range(1, this.getWidth())
                                .mapToObj(col -> Cell.at(this.getHeight() - 1, col)))
                .map(Grid.this::getDiagonalUpValues);
    }

    default Stream<Stream<T>> getAllDiagsDownValues() {
        return Stream.concat(
                        IntStream.range(0, this.getHeight()).mapToObj(row -> Cell.at(row, 0)),
                        IntStream.range(1, this.getWidth()).mapToObj(col -> Cell.at(0, col)))
                .map(Grid.this::getDiagonalDownValues);
    }
}
