package Database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    private Connection conn = null;

    // Phương thức thực hiện kết nối CSDL
    public Connection connectSQL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=QLSanpham;encrypt=false;trustServerCertificate=true",
                "sa",
                "12345678"
            );
            System.out.println("Kết nối thành công!!!");
            return conn; // Trả về kết nối nếu thành công
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            return null; // Trả về null nếu không kết nối được
        }
    }

    // Phương thức để lấy kết nối
    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) { // Kiểm tra nếu chưa kết nối hoặc đã đóng
                return connectSQL(); // Gọi phương thức connectSQL() để tạo kết nối
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra kết nối: " + e.getMessage());
        }
        return conn; // Trả về kết nối nếu đã có
    }

    // Phương thức đóng kết nối
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Đóng kết nối thành công!");
            } catch (SQLException ex) {
                System.out.println("Lỗi khi đóng kết nối: " + ex.getMessage());
            }
        }
    }

    public ResultSet LoadData(String sql) {
        ResultSet rs = null;
        try {
            Connection connection = getConnection(); // Lấy kết nối
            if (connection != null) {
                Statement stmt = connection.createStatement(); // Tạo Statement
                rs = stmt.executeQuery(sql); // Thực thi câu lệnh SQL và lấy kết quả
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thực thi truy vấn: " + e.getMessage());
        }
        return rs; // Trả về ResultSet
    }
     // Hàm thực hiện cập nhật dữ liệu (INSERT, UPDATE, DELETE)
    public void UpdateData(String sql) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
    }

    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
