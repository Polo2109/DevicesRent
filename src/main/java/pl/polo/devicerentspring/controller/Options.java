package pl.polo.devicerentspring.controller;

import pl.polo.devicerentspring.exceptions.InvalidOptionException;

public enum Options {
    ADD_DEVICE(1, "Dodaj urządzenie"),
    ADD_CATEGORY(2, "Dodaj kategorię"),
    ADD_CLIENT(3, "Dodaj klienta"),
    RENT_DEVICE(4, "Wypozycz urządzenie"),
    DELETE_DEVICE(5, "Usuń urządzenie"),
    DELETE_CATEGORY(6, "Usuń kategorię"),
    DELETE_CLIENT(7, "Usuń klienta"),
    END(8, "Koniec");

    private final int option;
    private final String description;

    Options(int option, String description) {
        this.option = option;
        this.description = description;
    }

    public int getOption() {
        return option;
    }

    public String getDescription() {
        return description;
    }

    static Options createFromInt(int option) throws InvalidOptionException{
        try {
            return Options.values()[option - 1];
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidOptionException("Brak opcji o id " + option);
        }
    }
}


