package hoangkhanh.models;

public class HoDan {
	private String maHoDan;
	private int soNha;
	private int soThanhVien;
	private String maKhuPho;
	
	public HoDan() {
		super();
	}
	
	public HoDan(String maHoDan, int soNha, int soThanhVien, String maKhuPho) {
		super();
		this.maHoDan = maHoDan;
		this.soNha = soNha;
		this.soThanhVien = soThanhVien;
		this.maKhuPho = maKhuPho;
	}
	
	public String getMaHoDan() {
		return maHoDan;
	}
	public void setMaHoDan(String maHoDan) {
		this.maHoDan = maHoDan;
	}
	public int getSoNha() {
		return soNha;
	}
	public void setSoNha(int soNha) {
		this.soNha = soNha;
	}
	public int getSoThanhVien() {
		return soThanhVien;
	}
	public void setSoThanhVien(int soThanhVien) {
		this.soThanhVien = soThanhVien;
	}
	public String getMaKhuPho() {
		return maKhuPho;
	}
	public void setMaKhuPho(String maKhuPho) {
		this.maKhuPho = maKhuPho;
	}

	@Override
	public String toString() {
		return "HoDan [maHoDan=" + maHoDan + ",soNha=" + soNha + " ,soThanhVien=" + soThanhVien + ",maKhuPho=" + maKhuPho + "]";
	}
	
}
