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
    }
}