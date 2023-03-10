import view.cli.accounting.AccountingCLI;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 07/03/23
 */
public class Main {
    public static void main(String[] args) {
        AccountingCLI accountingCLI = new AccountingCLI();
        accountingCLI.run(args);
    }
}
