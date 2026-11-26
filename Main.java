public class Main {
    public static void main(String[] args) {
        Employee yamada = new Employee(1, "山田太郎", Role.STAFF);
        Employee tanaka = new Employee(2, "田中部長", Role.MANAGER);

        Request req = new Request(1, yamada, "ノートPC", 120000);
        req.submit();

        // 一般社員が承認しようとする → エラー
        try {
            req.approve(yamada);
        } catch (IllegalStateException e) {
            System.out.println("エラー: " + e.getMessage());
        }

        // 上長が承認する → 成功
        req.approve(tanaka);
        System.out.println("状態: " + req.getStatus().getLabel());

                // 差し戻しのテスト
        Request req2 = new Request(2, yamada, "モニター", 30000);
        req2.submit();

        try {
            req2.reject(yamada);   // 一般社員 → エラーになるはず
        } catch (IllegalStateException e) {
            System.out.println("エラー: " + e.getMessage());
        }

        req2.reject(tanaka);       // 上長 → 成功するはず
        System.out.println("状態: " + req2.getStatus().getLabel());

        Request req3 = new Request(3, yamada, "キーボード", 5000);
        try {
            req3.reject(tanaka);
        } catch (IllegalStateException e) {
            System.out.println("エラー: " + e.getMessage());
        }

        Request req4 = new Request(4, tanaka, "プリンター", 40000);
        req4.submit();
        try {
            req4.approve(tanaka);   // 自分の申請を自分で承認 → エラーになるはず
        } catch (IllegalStateException e) {
            System.out.println("エラー: " + e.getMessage());
        }

        try {
            req4.reject(tanaka);   // 自分の申請を自分で差し戻す → エラーになるはず
        } catch (IllegalStateException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }
}