package view;

import view.cli.accounting.AccountingCLI;

/**
 * @author mkjodhani
 * @project
 * @since 07/03/23
 */
public class CLI {
    public static void main(String[] args) {
        AccountingCLI accountingCLI = new AccountingCLI();
        accountingCLI.run(args);
    }
}
