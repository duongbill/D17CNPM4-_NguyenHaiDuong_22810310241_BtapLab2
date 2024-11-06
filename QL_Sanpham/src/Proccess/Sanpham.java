package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sanpham {
  
    private String maSP;
    private String tenSP;
    private String dongia;
    private String maloai;

    // Getters and Setters
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }
    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }
    public String getDongia() { return dongia; }
    public void setDongia(String dongia) { this.dongia = dongia; }
    public String getMaloai() { return maloai; }
    public void setMaloai(String maloai) { this.maloai = maloai; }
    
    public Connect cn = new Connect();
    
    // Truy vấn tất cả dữ liệu trong Table LoaiSP
    public ResultSet ShowLoaiSP() throws SQLException {
        cn.connectSQL();
        String sql = "SELECT * FROM LoaiSP";
        return cn.LoadData(sql);
    }

    // Truy vấn các dòng dữ liệu trong Table LoaiSP theo Maloai
    public ResultSet ShowLoaiSP(String ml) throws SQLException {
        String sql = "SELECT * FROM LoaiSP WHERE Maloai='" + ml + "'";
        return cn.LoadData(sql);
    }

    // Truy vấn dữ liệu trong Table Sanpham theo Maloai
    public ResultSet ShowSPTheoloai(String ml) {
        String sql = "SELECT MaSP, TenSP, Dongia, L.Maloai, Tenloai FROM Sanpham S, LoaiSP L WHERE L.Maloai = S.Maloai AND L.Maloai='" + ml + "'";
        return cn.LoadData(sql);
    }

    // Truy vấn tất cả dữ liệu trong Table Sanpham
    public ResultSet ShowSanpham() throws SQLException {
        cn.connectSQL();
        String sql = "SELECT MaSP, TenSP, Dongia, L.Maloai, Tenloai FROM Sanpham S, LoaiSP L WHERE L.Maloai = S.Maloai";
        return cn.LoadData(sql);
    }

    // Truy vấn dữ liệu trong Table Sanpham theo MaSP
    public ResultSet ShowSPTheoma(String ma) throws SQLException {
        String sql = "SELECT MaSP, TenSP, Dongia, L.Maloai, Tenloai FROM Sanpham S, LoaiSP L WHERE L.Maloai = S.Maloai AND MaSP='" + ma + "'";
        return cn.LoadData(sql);
    }

    // Truy vấn dữ liệu trong Table Sanpham theo giá
    public ResultSet ShowSPTheogia(int giatu, int giaden) throws SQLException {
        String sql = "SELECT MaSP, TenSP, Dongia, L.Maloai, Tenloai FROM Sanpham S, LoaiSP L WHERE L.Maloai = S.Maloai AND Dongia BETWEEN " + giatu + " AND " + giaden;
        return cn.LoadData(sql);
    }

    // Truy vấn dữ liệu trong Table Sanpham theo tên
    public ResultSet ShowSPTheoten(String ten) throws SQLException {
        String sql = "SELECT MaSP, TenSP, Dongia, L.Maloai, Tenloai FROM Sanpham S, LoaiSP L WHERE L.Maloai = S.Maloai AND TenSP LIKE '%" + ten + "%'";
        return cn.LoadData(sql);
    }

    // Thêm một dòng dữ liệu vào table Sanpham
    public void InsertSanpham(String ma, String ten, int dg, String ml) throws SQLException {
        String sql = "INSERT INTO Sanpham VALUES ('" + ma + "', N'" + ten + "', " + dg + ", '" + ml + "')";
        cn.UpdateData(sql);
    }

    // Chỉnh sửa một dòng dữ liệu vào table Sanpham
    public void EditSanpham(String ma, String ten, int dg, String ml) throws SQLException {
        String sql = "UPDATE Sanpham SET TenSP = N'" + ten + "', Dongia = " + dg + ", Maloai = '" + ml + "' WHERE MaSP = '" + ma + "'";
        cn.UpdateData(sql);
    }

    // Xóa một dòng dữ liệu vào table Sanpham
    public void DeleteSanpham(String ma) throws SQLException {
        String sql = "DELETE FROM Sanpham WHERE MaSP = '" + ma + "'";
        cn.UpdateData(sql);
    }
    
     // Phương thức tìm kiếm sản phẩm theo tên
  public List<Sanpham> findAll() throws SQLException {
        String sql = "SELECT MaSP, TenSP, Dongia, Maloai FROM Sanpham";
        List<Sanpham> list = new ArrayList<>();
        try (Connection conn = cn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sanpham sp = new Sanpham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDongia(rs.getString("Dongia"));
                sp.setMaloai(rs.getString("Maloai"));
                list.add(sp);
            }
        }
        return list;
    }

    // Phương thức tìm kiếm sản phẩm theo tên
    public List<Sanpham> searchProductByName(String name) throws SQLException {
        String sql = "SELECT MaSP, TenSP, Dongia, Maloai FROM Sanpham WHERE LOWER(TenSP) LIKE LOWER(?)";
        List<Sanpham> list = new ArrayList<>();
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sanpham sp = new Sanpham();
                    sp.setMaSP(rs.getString("MaSP"));
                    sp.setTenSP(rs.getString("TenSP"));
                    sp.setDongia(rs.getString("Dongia"));
                    sp.setMaloai(rs.getString("Maloai"));
                    list.add(sp);
                }
            }
        }
        return list;
    }

    // Phương thức tìm kiếm sản phẩm theo tên với điều kiện tối ưu hóa SQL
    public List<Sanpham> searchProduct(String name) throws SQLException {
        if (name.trim().isEmpty()) {
            return findAll(); // Nếu không có tên tìm kiếm, trả về tất cả sản phẩm
        } else {
            return searchProductByName(name); // Tìm kiếm theo tên sản phẩm
        }
    }
    public List<Sanpham> searchProductByPrice(int minPrice, int maxPrice) throws SQLException {
    String sql = "SELECT MaSP, TenSP, Dongia, Maloai FROM Sanpham WHERE Dongia BETWEEN ? AND ?";
    List<Sanpham> list = new ArrayList<>();
    try (Connection conn = cn.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, minPrice);
        stmt.setInt(2, maxPrice);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sanpham sp = new Sanpham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDongia(rs.getString("Dongia"));
                sp.setMaloai(rs.getString("Maloai"));
                list.add(sp);
            }
        }
    }
        return list;
    }

}
