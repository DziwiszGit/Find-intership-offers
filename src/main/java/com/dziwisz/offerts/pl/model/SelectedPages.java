package com.dziwisz.offerts.pl.model;

public class SelectedPages {
    private boolean pracuj = false;
    private boolean linkedin = false;

    public SelectedPages(boolean pracuj, boolean linkedin) {
        this.pracuj = pracuj;
        this.linkedin = linkedin;
    }

    public boolean isPracuj() {
        return pracuj;
    }

    public boolean isLinkedin() {
        return linkedin;
    }


}