import java.util.Scanner;

public class Menu {
    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println();
            System.out.println("==== 備品購入申請システム ====");
            System.out.println("1. 新規申請を作る");
            System.out.println("2. 申請を提出する");
            System.out.println("3. 承認する");
            System.out.println("4. 差し戻す");
            System.out.println("5. 完了にする");
            System.out.println("6. 申請一覧を見る");
            System.out.println("0. 終了");
            System.out.println("番号を入力してください: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("(新規申請は、次の段階で作ります)");
                    break;
                
                case 0:
                    running = false;
                    System.out.println("終了します");
                    break;

                default:
                    System.out.println("1~6か0を入力してください");
                    break;
            }
        } 

        scanner.close();
    }
}
