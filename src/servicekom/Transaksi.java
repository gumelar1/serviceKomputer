/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicekom;

//import library2nya
import koneksi.Konek;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Asus A44H
 */
public class Transaksi extends javax.swing.JFrame {

    
   //membuat objek    
    private DefaultTableModel model;
    
    
    //deklarasi variabel
    String noJual,NMpelanggan, Alamat, kdProduk, nmProduk, xtotal;
    int  hrg_jual, qty;
    double total, bayar, kembali, sTotal;
    
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    String sql;
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        
        initComponents();
        Tanggal();
        
        
         //membuat obyek
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tblTransaksi.setModel(model);
        model.addColumn("NAMA PELANGGAN");
        model.addColumn("ALAMAT/NAMA INSTANSI");
        model.addColumn("KODE PRODUK");
        model.addColumn("NAMA PRODUK");
        model.addColumn("HARGA JUAL");
        model.addColumn("QTY");
        model.addColumn("SUBTOTAL");
        
    }
    //fungsi untuk menampilkan data pada textbox
    public void dataProduk(){   
        try{
            //tes koneksi
            Statement stat = (Statement) Konek.getKoneksi().createStatement();
           
            //perintah sql untuk membaca data dari tabel produk
            String sql = "SELECT * FROM tbl_produk WHERE kd_produk = '"+ txtKdProduk.getText() +"'";
            ResultSet res = stat.executeQuery(sql);
                        
            //baca data dan tampilkan pada text produk dan harga
            while(res.next()){
                //membuat obyek berjenis array
               txtNmProduk.setText(res.getString("nm_produk"));
               txtHrg.setText(res.getString("hrg_jual"));
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
   public void reset(){
        noJual ="";
        NMpelanggan="";
        Alamat="";
        kdProduk="";
        nmProduk="";
        xtotal="";
        hrg_jual=0;
        qty=0;
        total=0;
        bayar=0;
        kembali=0;
        sTotal=0;
       
        txtNoJual.setText("");
        txtNMpelanggan.setText("");
        txtAlamat.setText("");
        txtKdProduk.setText("");
        txtNmProduk.setText("");
        txtHrg.setText("");
        lblTotal.setText("");
        lblKembali.setText("");
        txtQty.setText("");
        txtBayar.setText("");
        
    }
    //fungsi untuk memasukan barang yang sudah dipilih pada tabel sementara
    public void masukTabel(){
        try{
            String NMpelanggan=txtNMpelanggan.getText();
            String Alamat=txtAlamat.getText();
            String nmProduk=txtNmProduk.getText();
            double hrg=Double.parseDouble(txtHrg.getText());
            int jml=Integer.parseInt(txtQty.getText());
            sTotal = hrg*jml;
            total = total + sTotal;
            xtotal=Double.toString(total);
            lblTotal.setText(xtotal);
            model.addRow(new Object[]{NMpelanggan,Alamat,nmProduk,hrg,jml,sTotal});
        }
        catch(Exception e){
            System.out.println("Error : "+e);
        }
    }
    public void idOtomatis(){
    try{
        sql="select *from tbl_transaksi order by no_jual desc";
        st=con.createStatement();
        rs=st.executeQuery(sql);
        if (rs.next()){
            String nofak = rs.getString("no_jual").substring(1);
            String AN = "" + (Integer.parseInt(nofak)+1);
            String Nol = "";
            
            if(AN.length()==1)
            {Nol = "000";}
            else if(AN.length()==2)
                {Nol = "00";}
            else if(AN.length()==3)
                {Nol = "0";}
            else if(AN.length()==4)
                {Nol = "";}
            txtNoJual.setText("J" + Nol + AN);
        }else{
            txtNoJual.setText("J0001");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    public void simpanDataTransaksi(){ 
        //proses perhitungan uang kembalian
        bayar = Double.parseDouble(txtBayar.getText());
        kembali = bayar - total;
        String xkembali=Double.toString(kembali);
        lblKembali.setText(xkembali);
       
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) Konek.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO tbl_transaksi(no_jual,kd_produk, nm_produk, hrg_jual, qty, total, bayar, kembali,nm_pelanggan,alamat,tanggal)"
                            + "VALUES('"+ txtNoJual.getText() +"','"+ txtKdProduk.getText() +"','"+ txtNmProduk.getText() +"','"+
                    txtHrg.getText() +"','"+ txtQty.getText() +"','"+ total +"', '"+ txtBayar.getText()
                    +"', '"+ kembali +"','"+ txtNMpelanggan.getText() +"','"+ txtAlamat.getText() +"','"+ jTanggal.getText() +"')";
            PreparedStatement p = (PreparedStatement) Konek.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    public void Tanggal(){
        Date ys=new Date(); 
     SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy"); 
     jTanggal.setText(s.format(ys)); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBayar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtNMpelanggan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        tblSimpan = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmdKeranjang = new javax.swing.JButton();
        txtKdProduk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNmProduk = new javax.swing.JTextField();
        lblKembali = new javax.swing.JLabel();
        txtHrg = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        txtNoJual = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmdKeranjang1 = new javax.swing.JButton();
        jTanggal = new javax.swing.JTextField();
        tblSimpan1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBayar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel1.add(txtBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 397, 204, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 80, 41));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("NAMA PELANGGAN");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 74, -1, -1));
        jPanel1.add(txtNMpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 71, 225, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("ALAMAT / NAMA INSTANSI");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        jPanel1.add(txtAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 97, 225, -1));

        tblSimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblSimpan.setText("SIMPAN");
        tblSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(tblSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 80, 40));

        label.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        label.setText("TOTAL PEMBAYARAN :");
        jPanel1.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 354, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("BAYAR                         :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 408, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TRANSAKSI JS SERVICE ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("NOMOR PENJUALAN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("KODE PRODUK");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 154, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("NAMA PRODUK");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 154, 85, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("HARGA SATUAN");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 154, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("JUMLAH BELI");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 154, -1, -1));

        cmdKeranjang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmdKeranjang.setText("BELI");
        cmdKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeranjangActionPerformed(evt);
            }
        });
        jPanel1.add(cmdKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(521, 174, 78, -1));

        txtKdProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdProdukActionPerformed(evt);
            }
        });
        txtKdProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKdProdukKeyReleased(evt);
            }
        });
        jPanel1.add(txtKdProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 175, 78, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("KEMBALI                      :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 446, -1, -1));
        jPanel1.add(txtNmProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 174, 195, 23));

        lblKembali.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblKembali.setText("..........................");
        jPanel1.add(lblKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 438, -1, -1));
        jPanel1.add(txtHrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 174, 88, 23));
        jPanel1.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 174, 73, 23));

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksi);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 203, 589, 125));

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotal.setText("..........................");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 346, -1, -1));

        txtNoJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoJualKeyReleased(evt);
            }
        });
        jPanel1.add(txtNoJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 47, 225, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("Rp.");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 408, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Rp.");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 354, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setText("Rp.");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 446, -1, -1));

        cmdKeranjang1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmdKeranjang1.setText("RESET");
        cmdKeranjang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeranjang1ActionPerformed(evt);
            }
        });
        jPanel1.add(cmdKeranjang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 46, 78, -1));
        jPanel1.add(jTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 0, 85, -1));

        tblSimpan1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblSimpan1.setText("CETAK");
        tblSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblSimpan1ActionPerformed(evt);
            }
        });
        jPanel1.add(tblSimpan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, 80, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKdProdukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKdProdukKeyReleased
        //memanggil fungsi data produk
        dataProduk();
    }//GEN-LAST:event_txtKdProdukKeyReleased

    private void tblSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblSimpanActionPerformed
        //memanggil fungsi simpan data transaksi
        simpanDataTransaksi();
    }//GEN-LAST:event_tblSimpanActionPerformed

    private void cmdKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeranjangActionPerformed
        //memanggil fungsi masuk tabel sementara
        masukTabel();
    }//GEN-LAST:event_cmdKeranjangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        this.dispose();   
        new menuUtama().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmdKeranjang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeranjang1ActionPerformed
        reset();    // TODO add your handling code here:
    }//GEN-LAST:event_cmdKeranjang1ActionPerformed

    private void txtKdProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKdProdukActionPerformed

    private void txtNoJualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoJualKeyReleased
     // TODO add your handling code here:
    }//GEN-LAST:event_txtNoJualKeyReleased

    private void tblSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblSimpan1ActionPerformed
        // TODO add your handling code here:
        new transaksiPegawai().setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_tblSimpan1ActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdKeranjang;
    private javax.swing.JButton cmdKeranjang1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTanggal;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JButton tblSimpan;
    private javax.swing.JButton tblSimpan1;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtHrg;
    private javax.swing.JTextField txtKdProduk;
    private javax.swing.JTextField txtNMpelanggan;
    private javax.swing.JTextField txtNmProduk;
    private javax.swing.JTextField txtNoJual;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
