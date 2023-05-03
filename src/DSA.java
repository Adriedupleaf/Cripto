import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class DSA extends JFrame{
    private JPanel DSAP;
    private JComboBox<String> comboBox1;
    private JPanel Res1;
    private JTextField DPubField;
    private JTextField DPrivField;
    private JButton generateButton;
    private JPanel Res2;
    private JTextField messageField;
    private JButton encryptButton;
    private JTextField enField;
    private JButton decryptButton;
    private JTextField decField;
    private KeyPair key=null;
    private SecretKey secretKey=null;
    private Cripts Cripto;
    private byte[] sign;
    private byte[] signD;

    DSA(){
        setContentPane(DSAP);
        setTitle("CriptingDSA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSecretKey(Cripts.gAESkey());
                    setKey(Cripts.gRSAkey());
                    DPubField.setText(getKey().toString().substring(22));
                    DPrivField.setText(getKey().toString().substring(22));

                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int obj= comboBox1.getSelectedIndex();
                switch (obj){
                    case 0->{
                        clearVision();
                        Res1.setVisible(true);}
                    case 1->{
                        clearVision();
                        Res2.setVisible(true);
                    }
                }
            }
        });
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(messageField.getText().length()!=0&&secretKey!=null&&key!=null) {
                        sign = Cripts.enAES(messageField.getText(), secretKey);
                        signD = Cripts.signM(sign,key.getPrivate());
                        enField.setText(signD.toString());
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    decField.setText(String.valueOf(Cripts.signV(sign,signD,key.getPublic())));

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void init(){
        clearVision();
        comboBox1.addItem("Generate key pair for DSA");
        comboBox1.addItem("Sign/Verify message");
    }
    public void clearVision(){
        Res1.setVisible(false);
        Res2.setVisible(false);
    }

    public KeyPair getKey() {
        return key;
    }

    public void setKey(KeyPair key) {
        this.key = key;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
