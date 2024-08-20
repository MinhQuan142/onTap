package org.example;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import org.example.database.mySql;
import org.example.util.InputLib;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.example.database.mySql.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department_Manager {
    private int department_id;
    private String department_name;
    private String department_description;

    //Phương thức Show Menu
    public static void departmentMenu() {
        System.out.println("-------------------Department Menu---------------");
        System.out.println("1. Danh sach phong ban.");
        System.out.println("2. Tao Phong Ban Moi");
        System.out.println("3. Cap Nhat Phong Ban");
        System.out.println("4. Xoa Phong Ban");
        System.out.println("5. Thong ke so luong nhan vien theo ma phong ban");
        System.out.println("0. Thoat");
        System.out.println("--------------------------------------------------");
        System.out.println("Nhap lua chon cua ban: ");
    }

    //Phương thức hiển thị Menu quản lý
    public static void departmentManager() {
        departmentMenu();
        while (true) {
            int choice = InputLib.inputNumber();
            switch (choice) {
                case 1:
                    System.out.println("Danh Sach Phong Ban:");
                    departmentShow();
                    departmentMenu();
                    break;
                case 2:
                    System.out.println("Tao moi phong ban");
                    departmentAdd();
                    break;
                case 3:
                    System.out.println("cap nhat phong ban");
                    departmentUpdate();
                    break;
                case 4:
                    System.out.println("xoa phong ban");
                    departmentMenu();
                    departmentDelete();
                    break;
                case 5:
                    System.out.println("thong ke phong ban");
                    departmentMenu();
                    break;
                case 0:
                    System.out.println("Thoat DEPARTMENT-MENU");
                    return;
                default:
                    break;
            }
        }
    }

    //Phương thức hiển thị toàn bộ phòng ban
    public static void departmentShow() {
        try {
            statement = mySql.connect.createStatement();
            //thuc thi cau lenh SQL
            String sql = "select * from department";
            ResultSet resultSet = statement.executeQuery(sql);
            //lap lai ket qua va hien thi
            while (resultSet.next()) {
                int department_id = resultSet.getInt("department_id");
                String department_name = resultSet.getString("department_name");
                String department_description = resultSet.getString("description");
                String is_deleted = resultSet.getString("is_deleted");
                System.out.println("[ ID: " + department_id
                        + " | Name: " + department_name
                        + " | Description: " + department_description
                        + " | IsDeleted: " + is_deleted
                        + " ]");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Phương thức thêm phnogf ban mới
    public static void departmentAdd() {
        System.out.println("=======Them Phong Ban========");

        try {
            Scanner scanner = new Scanner(System.in);
            Department_Manager department = new Department_Manager();

            System.out.println("Moi ban nhap ten phong ban moi");
            department.setDepartment_name(scanner.nextLine());
            System.out.println("Moi ban nhap mo ta phong ban");
            department.setDepartment_description(scanner.nextLine());

            // Câu lệnh SQL để thêm phòng ban mới vào cơ sở dữ liệu
            String query = "INSERT INTO department (department_name, description) VALUES (?, ?);";
            PreparedStatement pstmt = mySql.connect.prepareStatement(query);
            pstmt.setString(1, department.getDepartment_name());
            pstmt.setString(2, department.getDepartment_description());

            // Thực hiện câu lệnh SQL và kiểm tra kết quả
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Da them thanh cong phong ban moi");
                departmentMenu();
            } else {
                System.out.println("Them that bai");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Phương thức cập nhật phòng ban
    public static void departmentUpdate() {
        departmentShow();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ID phong ban ban muon cap nhat: ");
        int idUpdate = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        System.out.println("Nhap ten moi cho phong ban:");
        String nameUpdate = scanner.nextLine();
        System.out.println("Nhap mo ta moi cho phong ban:");
        String descriptionUpdate = scanner.nextLine();

        try {
            // Kiểm tra xem ID có tồn tại không
            String selectSql = "SELECT * FROM department WHERE department_id = ?";
            PreparedStatement selectStatement = mySql.connect.prepareStatement(selectSql);
            selectStatement.setInt(1, idUpdate);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Nếu ID tồn tại, thực hiện cập nhật
                String updateSql = "UPDATE department SET department_name = ?, description = ? WHERE department_id = ?";
                PreparedStatement updateStatement = mySql.connect.prepareStatement(updateSql);
                updateStatement.setString(1, nameUpdate);
                updateStatement.setString(2, descriptionUpdate);
                updateStatement.setInt(3, idUpdate);

                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Department voi ID -->>[ " + idUpdate + " ]<<-- da duoc cap nhat thanh cong.");
                } else {
                    System.out.println("Cap nhat that bai.");
                }
            } else {
                System.out.println("Department voi ID " + idUpdate + " khong ton tai.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        departmentShow();
    }

    //Phương thức xóa phòng ban
    public static void departmentDelete() {
        departmentShow();
        System.out.println("Nhập Id phòng bạn muốn xóa: ");
        Scanner scanner = new Scanner(System.in);
        int idDelete = scanner.nextInt();
        scanner.nextLine();
        try {
            String selectSql = "SELECT * FROM department WHERE department_id = ?";
            PreparedStatement selectStatement = mySql.connect.prepareStatement(selectSql);
            selectStatement.setInt(1, idDelete);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                String deleteSql = "DELETE FROM department WHERE department_id = ?";
                PreparedStatement deleteStatement = mySql.connect.prepareStatement(deleteSql);
                deleteStatement.setInt(1, idDelete);
                int rowsAffected = deleteStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Phòng với ID là " + idDelete + " đã được xóa thanh cong.");
                } else {
                    System.out.println("Không tìm thấy phòng ban có Id là: " + idDelete);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        departmentShow();
        departmentMenu();
    }
}
