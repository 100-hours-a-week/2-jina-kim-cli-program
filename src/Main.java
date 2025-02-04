import bank_program.Account;
import bank_program.CheckingAccount;
import bank_program.SavingAccount;
import bank_program.StudentAccount;

import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Account> accounts = new HashMap<>();  // 계좌 목록을 저장할 Map

        while (true) {
            // 메뉴
            System.out.println("\n*** 메뉴 ***");
            System.out.println("1. 계좌 생성");
            System.out.println("2. 계좌 선택");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // 버퍼 비우기

            if (choice == 1) {
                // 계좌 생성
                System.out.print("계좌 번호를 입력하세요: ");
                String accountNumber = scanner.nextLine();

                // 계좌가 이미 존재하는지 체크
                if (accounts.containsKey(accountNumber)) {
                    System.out.println("이미 존재하는 계좌 번호입니다.");
                    continue;
                }

                System.out.print("소유자 이름을 입력하세요: ");
                String owner = scanner.nextLine();

                System.out.print("초기 잔액을 입력하세요: ");
                double initialBalance = scanner.nextDouble();
                scanner.nextLine();

                // 계좌 타입 선택
                System.out.println("계좌 타입을 선택하세요: ");
                System.out.println("1. 일반 계좌");
                System.out.println("2. 저축 계좌");
                System.out.println("3. 학생 계좌");

                int accountType = scanner.nextInt();
                scanner.nextLine();

                Account account = null;

                if (accountType == 1) {
                    account = new CheckingAccount(accountNumber, owner, initialBalance);
                } else if (accountType == 2) {
                    account = new SavingAccount(accountNumber, owner, initialBalance);
                    System.out.println("저축 계좌 이자율은 2%입니다.");
                } else if (accountType == 3) {
                    account = new StudentAccount(accountNumber, owner, initialBalance);
                    System.out.println("학생 계좌는 저축 계좌이며, 저축 계좌 이자율은 2%입니다.");
                } else {
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }

                accounts.put(accountNumber, account);
                System.out.println("계좌가 생성되었습니다. 환영합니다!");

            } else if (choice == 2) {
                // 계좌 선택
                System.out.print("사용할 계좌 번호를 입력하세요: ");
                String accountNumber = scanner.nextLine();

                if (!accounts.containsKey(accountNumber)) {
                    System.out.println("해당 계좌 번호가 존재하지 않습니다.");
                    continue;
                }

                Account account = accounts.get(accountNumber);  // 선택한 계좌

                // 계좌에 대한 입출금 및 잔액 확인 메뉴
                while (true) {
                    System.out.println("\n*** 계좌 메뉴 ***");
                    System.out.println("1. 입금");
                    System.out.println("2. 출금");
                    System.out.println("3. 잔액 확인");
                    System.out.println("4. 계좌 선택으로 돌아가기");
                    System.out.print("선택: ");
                    int subChoice = scanner.nextInt();
                    scanner.nextLine();  // 버퍼 비우기

                    if (subChoice == 1) {
                        System.out.print("입금할 금액을 입력하세요: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    } else if (subChoice == 2) {
                        System.out.print("출금할 금액을 입력하세요: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    } else if (subChoice == 3) {
                        account.checkBalance();
                    } else if (subChoice == 4) {
                        break;  // 계좌 선택 메뉴로 돌아가기
                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                }

            } else if (choice == 3) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }

        scanner.close();
    }
}