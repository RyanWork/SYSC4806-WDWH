package SYSC4806.TestHelpers;

/**
 * Class interface to implement to perform @BeforeClass/@AfterClass
 * functionality without the annotations. Useful when class
 * Dependency Injection is required, but can't be used when
 * using @BeforeClass/@AfterClass
 */
public interface InstanceTestClassListener {
    /**
     * Method to execute before class setup
     */
    void beforeClassSetup();

    /**
     * Method to execute after class setup
     */
    void afterClassSetup();
}