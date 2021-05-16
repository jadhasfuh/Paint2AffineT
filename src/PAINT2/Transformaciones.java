package PAINT2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Transformaciones {

    GeneralPath P;
    Shape S, A, M;
    AffineTransform at;
    GradientPaint gp;
    Color CO1, CO2;
    int JOIN, CAP;
    Linea L;
    Gradiente G;
    Textura T;

    int C[] = {
            228, 198, 214, 197, 210, 188, 218, 184, 232, 181, 230, 172, 235, 170, 244, 179, 251, 181, 312, 177, 321, 169,
            328, 161, 337, 161, 344, 158, 341, 155, 348, 149, 357, 151, 358, 138, 365, 133, 374, 135, 375, 146, 386, 149,
            392, 151, 398, 151, 401, 170, 406, 179, 405, 185, 399, 193, 400, 202, 398, 205, 395, 213, 392, 221, 388, 223,
            388, 233, 396, 234, 400, 244, 395, 253, 385, 294, 391, 298, 389, 308, 399, 347, 403, 348, 402, 356, 406, 366,
            396, 377, 387, 377, 393, 363, 395, 353, 380, 313, 376, 310, 388, 244, 379, 244, 371, 244, 350, 275, 344, 280,
            347, 286, 347, 289, 358, 306, 366, 312, 363, 321, 342, 330, 333, 327, 339, 321, 350, 319, 356, 313, 340, 295,
            333, 283, 338, 279, 362, 242, 363, 233, 372, 228, 366, 222, 366, 208, 349, 202, 341, 214, 340, 222, 333, 222,
            333, 214, 337, 197, 328, 188, 242, 197, 238, 202
    };

    public Transformaciones() {

        P = new GeneralPath();
        P.moveTo(C[0], C[1]);
        for (int i = 0; i < C.length; i += 2) P.lineTo(C[i], C[i + 1]);
        P.closePath();
        Restaurar();
        A = Traslacion(100, 0, S);

    }

    public void DibujaPuntos(Graphics g, Shape F) {

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(L.COLOR);
        if (L.LJOIN == 0) {
            JOIN = BasicStroke.JOIN_BEVEL;
            CAP = BasicStroke.CAP_BUTT;
        } else if (L.LJOIN == 1) {
            JOIN = BasicStroke.JOIN_MITER;
            CAP = BasicStroke.CAP_SQUARE;
        } else {
            JOIN = BasicStroke.JOIN_ROUND;
            CAP = BasicStroke.CAP_ROUND;
        }
        if (L.DASH) g2D.setStroke(new BasicStroke(L.ANCHO, CAP, JOIN, 0, new float[]{15}, 0));
        else g2D.setStroke(new BasicStroke(L.ANCHO, CAP, JOIN));
        g2D.draw(F);
        if (!G.V) gp = new GradientPaint(
                (int) F.getBounds2D().getMinX(), (int) F.getBounds2D().getCenterY(), G.C1,
                (int) F.getBounds2D().getMaxX(), (int) F.getBounds2D().getCenterY(), G.C2
        );
        else gp = new GradientPaint(
                (int) F.getBounds2D().getCenterX(), (int) F.getBounds2D().getMinY(), G.C1,
                (int) F.getBounds2D().getCenterX(), (int) F.getBounds2D().getMaxY(), G.C2
        );
        if (!G.T) {
            if (G.G) g2D.setPaint(gp);
            else g2D.setPaint(G.C1);
            g2D.fill(F);
        }
        if (!T.T) {
            g2D.setPaint(T.TXT);
            g2D.fill(F);
        }

    }

    public Shape Escalar(double esc, Shape F) {

        at = new AffineTransform();
        at.translate(F.getBounds2D().getCenterX(), F.getBounds2D().getCenterY());
        at.scale(esc, esc);
        at.translate(-F.getBounds2D().getCenterX(), -F.getBounds2D().getCenterY());
        return at.createTransformedShape(F);

    }

    public Shape Deformar(double dex, double dey, Shape F) {

        at = new AffineTransform();
        at.translate(F.getBounds2D().getCenterX(), F.getBounds2D().getCenterY());
        at.shear(dex, dey);
        at.translate(-F.getBounds2D().getCenterX(), -F.getBounds2D().getCenterY());
        return at.createTransformedShape(F);

    }

    public void Restaurar() {
        S = P;
        CO1 = Color.DARK_GRAY;
        CO2 = Color.BLACK;
        L = new Linea(5, 0, 0, false, Color.BLACK);
        G = new Gradiente(Color.BLACK, Color.GRAY, false, true, false);
        T = new Textura(true);
        JOIN = BasicStroke.JOIN_BEVEL;
        CAP = BasicStroke.CAP_BUTT;
    }

    public Shape Rotacion(int grados, Shape F) {

        at = new AffineTransform();
        double radianes = Math.toRadians(grados);
        at.setToRotation(radianes, F.getBounds2D().getCenterX(), F.getBounds2D().getCenterY());
        return at.createTransformedShape(F);

    }

    public Shape Traslacion(int tx, int ty, Shape F) {

        at = new AffineTransform();
        at.setToTranslation(tx, ty);
        return at.createTransformedShape(F);

    }

    public Shape Reflexion(double rx, double ry, Shape F) {

        at = new AffineTransform();
        at.translate(F.getBounds2D().getCenterX(), F.getBounds2D().getCenterY());
        at.scale(rx, ry);
        at.translate(-F.getBounds2D().getCenterX(), -F.getBounds2D().getCenterY());
        return at.createTransformedShape(F);

    }

}

