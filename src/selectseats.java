/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mudit
 */
import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
public class selectseats extends javax.swing.JFrame {
    
    /**
     * Creates new form selectseats
     */
    int price;
    String resid,empid,sid,type,contact;
    String row;
    public selectseats() {
        initComponents();
    }
    public selectseats(String a,String b,String c,int d,String e,String f){
        initComponents();
        resid=a;
        empid=b;
        sid=c;
        price=d;
        type=e;
        contact=f;
    }

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cb1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        l1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D" }));
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(l1);

        jLabel1.setText("SELECT ROW");

        jButton1.setText("MAKE RESERVATION");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("SEATS AVAILABLE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(cb1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
             
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/db11g",
                "myuser",
                "password"
            );
            System.out.println("successful");
            Statement stmt=con.createStatement();
            int[] selectedIx = l1.getSelectedIndices();
            int num=selectedIx.length;
            System.out.println(num);
            String total_amt = Integer.toString(price * num);
            System.out.println("price=" +price);
            System.out.println("tot=" +total_amt);
            
            String q = "insert into reservation values (" + resid + ", " + empid + ", " + sid + ", '" + type + "', '" + contact + "', " + total_amt + ")";
            System.out.println("here");
            int x=stmt.executeUpdate(q);
            if(x==1) 
            System.out.println("here2");
            // Get all the selected items using the indices
            for (int i = 0; i < selectedIx.length; i++) {
            String seat = l1.getModel().getElementAt(selectedIx[i]).toString();
            String row_no = row;
            String seat_no = seat;
            String q2 = "select id from seat where row_no = '" + row_no + "' and seat_no = '" + seat_no + "'";
            ResultSet r1 = stmt.executeQuery(q2);
            if (r1.next()){
            String q3 = "insert into seat_reserved values (" + r1.getInt("id") + ", " + resid + ", " + sid + ")";
            int u= stmt.executeUpdate(q3);
            if(u==1) System.out.println("INSERTED");
            }
            
            }
            JOptionPane.showMessageDialog(this,"successfully added\n Total amount="+total_amt);
            res_or_add rd=new res_or_add();
            rd.setVisible(true);
            dispose();
            

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        // TODO add your handling code here:
        row=cb1.getSelectedItem().toString();
        System.out.println(row);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/db11g",
                "myuser",
                "password"
            );
            System.out.println("successful");
            String q1 = "select audi_id from screening where id = " + sid;
            Statement st=con.createStatement();
            ResultSet r1 = st.executeQuery(q1);
            String audi = "";
            if(r1.next()) {
                audi = Integer.toString(r1.getInt("audi_id"));
            }
            String query="(select seat_no from seat where audi_id = " + audi + "and row_no ='" +row+"') minus (select seat_no from seat,seat_reserved where seat.id=seat_reserved.seat_id and seat_reserved.screening_id="+sid+")";
            ResultSet rs=st.executeQuery(query);
            DefaultListModel model=new DefaultListModel();
            model.removeAllElements();
            l1.setModel(model);
            
            while(rs.next()){
                int seat=rs.getInt("seat_no");
                model.addElement(seat);
            }


            // Get the index of the first selected item
            

            

        }
        catch(Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_cb1ActionPerformed

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
            java.util.logging.Logger.getLogger(selectseats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(selectseats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(selectseats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(selectseats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new selectseats().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cb1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList l1;
    // End of variables declaration//GEN-END:variables
}
