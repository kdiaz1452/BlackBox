package blackbox;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class MessagePanel extends javax.swing.JPanel {

    private Client client;
    private String recipientUsername;
    private String senderUsername;

    public MessagePanel(Client client, String recipientUsername) {
        this.client = client;
        this.recipientUsername = recipientUsername;
        System.out.println("Recipient Username: " + recipientUsername);
        initComponents();
        
        // Set the title with recipient's name
        jLabel1.setText(recipientUsername);

        // Configure send button to send messages
        sendButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()) {
                senderUsername = client.getUsername();
                client.sendMessage(recipientUsername, message);
                messageInput.setText(""); // Clear input field
            }
        }
    });

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        messageDisplayPanel = new javax.swing.JPanel();
        messageInput = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(52, 56, 62));
        setLayout(new java.awt.GridBagLayout());

        messageDisplayPanel.setBackground(new java.awt.Color(52, 56, 62));
        messageDisplayPanel.setAutoscrolls(true);
        messageDisplayPanel.setMinimumSize(new java.awt.Dimension(16, 16));
        messageDisplayPanel.setPreferredSize(new java.awt.Dimension(234, 86));
        messageDisplayPanel.setLayout(new javax.swing.BoxLayout(messageDisplayPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(messageDisplayPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 372;
        gridBagConstraints.ipady = 334;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        add(jScrollPane1, gridBagConstraints);

        messageInput.setBackground(new java.awt.Color(52, 56, 62));
        messageInput.setForeground(new java.awt.Color(255, 255, 255));
        messageInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageInputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.ipadx = 236;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(messageInput, gridBagConstraints);

        sendButton.setBackground(new java.awt.Color(0, 48, 107));
        sendButton.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        sendButton.setForeground(new java.awt.Color(255, 255, 255));
        sendButton.setText("Send");
        sendButton.setIconTextGap(0);
        sendButton.setMargin(new java.awt.Insets(3, 14, 0, 14));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 5, 5);
        add(sendButton, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 36;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 100, 0, 0);
        add(jLabel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void messageInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageInputActionPerformed
        // TODO add your handling code here:
        sendButton.doClick();
    }//GEN-LAST:event_messageInputActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendButtonActionPerformed

 // Display sent message
    public void displaySentMessage(String message) {
        System.out.println("displaySentMessage was called");
        JPanel bubblePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align to the right
        bubblePanel.setOpaque(false);

        JTextArea messageBubble = new JTextArea(message);
        messageBubble.setEditable(false);
        messageBubble.setBackground(new Color(0, 102, 204)); // Dark blue for sent messages
        messageBubble.setForeground(Color.WHITE);
        messageBubble.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        messageBubble.setLineWrap(true);
        messageBubble.setWrapStyleWord(true);
        messageBubble.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set max width for the text area
        messageBubble.setMaximumSize(new Dimension(250, Short.MAX_VALUE));
        bubblePanel.add(messageBubble);

        messageDisplayPanel.add(bubblePanel);
        updateScroll();
        messageDisplayPanel.revalidate();
        messageDisplayPanel.repaint();
    }

    // Display received message
    public void displayReceivedMessage(String message) {
        System.out.println("displayReceivedMessage was called");
        JPanel bubblePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align to the left
        bubblePanel.setOpaque(false);

        JTextArea messageBubble = new JTextArea(message);
        messageBubble.setEditable(false);
        messageBubble.setBackground(new Color(173, 216, 230)); // Light blue for received messages
        messageBubble.setForeground(Color.BLACK);
        messageBubble.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        messageBubble.setLineWrap(true);
        messageBubble.setWrapStyleWord(true);
        messageBubble.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set max width for the text area
        messageBubble.setMaximumSize(new Dimension(250, Short.MAX_VALUE));
        bubblePanel.add(messageBubble);

        messageDisplayPanel.add(bubblePanel);
        updateScroll();
        messageDisplayPanel.revalidate();
        messageDisplayPanel.repaint();
    }


    // Scroll to the bottom when a new message is added
    private void updateScroll() {
        SwingUtilities.invokeLater(() -> {
            JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, messageDisplayPanel);
            if (scrollPane != null) {
                JScrollBar vertical = scrollPane.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
            }
        });
    }

    
    protected JTextField getMessageInput(){
        return messageInput;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel messageDisplayPanel;
    private javax.swing.JTextField messageInput;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
