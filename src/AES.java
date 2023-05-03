import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class AES extends JFrame{
    private JComboBox<String> comboBox1;
    private JPanel AESP;
    private JPanel Res1;
    private JTextField keyField;
    private JButton Generate;
    private JPanel Res2;
    private JTextField messageField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextField EnMessField;
    private JTextField DecMessField;
    private Cripts Cripto;
    private SecretKey key=null;
    private byte[] text=null;
    AES(){
        setContentPane(AESP);
        setTitle("CriptingAES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
        Generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setKey(Cripts.generateAESKey());;
                    keyField.setText(getKey().toString().substring(32));
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
                if(key!=null&&messageField.getText().length()!=0){
                    try {
                        setText(Cripts.encryptAES(messageField.getText(),getKey()));
                        EnMessField.setText(getText().toString());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(key!=null&&EnMessField.getText().length()!=0) {

                    try {
                        DecMessField.setText(Cripts.decryptAES(getText(),getKey()).toString());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
    public void clearVision(){
        Res1.setVisible(false);
        Res2.setVisible(false);
    }
    public void init(){
        clearVision();
        comboBox1.addItem("Generate key AES");
        comboBox1.addItem("Insert message AES");
    }

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }
}
