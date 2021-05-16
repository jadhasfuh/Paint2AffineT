package PAINT2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Main extends JPanel implements ActionListener, MouseWheelListener, MouseListener, MouseMotionListener {

    JFrame v;
    Image cuadricula;                                                         //CUADRICULA FONDO
    URL ruta;
    JTabbedPane menues;                                                       //PANEL DE PESTANAS
    Transformaciones T;
    JPanel workspace;
    JButton rotarD, rotarI, escalar, mover, flip, defo, como, info, acerd, rd5, ri5, ess, esr, flx, fly, restore, color, textura, linea;
    Border borde;
    boolean mov = false;

    public Main() {

        v = new JFrame("Paint 2");
        v.setSize(1000, 720);
        setSize(1000, 720);
        v.setResizable(false);
        v.setLocationRelativeTo(this);

        v.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/PAINT2/rec/flip.png")));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException classNotFoundException) {
            } catch (InstantiationException instantiationException) {
            } catch (IllegalAccessException illegalAccessException) {
            } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
            }
        }

        T = new Transformaciones();
        ruta = getClass().getResource("/PAINT2/rec/cuadricula.png");
        cuadricula = new ImageIcon(ruta).getImage();
        topMenu();
        workSpace();
        v.add(this);
        v.setVisible(true);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Main();
    }

    public JButton JBoton(URL ruto, int x, int y, int w, int h, String TT) {

        JButton b = new JButton();
        b.setIcon(new ImageIcon(ruto));
        b.setBounds(x, y, w, h);
        b.setToolTipText(TT);
        b.addActionListener(this);
        return b;

    }

    public void topMenu() {

        menues = new JTabbedPane(JTabbedPane.TOP);
        menues.setBounds(0, 0, getWidth(), 100);
        menues.setBackground(Color.GRAY);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);

        borde = BorderFactory.createTitledBorder("Herramientas");
        JPanel Herramientas = new JPanel();
        Herramientas.setLayout(null);
        Herramientas.setBorder(borde);
        Herramientas.setBounds(0, 0, 310, 70);

        borde = BorderFactory.createTitledBorder("Atajos");
        JToolBar barraH = new JToolBar("Atajos", JToolBar.HORIZONTAL);
        barraH.setFloatable(false);
        barraH.setBorder(borde);
        barraH.setBounds(330, 0, 290, 70);
        menues.addTab("Inicio", null, panel1, null);

        //BOTONES PARA SECCION DE HERRAMIENTAS
        ruta = getClass().getResource("/PAINT2/rec/rotd.png");
        Action A1 = new AbstractAction("", new ImageIcon(ruta)) {
            public void actionPerformed(ActionEvent e) {
                T.S = T.Rotacion(5, T.S);
                v.repaint();
            }
        };
        A1.putValue(Action.SHORT_DESCRIPTION, "Rota 5 grados a la derecha CTRL+D");
        rd5 = new JButton(A1);
        barraH.add(rd5);
        KeyStroke KS10 = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK);
        rd5.getActionMap().put("", A1);
        rd5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS10, "");
        rotarD = JBoton(ruta, 10, 20, 40, 40, "Girar a la derecha ALT+R");
        Herramientas.add(rotarD);

        ruta = getClass().getResource("/PAINT2/rec/roti.png");
        Action A2 = new AbstractAction("", new ImageIcon(ruta)) {
            public void actionPerformed(ActionEvent e) {
                T.S = T.Rotacion(-5, T.S);
                v.repaint();
            }
        };
        A2.putValue(Action.SHORT_DESCRIPTION, "Rota 5 grados a la izquierda CTRL+I");
        ri5 = new JButton(A2);
        barraH.add(ri5);
        KeyStroke KS11 = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
        ri5.getActionMap().put("", A2);
        ri5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS11, "");
        rotarI = JBoton(ruta, 60, 20, 40, 40, "Girar a la izquierda ALT+L");
        Herramientas.add(rotarI);

        ruta = getClass().getResource("/PAINT2/rec/scale.png");
        Action A3 = new AbstractAction("", new ImageIcon(ruta)) {
            public void actionPerformed(ActionEvent e) {
                T.S = T.Escalar(1.1, T.S);
                v.repaint();
            }
        };
        A3.putValue(Action.SHORT_DESCRIPTION, "Aumentar Tamano CTRL+M");
        ess = new JButton(A3);
        barraH.add(ess);
        KeyStroke KS12 = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK);
        ess.getActionMap().put("", A3);
        ess.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS12, "");
        escalar = JBoton(ruta, 110, 20, 40, 40, "Escalar ALT+S");
        Herramientas.add(escalar);

        ruta = getClass().getResource("/PAINT2/rec/contraer.png");
        Action A4 = new AbstractAction("", new ImageIcon(ruta)) {
            public void actionPerformed(ActionEvent e) {
                T.S = T.Escalar(.9, T.S);
                v.repaint();
            }
        };
        A4.putValue(Action.SHORT_DESCRIPTION, "Reducir Tama\u00F1o CTRL+N");
        esr = new JButton(A4);
        barraH.add(esr);
        KeyStroke KS13 = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        esr.getActionMap().put("", A4);
        esr.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS13, "");

        ruta = getClass().getResource("/PAINT2/rec/move.png");
        mover = JBoton(ruta, 160, 20, 40, 40, "Mover ALT+M");
        Herramientas.add(mover);
        ruta = getClass().getResource("/PAINT2/rec/flip.png");
        Action A5 = new AbstractAction("", new ImageIcon(ruta)) {
            public void actionPerformed(ActionEvent e) {
                T.S = T.Reflexion(-1, 1, T.S);
                v.repaint();
            }
        };
        A5.putValue(Action.SHORT_DESCRIPTION, "Flip X CTRL+X");
        flx = new JButton(A5);
        barraH.add(flx);
        KeyStroke KS14 = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        flx.getActionMap().put("", A5);
        flx.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS14, "");
        flip = JBoton(ruta, 210, 20, 40, 40, "Reflexion ALT+F");
        Herramientas.add(flip);

        ruta = getClass().getResource("/PAINT2/rec/defo.png");
        defo = JBoton(ruta, 260, 20, 40, 40, "Deformar ALT+D");
        Herramientas.add(defo);
        panel1.add(Herramientas);

        ruta = getClass().getResource("/PAINT2/rec/flipv.png");
        Action A6 = new AbstractAction("", new ImageIcon(ruta)) {
            public void actionPerformed(ActionEvent e) {
                T.S = T.Reflexion(1, -1, T.S);
                v.repaint();
            }
        };
        A6.putValue(Action.SHORT_DESCRIPTION, "Flip Y CTRL+Y");
        fly = new JButton(A6);
        barraH.add(fly);
        KeyStroke KS15 = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);
        fly.getActionMap().put("", A6);
        fly.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS15, "");

        ruta = getClass().getResource("/PAINT2/rec/restore.png");
        Action A7 = new AbstractAction("", new ImageIcon(ruta)) {
            public void actionPerformed(ActionEvent e) {
                T.Restaurar();
                v.repaint();
            }
        };
        A7.putValue(Action.SHORT_DESCRIPTION, "Restaurar figura CTRL+R");
        restore = new JButton(A7);
        barraH.add(restore);
        KeyStroke KS16 = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        restore.getActionMap().put("", A7);
        restore.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS16, "");

        //AQUI ANADIMOS EL PANEL DE HERRAMIENTAS
        panel1.add(barraH);

        // SECCION DE INFORMACION
        borde = BorderFactory.createTitledBorder("Info");
        JPanel Informacion = new JPanel();
        Informacion.setLayout(null);
        Informacion.setBorder(borde);
        Informacion.setBounds(getWidth() - 178, 0, 160, 70);

        //INFO ANIMACIONES
        Action tutoA = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                Animaciones anim = new Animaciones(Main.this, true);
                anim.Mostrar();
            }
        };
        como = new JButton(tutoA);
        ruta = getClass().getResource("/PAINT2/rec/como.png");
        como.setIcon(new ImageIcon(ruta));
        como.setBounds(10, 20, 40, 40);
        como.setToolTipText("EJEMPLOS ALT + I");
        como.addActionListener(this);
        Informacion.add(como);
        KeyStroke KS = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK);
        como.getActionMap().put("t", tutoA);
        como.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS, "t");

        //INFO TUTORIAL
        Action inf = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Info info = new Info(Main.this, true);
                info.Mostrar();
            }
        };
        info = new JButton(inf);
        ruta = getClass().getResource("/PAINT2/rec/info.png");
        info.setIcon(new ImageIcon(ruta));
        info.setBounds(60, 20, 40, 40);
        info.setToolTipText("Info F1");
        info.addActionListener(this);
        Informacion.add(info);
        KeyStroke KS2 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        info.getActionMap().put("", inf);
        info.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS2, "");

        //ACERCA DE
        Action acd = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                AcercaDe acd = new AcercaDe(Main.this, true);
                acd.Mostrar();
            }
        };
        acerd = new JButton(acd);
        ruta = getClass().getResource("/PAINT2/rec/autor.png");
        acerd.setIcon(new ImageIcon(ruta));
        acerd.setBounds(110, 20, 40, 40);
        acerd.setToolTipText("ACERCA DE ALT+P");
        acerd.addActionListener(this);
        Informacion.add(acerd);
        KeyStroke KS3 = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK);
        acerd.getActionMap().put("", acd);
        acerd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS3, "");

        //AGEGAMOS A PANEL
        panel1.add(Informacion);
        v.add(menues);

        //SECCION DE GRAFICOS
        borde = BorderFactory.createTitledBorder("Gr\u00E1ficos");
        JPanel Graficos = new JPanel();
        Graficos.setLayout(null);
        Graficos.setBorder(borde);
        Graficos.setBounds(638, 0, 160, 70);
        //BOTON COLORES
        Action gra = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                CDialogColores lin = new CDialogColores(Main.this, true, T.G);
                lin.Mostrar();
            }
        };
        ruta = getClass().getResource("/PAINT2/rec/color.png");
        color = new JButton();
        color.setIcon(new ImageIcon(ruta));
        color.setBounds(10, 20, 40, 40);
        color.addActionListener(this);
        color.setToolTipText("RELLENOS ALT+G");
        KeyStroke L5 = KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_DOWN_MASK);
        color.getActionMap().put("", gra);
        color.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(L5, "");
        Graficos.add(color);
        //BOTON TEXTURA
        Action tes = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                CDialogTextura lin = new CDialogTextura(Main.this, true, T.T);
                lin.Mostrar();
            }
        };
        ruta = getClass().getResource("/PAINT2/rec/textura.png");
        textura = new JButton();
        textura.setIcon(new ImageIcon(ruta));
        textura.setBounds(60, 20, 40, 40);
        textura.addActionListener(this);
        textura.setToolTipText("ACERCA DE ALT+T");
        KeyStroke L6 = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_DOWN_MASK);
        textura.getActionMap().put("", tes);
        textura.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(L6, "");
        Graficos.add(textura);
        //BOTON LINEA
        Action col = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                CDialogLinea lin = new CDialogLinea(Main.this, true, T.L);
                lin.Mostrar();
            }
        };
        ruta = getClass().getResource("/PAINT2/rec/line.png");
        linea = new JButton();
        linea.setIcon(new ImageIcon(ruta));
        linea.setBounds(110, 20, 40, 40);
        linea.addActionListener(this);
        linea.setToolTipText("PROPIEDADES DE LINEA ALT+O");
        KeyStroke L4 = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK);
        linea.getActionMap().put("", col);
        linea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(L4, "");
        Graficos.add(linea);
        panel1.add(Graficos);

        ///MAS ACCIONES
        Action RotI = new AbstractAction("RI") {
            public void actionPerformed(ActionEvent e) {
                double cantR[] = new CDialogRot(Main.this, true, 2).mostrar();
                if (cantR != null) {
                    T.S = T.Rotacion(((int) cantR[1]) * -1, T.S);
                    v.repaint();
                }
            }
        };
        KeyStroke KS4 = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK);
        rotarI.getActionMap().put("RI", RotI);
        rotarI.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS4, "RI");

        Action RotD = new AbstractAction("RD") {
            public void actionPerformed(ActionEvent e) {
                double cantR[] = new CDialogRot(Main.this, true, 1).mostrar();
                if (cantR != null) {
                    T.S = T.Rotacion((int) cantR[1], T.S);
                    v.repaint();
                }
            }
        };
        KeyStroke KS5 = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK);
        rotarD.getActionMap().put("RD", RotD);
        rotarD.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS5, "RD");

        //ESCALAR
        Action Esca = new AbstractAction("Esca") {
            public void actionPerformed(ActionEvent e) {
                //ESCALAR
                double esc = new CDialogScale(Main.this, true).mostrar();
                T.S = T.Escalar(esc, T.S);
                v.repaint();
            }
        };
        KeyStroke KS6 = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK);
        escalar.getActionMap().put("Esca", Esca);
        escalar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS6, "Esca");

        Action Mov = new AbstractAction("Mov") {
            public void actionPerformed(ActionEvent e) {
                //MOVER
                double posN[] = new CDialogMovDef(Main.this, true, 2).mostrar();
                if (posN != null) {
                    T.S = T.Traslacion((int) posN[0], (int) posN[1], T.S);
                    v.repaint();
                }
            }
        };
        KeyStroke KS7 = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK);
        mover.getActionMap().put("Mov", Mov);
        mover.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS7, "Mov");

        Action Flip = new AbstractAction("Flip") {
            public void actionPerformed(ActionEvent e) {
                int tipo = new CDialogFlip(Main.this, true).mostrar();
                if (tipo == 0) T.S = T.Reflexion(-1, 1, T.S);
                else if (tipo == 1) T.S = T.Reflexion(1, -1, T.S);
                else if (tipo == 2) T.S = T.Reflexion(-1, -1, T.S);
                v.repaint();
            }
        };
        KeyStroke KS8 = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK);
        rotarD.getActionMap().put("Flip", Flip);
        rotarD.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS8, "Flip");

        Action Defo = new AbstractAction("Def") {
            public void actionPerformed(ActionEvent e) {
                double cantN[] = new CDialogMovDef(Main.this, true, 1).mostrar();
                if (cantN != null) T.S = T.Deformar(cantN[0], cantN[1], T.S);
                v.repaint();
            }
        };
        KeyStroke KS9 = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK);
        rotarD.getActionMap().put("Def", Defo);
        rotarD.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS9, "Def");
    }

    public void workSpace() {
        workspace = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(cuadricula, 0, 0, getWidth(), getHeight(), this);
                T.DibujaPuntos(g, T.S);
            }
        };
        workspace.setBounds(0, 101, getWidth(), getHeight() - 100);
        workspace.addMouseListener(this);
        workspace.addMouseMotionListener(this);
        workspace.addMouseWheelListener(this);
        v.add(workspace);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rotarD) {
            double cantR[] = new CDialogRot(Main.this, true, 1).mostrar();
            if (cantR != null) {
                T.S = T.Rotacion((int) cantR[1], T.S);
            }
            v.repaint();
        } else if (e.getSource() == rotarI) {
            double cantR[] = new CDialogRot(Main.this, true, 2).mostrar();
            if (cantR != null) {
                T.S = T.Rotacion(((int) cantR[1]) * -1, T.S);
                v.repaint();
            }
        } else if (e.getSource() == escalar) {
            double esc = new CDialogScale(Main.this, true).mostrar();
            T.S = T.Escalar(esc, T.S);
            v.repaint();
        } else if (e.getSource() == flip) {
            int tipo = new CDialogFlip(Main.this, true).mostrar();
            if (tipo == 0) T.S = T.Reflexion(-1, 1, T.S);
            else if (tipo == 1) T.S = T.Reflexion(1, -1, T.S);
            else if (tipo == 2) T.S = T.Reflexion(-1, -1, T.S);
            v.repaint();
        } else if (e.getSource() == defo) {
            double cantN[] = new CDialogMovDef(Main.this, true, 1).mostrar();
            if (cantN != null) T.S = T.Deformar(cantN[0], cantN[1], T.S);
            v.repaint();
        } else if (e.getSource() == mover) {
            double posN[] = new CDialogMovDef(Main.this, true, 2).mostrar();
            if (posN != null) T.S = T.Traslacion((int) posN[0], (int) posN[1], T.S);
            v.repaint();
        } else if (e.getSource() == linea) {
            CDialogLinea lin = new CDialogLinea(Main.this, true, T.L);
            lin.Mostrar();
            v.repaint();
        } else if (e.getSource() == color) {
            CDialogColores lin = new CDialogColores(Main.this, true, T.G);
            lin.Mostrar();
            v.repaint();
        } else if (e.getSource() == textura) {
            CDialogTextura lin = new CDialogTextura(Main.this, true, T.T);
            lin.Mostrar();
            v.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mov) {
            int cx = e.getX(), cy = e.getY();
            int tx = cx - (int) T.S.getBounds2D().getCenterX();
            int ty = cy - (int) T.S.getBounds2D().getCenterY();
            T.S = T.Traslacion(tx, ty, T.S);
            v.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int cy = e.getY();
        int cx = e.getX();
        if (T.S.contains(cx, cy)) mov = true;
        else mov = false;
        if (cx >= T.S.getBounds2D().getMaxX() && e.getClickCount() >= 2) T.S = T.Rotacion(5, T.S);
        else if (cx < T.S.getBounds2D().getMinX() && e.getClickCount() >= 2) T.S = T.Rotacion(-5, T.S);
        v.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int sentido = e.getWheelRotation();
        if (sentido < 0) T.S = T.Escalar(1.1, T.S);
        else T.S = T.Escalar(.9, T.S);
        v.repaint();
    }

}