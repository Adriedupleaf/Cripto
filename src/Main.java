import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main extends JFrame{

    private JComboBox<String> comboBox1;
    private JPanel MainP;
    private Cripts Cpripto;
    public Main(){
        setContentPane(MainP);
        setTitle("CriptingF");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String obj= Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
                switch (obj){
                    case "AES"->{
                        new AES();
                    }
                    case "RSA"->{
                        new RSA();
                    }
                    case "DSA"->{
                        new DSA();
                    }
                }
            }
        });
    }
    public void init(){
        comboBox1.addItem("AES");
        comboBox1.addItem("RSA");
        comboBox1.addItem("DSA");
    }

    public static void main(String[] args) {
        new Main();
    }
}
