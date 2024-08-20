package org.example.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputLib {
    public static String inputString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    public static Date getDay() {
        Scanner in = new Scanner(System.in);
        String input;
        java.util.Date utilDay = null;
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        System.out.println("\nPlease enter the date of the appointment, format: yyyy-mm-dd");
        while (utilDay == null) {
            try {
                input = in.next();
                utilDay = (java.util.Date) df.parse(input);
            } catch (ParseException e) {
                System.out.println("Please enter a valid date! Format is yyyy-mm-dd");
            }
        }

        java.sql.Date sqlDate = new java.sql.Date(utilDay.getTime());
        return sqlDate;
    }

    public static String inputEmail() {
        System.out.println("Vui lòng nhập Email");
        while (true) {
            try {
                String email = inputString();

                // Kiểm tra định dạng email hợp lệ
                if (isValidEmail(email)) {
                    return email;
                } else {
                    throw new Error("Email không hợp lệ. Vui lòng thử lại.");
                }
            } catch (Error err) {
                System.out.println(err);
            }
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Dùng để nhập mật khẩu khi đăng ký (yêu cầu nhập lại để xác nhận)
    public static String enterPassword() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập mật khẩu:");
            String password1 = inputString();
            if (password1.length() < 6 || password1.length() > 30) {
                System.out.println("Password phải từ 6 -> 30 ký tự");
                continue;
            }

            System.out.println("Vui lòng nhập lại mật khẩu một lần nữa:");
            String password2 = inputString();

            // Kiểm tra nếu hai mật khẩu giống nhau
            if (password1.equals(password2)) {
                return password1;
            } else {
                System.out.println("Mật khẩu không khớp. Vui lòng thử lại.");
            }
        }
    }

    // Dùng để nhập mật khẩu khi đăng nhập
    public static String inputPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mật khẩu để đăng nhập:");
        return inputString();
    }

    //Phone ckeck
    public static String inputPhone() {
        System.out.println("Nhập so dien thoai theo hướng dẫn sau: \n điện thoại di động của Việt Nam có định dạng như sau:\n" +
                "\n" +
                "Bắt đầu bằng 0 hoặc +84.\n" +
                "Theo sau là một đầu số di động hợp lệ (bắt đầu với các số 3, 5, 7, 8, hoặc 9 và theo sau là các số cụ thể).\n" +
                "Phần tiếp theo là 7 chữ số, có thể được ngăn cách bằng khoảng trắng hoặc dấu chấm.\n" +
                "Ví dụ các số hợp lệ:\n" +
                "\n" +
                "0912345678\n" +
                "+84912345678\n" +
                "093 123 4567\n" +
                "+84 93.123.4567\n" +
                "Hay nhap so dien thoại");
        while (true) {
            try {
                String phoneNumber = inputString();

                // Kiểm tra định dạng Phone Number hợp lệ
                if (isValidPhone(phoneNumber )) {
                    return phoneNumber;
                } else {
                    throw new Error("So dien thoai không hợp lệ. Vui lòng thử lại.");
                }
            } catch (Error err) {
                System.out.println(err);
            }
        }
    }
// biểu thức chính quy check phone number
    private static boolean isValidPhone(String phoneNumber) {
        String emailRegex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
