public class Main {
    public static void main(String[] args) {

        // --- 正常な流れ:申請 → 承認 → 完了 ---
        System.out.println("【正常な流れ】");
        Request req1 = new Request(1, "山田太郎", "ノートPC", 120000);
        System.out.println("状態: " + req1.getStatus().getLabel());

        req1.submit();
        System.out.println("状態: " + req1.getStatus().getLabel());

        req1.approve();
        System.out.println("状態: " + req1.getStatus().getLabel());

        req1.complete();
        System.out.println("状態: " + req1.getStatus().getLabel());

        // --- 差し戻しの流れ:申請 → 差し戻し → 再申請 ---
        System.out.println();
        System.out.println("【差し戻しの流れ】");
        Request req2 = new Request(2, "佐藤花子", "モニター", 30000);
        req2.submit();
        req2.reject();
        System.out.println("状態: " + req2.getStatus().getLabel());

        req2.submit();
        System.out.println("状態: " + req2.getStatus().getLabel());

        // --- エラーの流れ:下書きのまま承認しようとする ---
        System.out.println();
        System.out.println("【エラーの流れ】");
        Request req3 = new Request(3, "鈴木一郎", "キーボード", 5000);
        try {
            req3.approve();
        } catch (IllegalStateException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }
}