package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.accounting.AccountingServiceTest;
import test.users.TenantServiceTest;;

/**
 * @author mkjodhani
 * @project Tenant Management System
 * @since 10/03/23
 * @version 1.0
 */
@Suite.SuiteClasses({
        AccountingServiceTest.class,
        TenantServiceTest.class
})
@RunWith(Suite.class)
public class TestSuite {
}
