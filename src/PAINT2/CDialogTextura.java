package PAINT2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CDialogTextura extends JDialog implements ActionListener {

    JLabel titulo, tran, repe, lw, lh, vista;
    JRadioButton con, sin;
    ButtonGroup opc;                                                               //0 -> H, 1 -> V
    JPanel botones, contenedor;
    JButton bac, bca, selecionar;
    JTextField w, h;
    Font F;
    URL ruta;
    Textura T;
    BufferedImage i;

    public CDialogTextura(Main ref, boolean modal, Textura textura) {

        super(ref.v, modal);
        setSize(500, 200);
        setLocationRelativeTo(this);
        setUndecorated(true);
        getContentPane().setBackground(Color.GRAY);

        F = new Font("Lucida Sans", Font.PLAIN, 12);
        T = textura;

        titulo = new JLabel("TEXTURA");
        titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/color.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);

        JPanel dimensiones = new JPanel(new GridLayout(1, 4));

        vista = new JLabel();

        //RESPALDO DE ANTERIOR
        i = T.I;
        if (i != null) vista.setIcon(new ImageIcon(i));

        repe = LABEL("Repetici\u00F3n:");
        lw = LABEL("W:");
        lh = LABEL("H:");
        w = new JTextField();
        w.setFont(F);
        h = new JTextField();
        h.setFont(F);
        dimensiones.add(lw);
        dimensiones.add(w);
        dimensiones.add(lh);
        dimensiones.add(h);

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

        sin = RADIO("Sin Relleno");
        sin.addActionListener(this);
        con = RADIO("Seleccionar");
        con.addActionListener(this);

        opc = new ButtonGroup();
        opc.add(sin);
        opc.add(con);

        tran = new JLabel();

        selecionar = new JButton();
        selecionar.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/open.png")));
        selecionar.addActionListener(this);

        if (T.T) {
            sin.setSelected(true);
            selecionar.setEnabled(false);
            lh.setEnabled(false);
            lw.setEnabled(false);
            w.setEnabled(false);
            h.setEnabled(false);
        } else con.setSelected(true);

        JPanel selec = new JPanel(new GridLayout(1,2));
        selec.add(vista);
        selec.add(selecionar);

        contenedor.add(sin);
        contenedor.add(tran);
        contenedor.add(con);
        contenedor.add(selec);
        contenedor.add(repe);
        contenedor.add(dimensiones);

        getContentPane().add(contenedor, BorderLayout.CENTER);

    }


    public JRadioButton RADIO(String texto) {

        JRadioButton r = new JRadioButton(texto);
        r.setFont(F);
        r.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        return r;

    }

    public JLabel LABEL(String texto) {

        JLabel r = new JLabel(texto);
        r.setFont(F);
        r.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        return r;

    }

    public void Mostrar() {

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bac) {
            if (sin.isSelected()) {
                T.T = true;
                setVisible(false);
            } else if (i != null) {
                try {
                    T.NewTexture(Integer.parseInt(w.getText()), Integer.parseInt(h.getText()));
                    T.T = false;
                    setVisible(false);
                    dispose();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Valor de W y/o H inv\u00E1lido, valor default '10'", "", JOptionPane.WARNING_MESSAGE);
                    T.NewTexture(10, 10);
                    w.setText("10");
                    h.setText("10");
                }
            } else JOptionPane.showMessageDialog(null, "No ha seleccionado una imagen", "", JOptionPane.WARNING_MESSAGE);
        } else if (e.getSource() == bca) {
            setVisible(false);
            dispose();
        } else if (e.getSource() == sin) {
            selecionar.setEnabled(false);
            lh.setEnabled(false);
            lw.setEnabled(false);
            w.setEnabled(false);
            h.setEnabled(false);
        } else if (e.getSource() == con) {
            selecionar.setEnabled(true);
            lh.setEnabled(true);
            lw.setEnabled(true);
            w.setEnabled(true);
            h.setEnabled(true);
        } else if (e.getSource() == selecionar) {
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    i = ImageIO.read(file);
                    T.I = i;
                    if (i != null) vista.setIcon(new ImageIcon(i));
                } catch (IOException e1) {}
            }
        }


    }

}