
import java.awt.Color;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MetroWindow extends javax.swing.JFrame implements MessageCallback {

    private Client client;
    private String serverAdress = "localhost";
    private Thread timer;

    private static final int PORT = 15236;

    @Override
    public void newMessage(Message message) {
        if (timer != null) {
            stopTimer();
        }
        jButton1.setEnabled(message.isYourTurn());
        jButton2.setEnabled(message.isYourTurn());

        metroWidget1.setPlan(message);
        setText(message.getScores(), message.getIndex());
        jLabel1.setText(message.getText());
        startTimer(message.getTime());
        this.repaint();
    }

    public MetroWindow() {
        initComponents();
    }

    private void disconnectMessage() {
        client.closeAll();
        jLabel1.setText("Disconnected");
        JOptionPane.showMessageDialog(this, "You were disconnected from the server. Try joining again", "Disconnected", JOptionPane.ERROR_MESSAGE);
    }

    private void setText(int[] scores, int index) {
        if (scores == null) {
            jLabel2.setText(" Scores: ");
            return;
        }
        String htmlContent = "<html> Scores: ";

        for (int i = 0; i < scores.length; i++) {
            Color color = MetroWidget.getColor(i);
            String colorString = String.format("rgb(%d,%d,%d)", color.getRed(), color.getGreen(), color.getBlue());
            String text;
            if (i == index) {
                text = "You";
            } else {
                text = MetroWidget.getColorText(i);
            }
            text += " - " + scores[i] + " " + "&nbsp;" + "&nbsp;";
            htmlContent += "<span style='color: " + colorString + ";'>" + "<b>" + text + "</b>" + "</span>";
        }

        htmlContent += "</html>";

        jLabel2.setText(htmlContent);
    }

    private void startTimer(int time) {
        if (time == 0) {
            return;
        }
        if (timer != null) {
            timer.interrupt();
        }
        final int UPDATETIME = 100;
        timer = new Thread() {
            @Override
            public void run() {
                int count = 0;
                while (count * UPDATETIME <= time) {
                    jProgressBar1.setValue(100 * (time - count * UPDATETIME) / time);
                    try {
                        Thread.sleep(UPDATETIME);
                    } catch (InterruptedException ex) {
                        return;
                    }
                    count++;
                }
            }
        };
        timer.start();
    }

    private void stopTimer() {
        timer.interrupt();
        jProgressBar1.setValue(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        metroWidget1 = new MetroWidget();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        metroWidget1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                metroWidget1MouseReleased(evt);
            }
        });

        jButton1.setText("Place tile");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Draw from deck");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select a server");

        jButton3.setText("Join server");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText(" Scores:");

        jButton4.setText("Select");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Remove");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(metroWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(metroWidget1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void metroWidget1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_metroWidget1MouseReleased
        metroWidget1.setSelected(evt.getX(), evt.getY());
    }//GEN-LAST:event_metroWidget1MouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Message message = new Message(metroWidget1.getSelected(), false);
            client.sendMessage(message);

        } catch (IOException ex) {
            disconnectMessage();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to join server?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            if (client != null) {
                client.closeAll();
            }
            jLabel1.setText("Waiting for server...");
            try {
                client = new Client(serverAdress, PORT, MetroWindow.this);
            } catch (IOException ex) {
                disconnectMessage();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Message message = new Message(true);
        try {
            client.sendMessage(message);
        } catch (IOException ex) {
            disconnectMessage();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String[] options = ServerListIO.readServerList();

        // Create a combo box with the predefined options
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Create a text field for custom input
        JTextField customInputField = new JTextField(10);

        // Create a check box for saving the server address
        JCheckBox saveCheckBox = new JCheckBox("Save server address");

        // Create a panel to hold the combo box, text field, and check box
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select an option or enter a custom option:"));
        panel.add(comboBox);
        panel.add(new JLabel("Custom:"));
        panel.add(customInputField);
        panel.add(saveCheckBox);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Choose an Option", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Process the user's input
        if (result == JOptionPane.OK_OPTION) {
            String selectedOption = (String) comboBox.getSelectedItem();
            String customOption = customInputField.getText().trim();
            boolean saveServerAddress = saveCheckBox.isSelected();

            if (customOption.isEmpty()) {
                serverAdress = selectedOption;
                return;
            }
            serverAdress = customOption;
            if (saveServerAddress) {
                ServerListIO.addServer(customOption);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String[] options = ServerListIO.readServerList();

        // Create a combo box with the predefined options
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Create a panel to hold the combo box
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select a Server to remove:"));
        panel.add(comboBox);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Remove a Server", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Process the user's input
        if (result == JOptionPane.OK_OPTION) {
            int selectedOption = comboBox.getSelectedIndex();
            ServerListIO.removeLineFromFile(selectedOption);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MetroWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MetroWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MetroWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MetroWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MetroWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private MetroWidget metroWidget1;
    // End of variables declaration//GEN-END:variables
}
