import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class RSA extends JFrame{
    private JPanel RSAP;
    private JComboBox<String> comboBox1;
    private JPanel Res1;
    private JTextField PubKField;
    private JTextField PriKField;
    private JButton generateButton;
    private JButton generateButton1;
    private JTextField keyField;
    private JPanel Res2;
    private JPanel Res3;
    private JTextField enKeyField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextField DecKField;
    private KeyPair key=null;
    private SecretKey keyAES=null;
    private byte[] enKey=null;
    private Cripts Cripto;

    RSA(){
        setContentPane(RSAP);
        setTitle("CriptingRSA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setKey(Cripts.gRSAkey());
                    PubKField.setText(getKey().getPublic().toString());
                    PriKField.setText(getKey().getPrivate().toString());
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
                    case 2->{
                        clearVision();
                        Res3.setVisible(true);

                    }
                }
            }
        });
        generateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setKeyAES(Cripts.gAESkey());
                    keyField.setText(getKeyAES().toString().substring(32));
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(keyAES!=null&&key!=null){
                    setEnKey(Cripts.enRSA(getKeyAES().getEncoded(),getKey().getPublic()));
                    enKeyField.setText(getEnKey().toString());
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
                    if(key!=null&&keyAES!=null&&enKey!=null){
                    byte[] k= Cripts.decRSA(getEnKey(), getKey().getPrivate());
                    DecKField.setText(new SecretKeySpec(k, "AES").toString().substring(32));}
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    public void clearVision(){
        Res1.setVisible(false);
        Res2.setVisible(false);
        Res3.setVisible(false);
    }
    public void init(){
        clearVision();
        comboBox1.addItem("Generate key pair for RSA");
        comboBox1.addItem("Generate key for encryption");
        comboBox1.addItem("Encrypt/Decrypt");
    }

    public KeyPair getKey() {
        return key;
    }

    public void setKey(KeyPair key) {
        this.key = key;
    }

    public SecretKey getKeyAES() {
        return keyAES;
    }

    public void setKeyAES(SecretKey keyAES) {
        this.keyAES = keyAES;
    }

    public byte[] getEnKey() {
        return enKey;
    }

    public void setEnKey(byte[] enKey) {
        this.enKey = enKey;
    }
}
