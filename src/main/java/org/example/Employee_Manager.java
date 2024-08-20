package org.example;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.database.mySql;
import org.example.util.InputLib;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static org.example.database.mySql.statement;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee_Manager {
    private int employee_id;
    private String employee_first_name;
    private String employee_last_name;
    private String date_of_birth;
    private String employee_phone;
    private String employee_address;
    private String salary;

    //Phương thức Show Menu
    public static void employeeMenu() {
        System.out.println("-------------------Empoyee Menu---------------");
        System.out.println("1. Danh sach nhan vien.");
        System.out.println("2. Tao nhan vien Moi");
        System.out.println("3. Cap Nhat nhan vien");
        System.out.println("4. Xoa nhan vien");
        System.out.println("5. Hien thi nhien vien theo tuoi giam dan");
        System.out.println("6. Tim kiem nhan vien theo ten hoac tuoi");
        System.out.println("0. Thoat");
        System.out.println("----------------------------------------------");
        System.out.println("Nhap lua chon cua ban: ");


    }

    //Phương thứ hiển thị Menu quản lý
    public static void employeeManager() {
        employeeMenu();
        while (true) {
//            Scanner input = new Scanner(System.in);
//            int choice = input.nextInt();
            int choice = InputLib.inputNumber();
            switch (choice) {
                case 1:
                    employeeShow();
                    employeeMenu();
                    break;
                case 2:
                    System.out.println("===========Thêm nhân Viên mới==================");
                    employeeAdd();

                    employeeMenu();
                    break;
                case 3:

                    employeeUpdate();
                    break;
                case 4:
                    System.out.println("Xóa nhân viên");
                    employeeDelete();
                    break;
                case 5:
                    System.out.println("Xắp sếp nhân nhiên");
                    break;
                case 6:
                    System.out.println("Tìm kiếm nhân viên");
                    employeeSearch();
                    break;
                case 0:
                    System.out.println("THOAT EMPLOYEE-MENU");
                    return;
                default:
                    break;
            }
        }
    }

    // Phương thức hiện thị toàn bộ nhân viên
    public static void employeeShow() {
        System.out.println("                       ==================Danh Sách nhân viên=================                           ");
        try {
            statement = mySql.connect.createStatement();
            //thuc thi cau lenh SQL
            String sql = "select * from employee";
            ResultSet resultSet = statement.executeQuery(sql);
            //lap lai ket qua va hien thi
            while (resultSet.next()) {
                int employee_id = resultSet.getInt("employee_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String date_of_birth = resultSet.getString("date_of_birth");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String salary = resultSet.getString("salary");
                String created_at = resultSet.getString("created_at");
                String update_at = resultSet.getString("update_at");
                String is_deleted = resultSet.getString("is_deleted");

                System.out.println("[ ID: " + employee_id
                        + " | Name: " + first_name + " " + last_name
                        + " | Ngày tháng năm sinh: " + date_of_birth
                        + " | Phone: " + phone
                        + " | Address: " + address
                        + " | salary: " + salary
                        + " | Ngày khỏi tạo: " + created_at
                        + " | Ngày Update: " + update_at
                        + " | Is Deleted: " + is_deleted
                        + " ]");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("                       =================================================                                           ");
    }

    //Phương thức thêm nhân viên
    public static void employeeAdd() {
        try {
            Scanner scanner = new Scanner(System.in);
            Employee_Manager employee = new Employee_Manager();
            System.out.println("Mời bạn nhập Họ và tên đệm của nhân viên: ");
            employee.setEmployee_first_name(scanner.nextLine());
            System.out.println("Mời bạn Tên nhân viên: ");
            employee.setEmployee_last_name(scanner.nextLine());
            employee.setDate_of_birth(String.valueOf(InputLib.getDay()));
            System.out.println("Phone: ");
            employee.setEmployee_phone(String.valueOf(InputLib.inputPhone()));
            System.out.println("Address: ");
            employee.setEmployee_address(scanner.nextLine());
            System.out.println("Salary: ");
            employee.setSalary(String.valueOf(InputLib.inputNumber()));
            // Câu lệnh SQL để thêm phòng ban mới vào cơ sở dữ liệu
            String query =
                    "INSERT INTO employee (first_name, last_name, date_of_birth, phone, address, salary  ) VALUES (?, ?,?, ?,?, ?);";
            PreparedStatement pstmt = mySql.connect.prepareStatement(query);
            pstmt.setString(1, employee.getEmployee_first_name());
            pstmt.setString(2, employee.getEmployee_last_name());
            pstmt.setString(3, employee.getDate_of_birth());
            pstmt.setString(4, employee.getEmployee_phone());
            pstmt.setString(5, employee.getEmployee_address());
            pstmt.setString(6, employee.getSalary());

            // Thực hiện câu lệnh SQL và kiểm tra kết quả
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Đã thêm thành công nhân viên mới");
                employeeShow();
            } else {
                System.out.println("Thêm thất bại");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //phương thức cập nhật thông tin nhân viên
    public static void employeeUpdate() {
        employeeShow();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ID Nhân Viên bạn muốn cập nhật: ");
        int idUpdate = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        System.out.println("Nhập họ và tên đệm:");
        String first_name_Update = scanner.nextLine();
        System.out.println("Nhập Tên:");
        String last_name_Update = scanner.nextLine();
        System.out.println("Nhập ngày tháng năm sinh theo mẫu: yyyy-mm-dd :");
        String date_of_birth_Update = String.valueOf(InputLib.getDay());
        System.out.println("Số Điện Thoại:");
        String phoneUpdate = String.valueOf(InputLib.inputPhone());
        System.out.println("Địa chỉ:");
        String address_Update = scanner.nextLine();
        System.out.println("Lương");
        String salary_Update = String.valueOf(InputLib.inputNumber());
        try {
            // Kiểm tra xem ID có tồn tại không
            String selectSql = "SELECT * FROM employee WHERE employee_id = ?";
            PreparedStatement selectStatement = mySql.connect.prepareStatement(selectSql);
            selectStatement.setInt(1, idUpdate);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                // Nếu ID tồn tại, thực hiện cập nhật
                String updateSql =
                        "UPDATE employee SET first_name = ?, last_name = ?, date_of_birth = ?, phone = ?, address = ?, salary = ? WHERE employee_id = ?";
                PreparedStatement updateStatement = mySql.connect.prepareStatement(updateSql);
                updateStatement.setString(1, first_name_Update);
                updateStatement.setString(2, last_name_Update);
                updateStatement.setString(3, date_of_birth_Update);
                updateStatement.setString(4, phoneUpdate);
                updateStatement.setString(5, address_Update);
                updateStatement.setString(6, salary_Update);
                updateStatement.setInt(7, idUpdate);
                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Nhân viên voi ID -->>[ " + idUpdate + " ]<<-- da duoc cap nhat thanh cong.");
                } else {
                    System.out.println("Cap nhat that bai.");
                }
            } else {
                System.out.println("nhân viên voi ID " + idUpdate + " khong ton tai.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        employeeShow();
    }

    //Phương thức xóa nhân viên
    public static void employeeDelete() {
        employeeShow();
        System.out.println("Nhập Id nhân viên bạn muốn xóa: ");
        Scanner scanner = new Scanner(System.in);
        int idDelete = scanner.nextInt();
        scanner.nextLine();
        try {
            String selectSql = "SELECT * FROM employee WHERE employee_id = ?";
            PreparedStatement selectStatement = mySql.connect.prepareStatement(selectSql);
            selectStatement.setInt(1, idDelete);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                String deleteSql = "DELETE FROM employee WHERE employee_id = ?";
                PreparedStatement deleteStatement = mySql.connect.prepareStatement(deleteSql);
                deleteStatement.setInt(1, idDelete);
                int rowsAffected = deleteStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Nhân Viên với ID là " + idDelete + " đã được xóa thanh cong.");
                } else {
                    System.out.println("Không tìm thấy Nhân Viên có Id là: " + idDelete);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        employeeShow();
    }

    // Phương thức tìm kiếm nhân viên theo họ hoặc tên
    public static void employeeSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên hoặc họ của nhân viên cần tìm kiếm: ");
        String name = scanner.nextLine();

        try {
            // Câu lệnh SQL để tìm kiếm nhân viên theo họ hoặc tên
            String query = "SELECT * FROM employee WHERE first_name LIKE ? OR last_name LIKE ?";
            PreparedStatement pstmt = mySql.connect.prepareStatement(query);
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + name + "%");

            ResultSet resultSet = pstmt.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Không tìm thấy nhân viên nào.");
            } else {
                System.out.println("Danh sách nhân viên trùng với từ khóa:");
                while (resultSet.next()) {
                    int employee_id = resultSet.getInt("employee_id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    String phone = resultSet.getString("phone");
                    String address = resultSet.getString("address");
                    double salary = resultSet.getDouble("salary");

                    System.out.println("[ ID: " + employee_id
                            + " Name: " + first_name
                            + " " + last_name
                            + " | Phone: " + phone
                            + " | Address: " + address
                            + " | Salary: " + salary
                            + " ]");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
