package PAINT2;

import java.awt.*;

public class Linea {

    float ANCHO;
    int LCAP = 0;                                                              //0 -> BEVEL, 1 -> MITER, 2 -> ROUND
    int LJOIN = 0;                                                             //0 -> BUTT, 1 -> ROUND, 2 -> SQUARE
    boolean DASH;
    Color COLOR;

    public Linea(float ANCHO, int LCAP, int LJOIN, boolean DASH, Color COLOR) {

        this.ANCHO = ANCHO;
        this.LCAP= LCAP;
        this.LJOIN = LJOIN;
        this.DASH = DASH;
        this.COLOR = COLOR;

    }

}
