package PAINT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CDialogFlip  extends JDialog {

    int argR;
    JLabel et3, et4, et5, titulo;
    JPanel botones, contenedor;
    JRadioButton rb1, rb2, rb3;
    ButtonGroup grupoR;
    JButton bac, bca;
    URL ruta;
    Font F;

    public JLabel LABEL(String t){

        JLabel l = new JLabel(t);
        l.setFont(F);
        l.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        return l;

    }

    public CDialogFlip(Main ref, boolean modal) {

        super(ref.v, modal);
        setSize(500, 150);
        setLocationRelativeTo(this);
        setUndecorated(true);

        F = new Font("Lucida Sans", Font.PLAIN, 12);
        getContentPane().setBackground(Color.GRAY);

        et3 = LABEL("Reflex X");
        et4 = LABEL("Reflex Y");
        et5 = LABEL("Reflex XY");

        titulo = new JLabel("REFLEXI\u00D3N");
        titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/flip.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);
        getContentPane().add(titulo, BorderLayout.NORTH);

        grupoR = new ButtonGroup();
        rb1 = new JRadioButton("", true);
        rb2 = new JRadioButton();
        rb3 = new JRadioButton();

        grupoR.add(rb1);
        grupoR.add(rb2);
        grupoR.add(rb3);

        ruta = getClass().getResource("/PAINT2/rec/ok.png");
        bac = new JButton(new ImageIcon(ruta));
        ruta = getClass().getResource("/PAINT2/rec/exit.png");
        bca = new JButton(new ImageIcon(ruta));

        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(3, 2));
        contenedor.add(et3);
        contenedor.add(rb1);
        contenedor.add(et4);
        contenedor.add(rb2);
        contenedor.add(et5);
        contenedor.add(rb3);

        botones = new JPanel(new GridLayout(1, 2));
        botones.add(bac);
        botones.add(bca);

        getContentPane().add(botones, BorderLayout.SOUTH);
        getContentPane().add(contenedor, BorderLayout.CENTER);

        bac.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                argR = 5;
                ;
                if (rb1.isSelected()) argR = 0;
                else if (rb2.isSelected()) argR = 1;
                else if (rb3.isSelected()) argR = 2;
                setVisible(false);
                dispose();

            }

        });

        bca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                argR = -1;
                setVisible(false);
                dispose();
            }

        });

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    public int mostrar() {

        setVisible(true);
        return argR;

    }

}