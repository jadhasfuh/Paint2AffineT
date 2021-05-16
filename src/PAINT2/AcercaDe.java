package PAINT2;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcercaDe extends JDialog {

    JPanel botones, contenedor;
    JButton volver;
    JTextPane texto, name1;
    JLabel titulo,img1;
    String info =  "\n Paint 2 (Fork de Paint Chafa v.0.0) \n Desarrollado por: \n", nombre1 = "Ceja Renter\u00EDa Adri\u00E1n \nNo.Control: 17420533";

    public AcercaDe(Main ref, boolean modal) {

        super(ref.v, modal);

        setSize(640, 480);
        setLocationRelativeTo(this);
        setUndecorated(true);

        botones = new JPanel(new GridLayout(1,1));
        volver = new JButton(new ImageIcon(getClass().getResource("/PAINT2/rec/home.png")));
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(sa, .4f);
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
        botones.add(volver);
        getContentPane().add(botones, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.GRAY);

        titulo = new JLabel("ACERCA DE");
        titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/autor.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);
        getContentPane().add(titulo, BorderLayout.NORTH);

        texto = new JTextPane();
        texto.getStyledDocument().setParagraphAttributes(0, info.length(), sa, false);
        texto.setText(info);
        texto.setEnabled(false);
        texto.setOpaque(false);
        texto.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
        texto.setForeground(Color.BLACK);
        texto.setBounds(20, 20, 600, 400);

        img1 = new JLabel();
        img1.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/pp.jpg")));
        img1.setBounds(260, 130, 150, 150);

        name1 = new JTextPane();
        name1.getStyledDocument().setParagraphAttributes(0, nombre1.length(), sa, false);
        name1.setText(nombre1);
        name1.setBounds(255, 310, 240, 150);
        name1.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
        name1.setEnabled(false);

        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.add(texto);
        contenedor.add(img1);
        contenedor.add(name1);
        getContentPane().add(contenedor, BorderLayout.CENTER);

    }

    public void Mostrar() {
        setVisible(true);
    }

}