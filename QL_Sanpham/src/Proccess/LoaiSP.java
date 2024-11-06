package Proccess;

import java.sql.*;
import Database.Connect;
import java.util.ArrayList;
import java.util.List;

public class LoaiSP {
    private String maloai; // Mã loại
    private String tenloai; // Tên loại

    private String LoaiSP;
    private String Sanpham;

    // Getter và Setter cho LoaiSP
    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    // Getter và Setter cho Sanpham
    public String getSanpham() {
        return Sanpham;
    }

    public void setSanpham(String Sanpham) {
        this.Sanpham = Sanpham;
    }
    // Getter và Setter cho maloai
    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    // Getter và Setter cho tenloai
    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    
    public Connect cn = new Connect();

   
      public List<LoaiSP> getALLLoaiSP() throws SQLException {
        String sql = "SELECT * FROM LoaiSP";
        List<LoaiSP> list = new ArrayList<>();
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LoaiSP loaiSP = new LoaiSP();
                loaiSP.setMaloai(rs.getString("Maloai")); // Thiết lập mã loại từ kết quả
                loaiSP.setTenloai(rs.getString("Tenloai")); // Thiết lập tên loại từ kết quả
                list.add(loaiSP);
            }
        }
        return list;
    }

       // Truy vấn các dòng dữ liệu trong Table LoaiSP theo Maloai
public LoaiSP getLoaiSP(String ml) throws SQLException {
    String sql = "SELECT * FROM LoaiSP WHERE Maloai=?";
    LoaiSP loaiSP = null;

    try (Connection conn = cn.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, ml);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                loaiSP = new LoaiSP();
                loaiSP.setMaloai(rs.getString("Maloai"));
                loaiSP.setTenloai(rs.getString("Tenloai"));
            }
        }
    } 
    return loaiSP;
}


    public boolean InsertData(LoaiSP obj) throws SQLException {
        String sql = "INSERT INTO LoaiSP (Maloai, Tenloai) VALUES (?, ?)";
    try (Connection conn = cn.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, obj.getMaloai());
        stmt.setString(2, obj.getTenloai());
        int rowsAffected = stmt.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected); // Kiểm tra số dòng bị ảnh hưởng
        return rowsAffected > 0;
        }
    }


    public boolean EditData(LoaiSP obj) throws SQLException {
        String sql = "UPDATE LoaiSP SET Tenloai = ? WHERE Maloai = ?";
    try (Connection conn = cn.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, obj.getTenloai());
        stmt.setString(2, obj.getMaloai());
        int rowsAffected = stmt.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected); // Kiểm tra số dòng bị ảnh hưởng
        return rowsAffected > 0;
        }
    }


    public boolean DeleteData(String ml) throws SQLException {
        String sql = "Delete from LoaiSP where Maloai=?";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ml);
            return stmt.executeUpdate() > 0;
        }
    }


    // Phương thức để lấy danh sách sản phẩm theo loại
    public ResultSet ShowSPTheoloai(String ml) {
        String sql = "SELECT MaSP, TenSP, Dongia, Tenloai FROM Sanpham S, LoaiSP L WHERE L.Maloai = S.Maloai AND L.Maloai = '" + ml + "'";
        return cn.LoadData(sql);
    }

    // Phương thức để lấy danh sách loại sản phẩm
    public ResultSet ShowLoaiSP() {
        ResultSet result = null;
        String sql = "SELECT Maloai FROM LoaiSP";
        try {
            // Gọi phương thức getConnection() để lấy kết nối từ lớp Connect
            Connection conn = cn.getConnection();
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery(sql); // Thực hiện truy vấn và lưu kết quả vào result
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra màn hình console nếu có lỗi
        }
        return result; // Trả về kết quả truy vấn
    }
}
