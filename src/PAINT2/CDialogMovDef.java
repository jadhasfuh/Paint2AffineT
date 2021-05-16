package PAINT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CDialogMovDef extends JDialog {

    double pos[];
    JLabel et2, et3, titulo;
    JTextField ct1, ct2;
    JButton bac, bca;
    JPanel contenedor, botones;
    URL ruta;
    Font F;

    public CDialogMovDef(Main ref, boolean modal, int tipo) {

        super(ref.v, modal);
        setSize(500, 150);
        setLocationRelativeTo(this);
        setUndecorated(true);

        F = new Font("Lucida Sans", Font.PLAIN, 12);

        if (tipo == 1) {
            titulo = new JLabel("DEFORMAR");
            titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/defo.png")));
            et2 = new JLabel("Eje X:");
            et3 = new JLabel("Eje Y:");
        } else {
            titulo = new JLabel("MOVER");
            titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/move.png")));
            et2 = new JLabel("En X:");
            et3 = new JLabel("En Y:");
        }

        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);
        getContentPane().add(titulo, BorderLayout.NORTH);

        ruta = getClass().getResource("/PAINT2/rec/ok.png");
        bac = new JButton(new ImageIcon(ruta));
        ruta = getClass().getResource("/PAINT2/rec/exit.png");
        bca = new JButton(new ImageIcon(ruta));

        botones = new JPanel(new GridLayout(1, 2));
        botones.add(bac);
        botones.add(bca);

        et2.setFont(F);
        et2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        et3.setFont(F);
        et3.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        ct1 = new JTextField();
        ct1.setFont(F);
        ct1.setText("0");
        ct2 = new JTextField();
        ct2.setFont(F);
        ct2.setText("0");

        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(2, 2));

        contenedor.add(et2);
        contenedor.add(ct1);
        contenedor.add(et3);
        contenedor.add(ct2);

        getContentPane().add(botones, BorderLayout.SOUTH);
        getContentPane().add(contenedor, BorderLayout.CENTER);
        getContentPane().setBackground(Color.GRAY);

        bac.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    pos = new double[2];
                    if (ct1.getText().isEmpty()) pos[0] = 0;
                    else pos[0] = Double.parseDouble(ct1.getText());
                    if (ct2.getText().isEmpty()) pos[1] = 0;
                    else pos[1] = Double.parseDouble(ct2.getText());
                    setVisible(false);
                    dispose();
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Valores inv\u00E1lidos", "", JOptionPane.WARNING_MESSAGE);
                }

            }

        });

        bca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                pos = null;
                setVisible(false);
                dispose();

            }

        });

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    public double[] mostrar() {

        setVisible(true);
        return pos;

    }

}
