package a43.lan.extensions.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import a43.lan.tests.BasicNetworkTest;

@RunWith(Suite.class)
@SuiteClasses({ BasicNetworkTest.class, BufferingNetworkTest.class })
public class AllTests {
}
