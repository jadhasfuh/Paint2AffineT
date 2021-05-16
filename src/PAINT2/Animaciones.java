package PAINT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Animaciones extends JDialog implements ActionListener {

    JButton max, min, rod, roi, def, mov, flh, flv, volver;
    JLabel lblmax, lblmin, lblrod, lblroi, lbldef, lblmov, lblflh, lblflv;
    JPanel contenedor;
    JTextPane name;
    Transformaciones T;
    Timer timer;

    public Animaciones(Main ref, boolean modal) {

        super(ref.v, modal);
        setSize(640, 480);
        setLocationRelativeTo(this);
        setUndecorated(true);

        name = new JTextPane();
        name.setForeground(Color.DARK_GRAY);
        name.setText("EJEMPLOS");
        name.setEnabled(false);
        name.setBackground(Color.GRAY);
        name.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        getContentPane().add(name, BorderLayout.NORTH);

        volver = new JButton(new ImageIcon(getClass().getResource("/PAINT2/rec/home.png")));
        volver.addActionListener(this);
        getContentPane().add(volver, BorderLayout.SOUTH);

        max = JBoton(getClass().getResource("/PAINT2/rec/scale.png"), 30, 10, 40, 40);
        lblmax = JEtiq("Maximizar", 90, 10, 120, 40);
        min = JBoton(getClass().getResource("/PAINT2/rec/contraer.png"), 30, 60, 40, 40);
        lblmin = JEtiq("Minimizar", 90, 60, 120, 40);
        rod = JBoton(getClass().getResource("/PAINT2/rec/rotd.png"), 30, 110, 40, 40);
        lblrod = JEtiq("Rotar a la Derecha", 90, 110, 120, 40);
        roi = JBoton(getClass().getResource("/PAINT2/rec/roti.png"), 30, 160, 40, 40);
        lblroi = JEtiq("Rotar a la Izquierda", 90, 160, 120, 40);
        def = JBoton(getClass().getResource("/PAINT2/rec/defo.png"), 30, 210, 40, 40);
        lbldef = JEtiq("Deformar", 90, 210, 120, 40);
        mov = JBoton(getClass().getResource("/PAINT2/rec/move.png"), 30, 260, 40, 40);
        lblmov = JEtiq("Mover", 90, 260, 120, 40);
        flh = JBoton(getClass().getResource("/PAINT2/rec/flip.png"), 30, 310, 40, 40);
        lblflh = JEtiq("Reflejar X", 90, 310, 120, 40);
        flv = JBoton(getClass().getResource("/PAINT2/rec/flipv.png"), 30, 360, 40, 40);
        lblflv = JEtiq("Reflejar Y", 90, 360, 120, 40);

        T = new Transformaciones();
        contenedor = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(Color.DARK_GRAY);
                T.DibujaPuntos(g,T.A);
            }
        };

        contenedor.setLayout(null);
        contenedor.add(max);
        contenedor.add(min);
        contenedor.add(rod);
        contenedor.add(roi);
        contenedor.add(def);
        contenedor.add(mov);
        contenedor.add(flh);
        contenedor.add(flv);
        contenedor.add(lblmax);
        contenedor.add(lblmin);
        contenedor.add(lblrod);
        contenedor.add(lblroi);
        contenedor.add(lbldef);
        contenedor.add(lblmov);
        contenedor.add(lblflh);
        contenedor.add(lblflv);

        getContentPane().add(contenedor, BorderLayout.CENTER);

    }

    public JButton JBoton(URL ruto, int x, int y, int w, int h) {
        JButton b = new JButton();
        b.setIcon(new ImageIcon(ruto));
        b.setBounds(x, y, w, h);
        b.addActionListener(this);
        return b;
    }

    public JLabel JEtiq(String tit, int x, int y, int w, int h) {
        JLabel e = new JLabel(tit);
        e.setBounds(x, y, w, h);
        e.setEnabled(false);
        e.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
        return e;
    }

    public void Mostrar() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (timer != null) timer.stop();
        if (e.getSource() == volver) {
            setVisible(false);
            dispose();
        } else if (e.getSource() == max) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Escalar(1.01,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        } else if (e.getSource() == min) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Escalar(.99,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        } else if (e.getSource() == rod){
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Rotacion(10,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        } else if (e.getSource() == roi) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Rotacion(-10,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        } else if (e.getSource() == def) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Deformar(.1,.09,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        } else if (e.getSource() == mov) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Traslacion(1,2,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        } else if (e.getSource() == flh) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Reflexion(-1,1,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        } else if (e.getSource() == flv) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    T.A = T.Reflexion(1,-1,T.A);
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.start();
        }

    }

}