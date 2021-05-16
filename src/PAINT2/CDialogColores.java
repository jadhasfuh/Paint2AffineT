package PAINT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CDialogColores extends JDialog implements ActionListener {

    JLabel titulo, tran;
    JRadioButton ver, hor, trasn, color, gradient;
    ButtonGroup grad, opciones;                                                               //0 -> H, 1 -> V
    JPanel botones, contenedor;
    JButton bac, bca, c1, c2, c3;
    Font F;
    Gradiente G;
    URL ruta;
    JColorChooser Selectorcolor;

    public CDialogColores(Main ref, boolean modal, Gradiente gr) {

        super(ref.v, modal);
        setSize(500, 200);
        setLocationRelativeTo(this);
        setUndecorated(true);
        getContentPane().setBackground(Color.GRAY);

        F = new Font("Lucida Sans", Font.PLAIN, 12);
        G = gr;

        titulo = new JLabel("RELLENOS");
        titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/color.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);

        getContentPane().add(titulo, BorderLayout.NORTH);

        ruta = getClass().getResource("/PAINT2/rec/ok.png");
        bac = new JButton(new ImageIcon(ruta));
        bac.addActionListener(this);

        ruta = getClass().getResource("/PAINT2/rec/exit.png");
        bca = new JButton(new ImageIcon(ruta));
        bca.addActionListener(this);

        botones = new JPanel(new GridLayout(1, 2));
        botones.add(bac);
        botones.add(bca);

        getContentPane().add(botones, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.GRAY);

        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(3, 2));

        ver = RADIO("Vertical");
        hor = RADIO("Horizontal");
        trasn = RADIO("Sin Relleno");
        color = RADIO("Color");
        gradient = RADIO("Gradiente");

        grad = new ButtonGroup();
        grad.add(ver);
        grad.add(hor);

        if (G.V) ver.setSelected(true);
        else hor.setSelected(true);

        opciones = new ButtonGroup();
        opciones.add(trasn);
        opciones.add(color);
        opciones.add(gradient);

        trasn.addActionListener(this);
        color.addActionListener(this);
        gradient.addActionListener(this);

        tran = new JLabel();

        c1 = new JButton();
        c1.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/pic.png")));
        c1.setOpaque(true);
        c1.setBorderPainted(false);
        c1.setBackground(G.C1);
        c1.addActionListener(this);

        c2 = new JButton();
        c2.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/pic.png")));
        c2.setOpaque(true);
        c2.setBorderPainted(false);
        c2.setBackground(G.C1);
        c2.addActionListener(this);

        c3 = new JButton();
        c3.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/pic.png")));
        c3.setOpaque(true);
        c3.setBorderPainted(false);
        c3.setBackground(G.C2);
        c3.addActionListener(this);

        if (G.T) {
            trasn.setSelected(true);
            ver.setEnabled(false);
            hor.setEnabled(false);
            c1.setEnabled(false);
            c2.setEnabled(false);
            c3.setEnabled(false);
        } else if (G.G) {
            gradient.setSelected(true);
            ver.setEnabled(true);
            hor.setEnabled(true);
            c1.setEnabled(false);
            c2.setEnabled(true);
            c3.setEnabled(true);
        } else {
            color.setSelected(true);
            ver.setEnabled(false);
            hor.setEnabled(false);
            c1.setEnabled(false);
            c2.setEnabled(false);
            c3.setEnabled(false);
        }

        contenedor.add(trasn);
        contenedor.add(tran);
        contenedor.add(color);
        contenedor.add(c1);
        contenedor.add(gradient);

        JPanel opGrad = new JPanel(new GridLayout(2, 2));
        opGrad.add(c2);
        opGrad.add(c3);
        opGrad.add(ver);
        opGrad.add(hor);

        contenedor.add(opGrad);

        getContentPane().add(contenedor, BorderLayout.CENTER);

    }

    public JRadioButton RADIO(String texto) {

        JRadioButton l = new JRadioButton(texto);
        l.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l.setFont(F);
        return l;

    }

    public void Mostrar() {

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bac) {
            if (trasn.isSelected()) {
                G.T = true;
                G.G = false;
            } else if (gradient.isSelected()) {
                G.G = true;
                G.T = false;
            } else if (color.isSelected()) {
                G.G = false;
                G.T = false;
            }
            if (ver.isSelected()) G.V = true;
            else G.V = false;
            setVisible(false);
        } else if (e.getSource() == bca) {
            setVisible(false);
            dispose();
        } else if (e.getSource() == trasn) {
            ver.setEnabled(false);
            hor.setEnabled(false);
            c1.setEnabled(false);
            c2.setEnabled(false);
            c3.setEnabled(false);
        } else if (e.getSource() == color){
            ver.setEnabled(false);
            hor.setEnabled(false);
            c1.setEnabled(true);
            c2.setEnabled(false);
            c3.setEnabled(false);
        } else if (e.getSource() == gradient){
            ver.setEnabled(true);
            hor.setEnabled(true);
            c1.setEnabled(false);
            c2.setEnabled(true);
            c3.setEnabled(true);
        } else if (e.getSource() == c1) {
            Selectorcolor = new JColorChooser();
            Color c = Selectorcolor.showDialog(null, "Seleccione un Color", G.C1);
            if (c != null) G.C1 = c;
            c1.setBackground(G.C1);
            c2.setBackground(G.C1);
        } else if (e.getSource() == c2) {
            Selectorcolor = new JColorChooser();
            Color c = Selectorcolor.showDialog(null, "Seleccione un Color", G.C1);
            if (c != null) G.C1 = c;
            c2.setBackground(G.C1);
            c1.setBackground(G.C1);
        } else if (e.getSource() == c3) {
            JColorChooser Selectorcolor = new JColorChooser();
            Color c = Selectorcolor.showDialog(null, "Seleccione un Color", G.C2);
            if (c != null) G.C2 = c;
            c3.setBackground(G.C2);
        }

    }
}