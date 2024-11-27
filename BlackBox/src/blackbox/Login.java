package blackbox;
import javax.swing.*;

public class Login extends javax.swing.JFrame {

    private int loginAttempts = 0;
    private Client client;
    
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginLeftPanel = new javax.swing.JPanel();
        LoginLogo = new javax.swing.JLabel();
        WelcomeBig = new javax.swing.JLabel();
        WelcomeSmall = new javax.swing.JLabel();
        LoginRightPanel = new javax.swing.JPanel();
        LoginLabel = new javax.swing.JLabel();
        Password = new javax.swing.JLabel();
        Username = new javax.swing.JLabel();
        PasswordBox = new javax.swing.JPasswordField();
        UsernameBox = new javax.swing.JTextField();
        SubmitButton = new javax.swing.JButton();
        errorMessageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 48, 107));
        setPreferredSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(null);

        LoginLeftPanel.setBackground(new java.awt.Color(0, 0, 0));
        LoginLeftPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        LoginLeftPanel.setLayout(null);

        LoginLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blackbox_logo.png"))); // NOI18N
        LoginLogo.setText("jLabel1");
        LoginLeftPanel.add(LoginLogo);
        LoginLogo.setBounds(75, 35, 250, 250);

        WelcomeBig.setBackground(new java.awt.Color(0, 0, 0));
        WelcomeBig.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        WelcomeBig.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeBig.setText("Welcome to BlackBox");
        LoginLeftPanel.add(WelcomeBig);
        WelcomeBig.setBounds(30, 300, 350, 70);
        WelcomeBig.getAccessibleContext().setAccessibleName("WelcomeBig");

        WelcomeSmall.setBackground(new java.awt.Color(0, 0, 0));
        WelcomeSmall.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        WelcomeSmall.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeSmall.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WelcomeSmall.setText("Your new secure messaging service");
        LoginLeftPanel.add(WelcomeSmall);
        WelcomeSmall.setBounds(50, 370, 300, 23);
        WelcomeSmall.getAccessibleContext().setAccessibleName("WelcomeSmall");

        getContentPane().add(LoginLeftPanel);
        LoginLeftPanel.setBounds(0, 0, 400, 500);
        LoginLeftPanel.getAccessibleContext().setAccessibleName("LoginLeftPanel");

        LoginRightPanel.setBackground(new java.awt.Color(255, 255, 255));
        LoginRightPanel.setForeground(new java.awt.Color(0, 48, 107));
        LoginRightPanel.setToolTipText("");
        LoginRightPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        LoginRightPanel.setLayout(null);

        LoginLabel.setBackground(new java.awt.Color(255, 255, 255));
        LoginLabel.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(0, 48, 107));
        LoginLabel.setText("LOGIN");
        LoginRightPanel.add(LoginLabel);
        LoginLabel.setBounds(150, 60, 100, 60);
        LoginLabel.getAccessibleContext().setAccessibleName("LoginLabel");

        Password.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        Password.setForeground(new java.awt.Color(0, 48, 107));
        Password.setText("Password");
        LoginRightPanel.add(Password);
        Password.setBounds(30, 270, 80, 23);

        Username.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        Username.setForeground(new java.awt.Color(0, 48, 107));
        Username.setText("Username");
        LoginRightPanel.add(Username);
        Username.setBounds(30, 160, 80, 23);

        PasswordBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordBoxActionPerformed(evt);
            }
        });
        LoginRightPanel.add(PasswordBox);
        PasswordBox.setBounds(30, 300, 340, 35);

        UsernameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameBoxActionPerformed(evt);
            }
        });
        LoginRightPanel.add(UsernameBox);
        UsernameBox.setBounds(30, 190, 340, 35);

        SubmitButton.setBackground(new java.awt.Color(0, 48, 107));
        SubmitButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        SubmitButton.setForeground(new java.awt.Color(255, 255, 255));
        SubmitButton.setText("Submit");
        SubmitButton.setAlignmentY(0.0F);
        SubmitButton.setIconTextGap(0);
        SubmitButton.setMargin(new java.awt.Insets(2, 14, 0, 14));
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });
        LoginRightPanel.add(SubmitButton);
        SubmitButton.setBounds(150, 400, 100, 40);

        errorMessageLabel.setBackground(new java.awt.Color(255, 255, 255));
        errorMessageLabel.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        errorMessageLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorMessageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoginRightPanel.add(errorMessageLabel);
        errorMessageLabel.setBounds(40, 350, 320, 30);

        getContentPane().add(LoginRightPanel);
        LoginRightPanel.setBounds(400, 0, 400, 500);
        LoginRightPanel.getAccessibleContext().setAccessibleName("LoginRightPanel");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordBoxActionPerformed
        // TODO add your handling code here:
        SubmitButton.doClick();
    }//GEN-LAST:event_PasswordBoxActionPerformed

    private void UsernameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameBoxActionPerformed

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed
       // TODO add your handling code here:
        String username = UsernameBox.getText();
        String password = new String(PasswordBox.getPassword());  // Convert password to string

        this.client = new Client("localhost", 9001); // Example: You may want to pass server/port dynamically

        if (client.login(username, password)) {
            SwingUtilities.invokeLater(()-> {
                JOptionPane.showMessageDialog(this, "Login Successful");
                // Create the Conversations window
                Conversations conversationsWindow = new Conversations(client, username);
                client.setConversations(conversationsWindow);
                new Thread(() -> client.listenForMessages()).start();
                conversationsWindow.pack();
                conversationsWindow.setLocationRelativeTo(null);
                conversationsWindow.setVisible(true);
                this.dispose();
            });
        } else {
        loginAttempts++;
        if (loginAttempts >= 3) {
            JOptionPane.showMessageDialog(this, "Too many failed attempts.");
            client.disconnect(); // Close connection on exit
            System.exit(0); // Exit after 3 failed login attempts
        } else {
            // Update error message on failed login attempt
            SwingUtilities.invokeLater(() -> {
                errorMessageLabel.setText("Invalid username or password.");
                errorMessageLabel.setVisible(true); // Ensure error label is visible
            });
            client.disconnect(); // Disconnect client after failed login
        }
    }
    }//GEN-LAST:event_SubmitButtonActionPerformed

    protected Client getClient()
    {
        return client;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JPanel LoginLeftPanel;
    private javax.swing.JLabel LoginLogo;
    private javax.swing.JPanel LoginRightPanel;
    private javax.swing.JLabel Password;
    private javax.swing.JPasswordField PasswordBox;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JLabel Username;
    private javax.swing.JTextField UsernameBox;
    private javax.swing.JLabel WelcomeBig;
    private javax.swing.JLabel WelcomeSmall;
    private javax.swing.JLabel errorMessageLabel;
    // End of variables declaration//GEN-END:variables
}
