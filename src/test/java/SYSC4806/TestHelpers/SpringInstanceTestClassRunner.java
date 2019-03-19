package SYSC4806.TestHelpers;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Instance of spring runner to run when using tests.
 * Used as @RunWith(SpringInstanceTestClassRunner.class)
 */
public class SpringInstanceTestClassRunner extends SpringJUnit4ClassRunner {
    /**
     * Instance of the test class listener that has the
     * Before/After Class Setup methods
     */
    private InstanceTestClassListener InstanceSetupListener;

    /**
     * Constructor to invoke inheriting properties
     * @param clazz
     * @throws InitializationError
     */
    public SpringInstanceTestClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    /**
     * Create the test and invoke the before class setup method
     * @return
     * @throws Exception
     */
    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        // Note that JUnit4 will call this createTest() multiple times for each
        // test method, so we need to ensure to call "beforeClassSetup" only once.
        if (test instanceof InstanceTestClassListener && InstanceSetupListener == null) {
            InstanceSetupListener = (InstanceTestClassListener) test;
            InstanceSetupListener.beforeClassSetup();
        }
        return test;
    }

    /**
     * Invoke the after class set up when running
     * @param notifier
     */
    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        if (InstanceSetupListener != null)
            InstanceSetupListener.afterClassSetup();
    }
}