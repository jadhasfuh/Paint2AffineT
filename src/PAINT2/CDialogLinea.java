package PAINT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CDialogLinea extends JDialog {

    JLabel titulo, ljoin, lstroke, lancho, ldash, lcolor;
    JRadioButton rb, rm, rr, rdash, rdashent;
    ButtonGroup join;                                                               //0 -> BEVEL, 1 -> MITER, 2 -> ROUND
    JTextField ancho;
    ButtonGroup dashed;
    JPanel botones, contenedor;
    JButton bac, bca, color;
    Font F;
    URL ruta;

    public CDialogLinea(Main ref, boolean modal, Linea L) {

        super(ref.v, modal);
        setSize(500, 200);
        setLocationRelativeTo(this);
        setUndecorated(true);
        getContentPane().setBackground(Color.GRAY);

        F = new Font("Lucida Sans", Font.PLAIN, 12);

        titulo = new JLabel("LINEA");
        titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/line.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);
        getContentPane().add(titulo, BorderLayout.NORTH);

        ruta = getClass().getResource("/PAINT2/rec/ok.png");
        bac = new JButton(new ImageIcon(ruta));
        bac.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (rb.isSelected()) L.LJOIN = 0;
                else if (rm.isSelected()) L.LJOIN = 1;
                else L.LJOIN = 2;
                if (!ancho.getText().isEmpty()) L.ANCHO = Math.abs(Float.parseFloat(ancho.getText()));
                if (rdash.isSelected()) L.DASH = true;
                else L.DASH = false;
                setVisible(false);

            }

        });

        ruta = getClass().getResource("/PAINT2/rec/exit.png");
        bca = new JButton(new ImageIcon(ruta));
        bca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                dispose();

            }

        });

        botones = new JPanel(new GridLayout(1, 2));
        botones.add(bac);
        botones.add(bca);

        getContentPane().add(botones, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.GRAY);

        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(4, 2));

        ljoin = LABEL("Uniones de l\u00EDnea:");
        lstroke = LABEL("Estilo CAP:");
        lancho = LABEL("Anchura:");
        ldash = LABEL("Tipo de l\u00EDnea:");
        lcolor = LABEL("Color:");

        JPanel p1 = new JPanel(new GridLayout(1, 3));

        rb = BUTTON("Achatado");
        rm = BUTTON("Esquina");
        rr = BUTTON("Redondo");

        join = new ButtonGroup();
        join.add(rb);
        join.add(rm);
        join.add(rr);

        if (L.LJOIN == 0) rb.setSelected(true);
        else if (L.LJOIN == 1) rm.setSelected(true);
        else rr.setSelected(true);

        p1.add(rb);
        p1.add(rm);
        p1.add(rr);

        rm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                rdashent.setSelected(true);

            }

        });

        JPanel p3 = new JPanel(new GridLayout(1, 2));

        rdashent = BUTTON("Continua");
        rdash = BUTTON("Punteada");

        dashed = new ButtonGroup();
        dashed.add(rdash);
        dashed.add(rdashent);

        if (L.DASH) rdash.setSelected(true);
        else rdashent.setSelected(true);

        p3.add(rdash);
        p3.add(rdashent);

        rdash.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (rm.isSelected()) rdashent.setSelected(true);

            }
        });

        ancho = new JTextField();
        ancho.setText(L.ANCHO + "");
        ancho.setFont(F);

        color = new JButton();
        color.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/pic.png")));
        color.setOpaque(true);
        color.setBorderPainted(false);
        color.setBackground(L.COLOR);
        color.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JColorChooser Selectorcolor = new JColorChooser();
                L.COLOR = Selectorcolor.showDialog(null, "Seleccione un Color", L.COLOR);
                color.setBackground(L.COLOR);

            }

        });

        contenedor.add(ljoin);
        contenedor.add(p1);
        contenedor.add(lancho);
        contenedor.add(ancho);
        contenedor.add(ldash);
        contenedor.add(p3);
        contenedor.add(lcolor);
        contenedor.add(color);

        getContentPane().add(contenedor, BorderLayout.CENTER);

    }

    public JLabel LABEL(String texto) {

        JLabel l = new JLabel(texto);
        l.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l.setFont(F);
        return l;

    }

    public JRadioButton BUTTON(String texto) {

        JRadioButton r = new JRadioButton(texto);
        r.setFont(F);
        return r;

    }

    public void Mostrar() {

        setVisible(true);

    }

}