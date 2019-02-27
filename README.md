Unit Testing with Mockito

MockMvc  - MockMvc will mock all the Spring MVC infrastructure.
1.	@RunWith(MockitoJUnitRunner.class) - This class automatically initialize all the objects annotated with @Mock and @InjectMocks annotations.
2.	@Mock - Creates a mock implementation for the class it is annotated with.
3.	@InjectMocks - Creates the mock implementation, additionally injects the dependent mocks that are marked with the annotations @Mock into it.
4.	@Test - Write the test in method annotated with @Test.
5.	@BeforeClass @AfterClass - Static methods which are executed once before and after a test class.
6.	@Before @After annotations – Run before and after every test method in the class.
7.	@Ignore - Doesn't execute particular test case.

The MockMvc is initialized using the MockMvcBuilders.standaloneSetup(...).build() method. We can also add filters, interceptors or etc. using the .addFilter() or .addInterceptor() methods.
Verifying the mock behavior – 
verify(prototypeService, times(1)).addDrawing(drawRevision, loggedInUser) - verify number of interactions with mock.
verifyNoMoreInteractions(prototypeService) - Verify there are no unexpected interactions.

Choosing between the approaches - 
Launching the entire spring context (Using SpringRunner) makes the unit test slower. Unit tests will also start failing if there are errors in other beans in the contexts. So, the MockitoJUnitRunner approach is preferred.

Eg. 
The JUnit Runner which causes all the initialization with @Mock and @InjectMocks to happen before the tests are run.
@RunWith(MockitoJUnitRunner.class) 
public class PSControllerMockMvcStandaloneTest{

}


@Mock
private PrototypeService prototypeService  - Create a mock for PrototypeService.

@InjectMocks 
private PrototypeStatusController prototypeStatusController- Inject the mocks as dependencies into PrototypeStatusController



Important Note - 
If we have both constructor and annotation injection fields in class, injection will be failed.
Work-around -> Extend your test classes from any other class. 


