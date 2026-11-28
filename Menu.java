import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "MS932");
        Employee yamada = new Employee(1, "山田太郎", Role.STAFF);
        Employee tanaka = new Employee(2, "田中部長", Role.MANAGER);

        List<Request> requests = new ArrayList<>();
        int nextID = 1;

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
                    System.out.println("品名：");
                    String itemName = scanner.next();
                    System.out.println("金額：");
                    int amount = scanner.nextInt();

                    Request newRequest = new Request(nextID, yamada, itemName, amount);
                    requests.add(newRequest);
                    nextID++;

                    System.out.println("申請(ID：" + newRequest.getID() + ")を作成しました（下書き）");
                    break;
                
                case 0:
                    running = false;
                    System.out.println("終了します");
                    break;

                case 2:
                    System.out.print("提出する申請のID：");
                    int id = scanner.nextInt();

                    for(Request r : requests){
                        if(r.getID() == id ){
                            try{
                                r.submit();
                                System.out.println("提出しました");
                            } catch(IllegalStateException e){
                                System.out.println("エラー：" + e.getMessage());
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.print("承認する申請のID：");
                    int approveId = scanner.nextInt();

                    for(Request r : requests){
                        if(r.getID() == approveId ){
                            try{
                                r.approve(tanaka);
                                System.out.println("承認しました");
                            } catch(IllegalStateException e){
                                System.out.println("エラー：" + e.getMessage());
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.print("差し戻す申請のID：");
                    int rejectedId = scanner.nextInt();

                    for(Request r : requests){
                        if(r.getID() == rejectedId ){
                            try{
                                r.reject(tanaka);
                                System.out.println("差し戻しました");
                            } catch(IllegalStateException e){
                                System.out.println("エラー：" + e.getMessage());
                            }
                        }
                    }
                    break;

                case 5:
                    System.out.print("完了にする申請のID：");
                    int completedId = scanner.nextInt();

                    for(Request r : requests){
                        if(r.getID() == completedId ){
                            try{
                                r.complete(tanaka);
                                System.out.println("完了にしました");
                            } catch(IllegalStateException e){
                                System.out.println("エラー：" + e.getMessage());
                            }
                        }
                    }
                    break;
                
                case 6:
                    if(requests.isEmpty()){
                        System.out.println("申請はまだありません");
                    } else {
                        for(Request r : requests){
                            System.out.println("ID：" + r.getID()
                            + " / " + r.getItemName()
                            + " / " + r.getAmount() + "円"
                            + " / " + r.getStatus().getLabel());
                        }
                    }
                    break;
                    default:
                        System.out.println("1~6か0を入力してください");
                    break;
            }
        } 

        scanner.close();
    }
}
