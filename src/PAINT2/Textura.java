package PAINT2;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Textura {

    BufferedImage I = null;
    TexturePaint TXT;
    Rectangle2D R;
    boolean T;

    public Textura(boolean T) {

        this.T = T;

    }

    public void NewTexture(int w, int h) {

        R = new Rectangle2D.Double(0, 0, w, h);
        TXT = new TexturePaint(I, R);

    }

}
