package org.LLD1.models;


import java.util.Objects;


public class Symbol {
    private final char symbol;
    private final String color;

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return symbol == symbol1.symbol && Objects.equals(color, symbol1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, color);
    }

    public Symbol(char symbol, String color) {
        this.symbol = symbol;
        this.color = color;
    }
    public char getSymbol() {
        return symbol;
    }

}
