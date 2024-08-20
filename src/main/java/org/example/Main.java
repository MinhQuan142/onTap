package org.example;
import org.example.util.InputLib;
public class Main {
    public static void showMenu() {
        System.out.println("--------------------Menu---------------------");
        System.out.println("1. Quan Ly Phong Ban.");
        System.out.println("2. Quan Ly Nhan Vien");
        System.out.println("0. Thoat");
        System.out.println("----------------------------------------------");
        System.out.println("Nhap lua chon cua ban: ");
    }
    public static void main(String[] args) {

        while (true) {
            showMenu();

            int choice = InputLib.inputNumber();
            switch (choice) {
                case 1:
                    Department_Manager.departmentManager();
                    break;
                case 2:
                    Employee_Manager.employeeManager();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh");
                    return;
                default:
                    System.out.println("lua chon khong ton tai.");
            }

        }
    }
}