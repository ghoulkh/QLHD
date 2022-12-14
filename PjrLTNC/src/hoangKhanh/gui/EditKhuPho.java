package quanghuy.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import quanghuy.controllers.KhuPhoController;
import quanghuy.models.HoDan;
import quanghuy.models.KhuPho;
import quanghuy.models.Nguoi;
import quanghuy.utils.ConnectDB;
import javax.swing.JList;

public class EditKhuPho extends JFrame {
	
	JTextField txtMaKhuPho, txtName, txtMaHoDan, txtSoNha, txtMaNguoi, txtHoVaTen, txtTuoi, txtNamSinh, txtNgheNghiep;
	JButton btnLuuHoDan, btnLuuNguoi, btnHoDanTruoc, btnHoDanSau, btnThanhVienTruoc, btnThanhVienSau;
	JButton btnBack, btnSaveAll;
	DefaultTableModel dtmTable ;
	JTable tblPeople;
	
	String maKhuPho;
	KhuPhoController kpController =new KhuPhoController();
	
	List<HoDan> listHD = new ArrayList<>(); 
	KhuPho kp = new KhuPho();
	List<Nguoi> ListNg = new ArrayList<>();
	
	public EditKhuPho(String title, String ma) {
		super(title);
		this.maKhuPho = ma;
		listHD = kpController.getHoDanVoiMaKhuPho(this.maKhuPho); 
		kp = kpController.getKhuPhoVoiMaKhuPho(this.maKhuPho);
		ListNg = kpController.getNguoiVoiMaHoDan(listHD.get(0).getMaHoDan());
		
		addControls();

		addEvents();
		
		setDanhSachNguoi();
		
	}

	// =================================================================
	
	
	public void showWindow() {
		this.setSize(700, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	// =================================================================

	private void setDanhSachNguoi() {
			dtmTable.setRowCount(0);
			for(Nguoi ng : ListNg ) {
				
				Vector<Object> vec = new Vector<>();
				vec.add(ng.getMaNguoi());
				vec.add(ng.getHoVaTen());
				vec.add(ng.getTuoi());
				vec.add(ng.getNamSinh());
				vec.add(ng.getNgheNghiep());
				
				dtmTable.addRow(vec);
			}

	}

//=====================================================================================
	
	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		
		JPanel pnDetail = new JPanel();
		pnDetail.setLayout(new BoxLayout(pnDetail, BoxLayout.X_AXIS));

		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
		
		JPanel pnRight = new JPanel();
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

		//pnLeft
		JPanel pnMaKhu = new JPanel();
		JLabel lblMaKhu = new JLabel("Ma?? Khu Ph???: ");
		txtMaKhuPho = new JTextField(25);
		txtMaKhuPho.setText(kp.getMaKhuPho());
		txtMaKhuPho.setEditable(false);
		pnMaKhu.add(lblMaKhu);
		pnMaKhu.add(txtMaKhuPho);
		pnLeft.add(pnMaKhu);

		JPanel pnName = new JPanel();
		JLabel lblName = new JLabel("T??n Khu Ph???: ");
		txtName = new JTextField(25);
		txtName.setText(kp.getTenKhuPho());
		pnName.add(lblName);
		pnName.add(txtName);
		pnLeft.add(pnName);

		JPanel pnHoDan = new JPanel();
		pnHoDan.setLayout(new BoxLayout(pnHoDan, BoxLayout.Y_AXIS));
		
		JPanel pnDan = new JPanel();
		JLabel lblDan = new JLabel("M?? h??? d??n: ");
		txtMaHoDan = new JTextField(25);
		txtMaHoDan.setText(listHD.get(0).getMaHoDan());
		txtMaHoDan.setEditable(false);
		pnDan.add(lblDan);
		pnDan.add(txtMaHoDan);
		pnHoDan.add(pnDan);

		JPanel pnNha = new JPanel();
		JLabel lblNha = new JLabel("S???? Nh??: ");
		txtSoNha = new JTextField(25);
		txtSoNha.setText(String.valueOf(listHD.get(0).getSoNha()));
		pnNha.add(lblNha);
		pnNha.add(txtSoNha);
		pnHoDan.add(pnNha);
		
		JPanel pnLuuHoDan = new JPanel();
		btnLuuHoDan = new JButton("L??u");
		pnLuuHoDan.add(btnLuuHoDan);
		pnHoDan.add(pnLuuHoDan);
		
		JPanel pnDanButton = new JPanel();
		btnHoDanTruoc = new JButton("HD tr?????c");
		btnHoDanSau = new JButton("HD Sau");
		pnDanButton.add(btnHoDanTruoc);
		pnDanButton.add(btnHoDanSau);
		pnHoDan.add(pnDanButton);

		lblMaKhu.setPreferredSize(lblName.getPreferredSize());
		lblNha.setPreferredSize(lblName.getPreferredSize());
		lblDan.setPreferredSize(lblName.getPreferredSize());
		
		pnHoDan.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GREEN), "Th??ng tin h??? d??n"));
		pnLeft.add(pnHoDan);
		pnDetail.add(pnLeft);
		
		//pnRight
		JPanel pn1 = new JPanel();
		JLabel lblMaNguoi = new JLabel("M?? ng?????i: ");
		txtMaNguoi = new JTextField(25);
		txtMaNguoi.setText(ListNg.get(0).getMaNguoi());
		txtMaNguoi.setEditable(false);
		pn1.add(lblMaNguoi);
		pn1.add(txtMaNguoi);
		pnRight.add(pn1);

		JPanel pn2 = new JPanel();
		JLabel lblTen = new JLabel("H??? v?? t??n: ");
		txtHoVaTen = new JTextField(25);
		txtHoVaTen.setText(ListNg.get(0).getHoVaTen());
		pn2.add(lblTen);
		pn2.add(txtHoVaTen);
		pnRight.add(pn2);
		
		JPanel pn3 = new JPanel();
		JLabel lblTuoi = new JLabel("Tu???i: ");
		txtTuoi = new JTextField(25);
		txtTuoi.setText(String.valueOf(ListNg.get(0).getTuoi()));
		pn3.add(lblTuoi);
		pn3.add(txtTuoi);
		pnRight.add(pn3);
		
		JPanel pn4 = new JPanel();
		JLabel lblN = new JLabel("N??m Sinh: ");
		txtNamSinh = new JTextField(25);
		txtNamSinh.setText(String.valueOf(ListNg.get(0).getNamSinh()));
		pn4.add(lblN);
		pn4.add(txtNamSinh);
		pnRight.add(pn4);
		
		JPanel pn5 = new JPanel();
		JLabel lblNghe = new JLabel("Ngh??? nghi???p: ");
		txtNgheNghiep = new JTextField(25);
		txtNgheNghiep.setText(ListNg.get(0).getNgheNghiep());
		pn5.add(lblNghe);
		pn5.add(txtNgheNghiep);
		pnRight.add(pn5);
		
		JPanel pnLuuNguoi = new JPanel();
		btnLuuNguoi = new JButton("L??u");
		pnLuuNguoi.add(btnLuuNguoi);
		pnRight.add(pnLuuNguoi);
		
		JPanel pnNguoiButton = new JPanel();
		btnThanhVienTruoc = new JButton("Th??nh vi??n tr?????c");
		btnThanhVienSau = new JButton("Th??nh vi??n sau");
		pnNguoiButton.add(btnThanhVienTruoc);
		pnNguoiButton.add(btnThanhVienSau);
		pnRight.add(pnNguoiButton);
		
		lblMaNguoi.setPreferredSize(lblNghe.getPreferredSize());
		lblTuoi.setPreferredSize(lblNghe.getPreferredSize());
		lblN.setPreferredSize(lblNghe.getPreferredSize());
		lblTen.setPreferredSize(lblNghe.getPreferredSize());
		
		pnRight.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Th??ng tin th??nh vi??n"));
		pnDetail.add(pnRight);
		//pnNorth
		TitledBorder bdrDetailInfor = new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Th??ng tin khu ph???");
		pnDetail.setBorder(bdrDetailInfor);
		pnNorth.add(pnDetail, BorderLayout.CENTER);

		//pnCenter
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		dtmTable = new DefaultTableModel();
		dtmTable.addColumn("Ma?? Ng?????i");
		dtmTable.addColumn("H??? v?? T??n");
		dtmTable.addColumn("Tu???i");
		dtmTable.addColumn("N??m sinh");
		dtmTable.addColumn("Ngh??? nghi???p");
		tblPeople = new JTable(dtmTable);
		JScrollPane sc = new JScrollPane(tblPeople, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(sc, BorderLayout.CENTER);

		TitledBorder bdrTable = new TitledBorder(BorderFactory.createLineBorder(Color.YELLOW),
				"Danh sa??ch c??ng d??n");
		pnCenter.setBorder(bdrTable);

		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		JPanel pnSouthButton = new JPanel();
		pnSouthButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnSaveAll = new JButton("Save");
		btnBack = new JButton("Back");
		pnSouthButton.add(btnSaveAll);
		pnSouthButton.add(btnBack);
		
		pnSouth.add(pnSouthButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		panel.setBounds(0, 500, 997, 30);
		JLabel lblFromSeasideTo = new JLabel("B??i thi cu???i k??? LTNC - L?? Quang Huy - 20182578");
		lblFromSeasideTo.setForeground(new Color(240, 255, 255));
		lblFromSeasideTo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		panel.add(lblFromSeasideTo);
		
		pnSouth.add(panel);

		con.add(pnNorth, BorderLayout.NORTH);
		con.add(pnCenter, BorderLayout.CENTER);
		con.add(pnSouth, BorderLayout.SOUTH);
	}

//=================================================================
	Connection conn2 = ConnectDB.getConnection();
	PreparedStatement preStm = null;

	private void addEvents() {
		btnSaveAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				JOptionPane.showMessageDialog(null, "S???a khu ph??? th??nh c??ng", "Th??ng b??o",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//InsertService is = new InsertService();
				EditKhuPho.this.dispose();
			}
		});

		btnLuuHoDan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		btnLuuNguoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String selectedMaNXB = tblPeople.getValueAt(tblPeople.getSelectedRow(), 0) + "";

				try { // thao ta??c v????i db c????n try catch block
					// String sql = "delete from tblpublisher where ma ='"+selectedMaNXB+"'";
					String sql = "delete from tblpublisher where MaNXB = ?";
					preStm = conn2.prepareStatement(sql);
					preStm.setString(1, selectedMaNXB);
					ResultSet rs = preStm.executeQuery();
					// int ketqua = stm.executeUpdate(sql); //tra?? v???? s???? do??ng bi?? a??nh h??????ng,
					// dung statement binh thuong
//					if (rs != null) {
//						showAllPeople();
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

}
