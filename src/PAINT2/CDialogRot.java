package PAINT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CDialogRot extends JDialog {

    double argR[];
    JLabel et2, titulo;
    JTextField ct1;
    JButton bac, bca;
    JPanel botones, contenedor;
    URL ruta;
    Font F;

    public CDialogRot(Main ref, boolean modal, int tipo) {

        super(ref.v, modal);
        setSize(500, 115);
        setLocationRelativeTo(this);
        setUndecorated(true);

        F = new Font("Lucida Sans", Font.PLAIN, 12);
        getContentPane().setBackground(Color.GRAY);

        if (tipo == 1) {
            titulo = new JLabel("ROTAR SENTIDO HORARIO");
            titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/rotd.png")));
        } else {
            titulo = new JLabel("ROTAR ANTI SENTIDO HORARIO");
            titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/roti.png")));
        }
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);
        getContentPane().add(titulo, BorderLayout.NORTH);

        et2 = new JLabel("\u00C1ngulo:");
        et2.setFont(F);
        ct1 = new JTextField();
        ct1.setText("0");
        ct1.setFont(F);

        ruta = getClass().getResource("/PAINT2/rec/ok.png");
        bac = new JButton(new ImageIcon(ruta));
        ruta = getClass().getResource("/PAINT2/rec/exit.png");
        bca = new JButton(new ImageIcon(ruta));


        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(1, 2));
        contenedor.add(et2);
        contenedor.add(ct1);
        et2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        botones = new JPanel(new GridLayout(1, 2));
        botones.add(bac);
        botones.add(bca);

        getContentPane().add(botones, BorderLayout.SOUTH);
        getContentPane().add(contenedor, BorderLayout.CENTER);

        bac.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                argR = new double[2];
                if (tipo == 1) argR[0] = 0;
                else if (tipo == 2) argR[0] = 1;
                try {
                    if (ct1.getText().isEmpty()) argR[1] = 0;
                    else argR[1] = Double.parseDouble(ct1.getText());
                    setVisible(false);
                    dispose();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Valores inv\u00E1lidos", "", JOptionPane.WARNING_MESSAGE);
                }

            }

        });

        bca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                argR = null;
                setVisible(false);
                dispose();
            }
        });

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    public double[] mostrar() {

        setVisible(true);
        return argR;

    }

}