package PAINT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CDialogScale extends JDialog {

    double cantEsc = 0;
    JPanel contenedor, botones;
    JLabel et2, titulo;
    JTextField ct1;
    JButton bac, bca;
    URL ruta;
    Font F;

    public CDialogScale(Main ref, boolean modal) {

        super(ref.v, modal);
        setSize(500, 115);
        setLocationRelativeTo(this);
        setUndecorated(true);

        F = new Font("Lucida Sans", Font.PLAIN, 12);

        titulo = new JLabel("ESCALAR");
        titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/scale.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);
        getContentPane().add(titulo, BorderLayout.NORTH);

        et2 = new JLabel("Escala:");
        et2.setFont(F);
        et2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        ct1 = new JTextField(10);
        ct1.setFont(F);
        ct1.setText("0");

        ruta = getClass().getResource("/PAINT2/rec/ok.png");
        bac = new JButton(new ImageIcon(ruta));
        ruta = getClass().getResource("/PAINT2/rec/exit.png");
        bca = new JButton(new ImageIcon(ruta));

        botones = new JPanel(new GridLayout(1, 2));
        botones.add(bac);
        botones.add(bca);

        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(1, 2));

        contenedor.add(et2);
        contenedor.add(ct1);

        getContentPane().add(botones, BorderLayout.SOUTH);
        getContentPane().add(contenedor, BorderLayout.CENTER);
        getContentPane().setBackground(Color.GRAY);

        bac.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String res = ct1.getText();
                try {
                    cantEsc = Double.parseDouble(res);
                } catch (NumberFormatException e2) {
                    cantEsc = 1.0;
                }
                setVisible(false);
                dispose();
            }
        });

        bca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cantEsc = 1.0;
                setVisible(false);
                dispose();

            }

        });

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    public double mostrar() {

        setVisible(true);
        return cantEsc;

    }

}