package baeldung

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.nio.file.Files
import java.nio.file.Path
import com.kms.katalon.core.configuration.RunConfiguration
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import java.util.function.BiFunction

/**
 * https://www.baeldung.com/java-merge-maps
 *
 * This test demonstrates how to merge two maps using the Java 8 capabilities.
 */
@RunWith(JUnit4.class)
public class MergingTwoMapsTest {

	private Map<String, Employee> map1 = new HashMap<>()
	private Map<String, Employee> map2 = new HashMap<>()

	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter()
	
	@Before
	void setup() {
		Employee employee1 = new Employee(1L, "Henry")
		map1.put(employee1.getName(), employee1)

		Employee employee2 = new Employee(22L, "Annie")
		map1.put(employee2.getName(), employee2)

		Employee employee3 = new Employee(8L, "John")
		map1.put(employee3.getName(), employee3)

		Employee employee4 = new Employee(2L, "George")
		map2.put(employee4.getName(), employee4)

		Employee employee5 = new Employee(3L, "Henry")
		map2.put(employee5.getName(), employee5)
	}

	@Test
	void testPrettyPrint() {
		println "map1: " + writer.writeValueAsString(map1)
		println "map2: " + writer.writeValueAsString(map2)
	}
	
	@Test
	void testMerge() {
		Map<String, Employee> map3 = new HashMap<>(map1)
		BiFunction newEmployee = { Employee v1, Employee v2 -> 
						new Employee(v1.getId(), v2.getName())
					}
		map2.forEach({ key, value -> 
				map3.merge(key, value, newEmployee)
			})
		println "map3: " + writer.writeValueAsString(map3)
	}
}
