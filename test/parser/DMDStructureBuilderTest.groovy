package parser
import static org.junit.Assert.*

import MetaModell.DomainProperty
import MetaModell.DomainModel
import dmd.dsl.DMDStructureBuilder

import org.junit.Test
/**
 * Assures that model is populated correctly
 * @author DUEC007
 *
 */
class DMDStructureBuilderTest {
	
	@Test
	public void testProject() {
		DomainModel project = new DMDStructureBuilder().AnyProject { }
		assertEquals("AnyProject", project.name)
	}

	@Test
	public void testEntity() {
		DomainModel project = new DMDStructureBuilder().Test {
			entity("TestEntity")
			entity("SecondEntity")
		}
		
		assertNotNull(project.entityList)
		assertEquals(2, project.entityList.size())
		
		//First element in DSL equals first element in the List
		assertEquals("TestEntity", project.entityList.get(0).name)
	}
	
	@Test
	public void testValueObject() {
		DomainModel project = new DMDStructureBuilder().Test {
			valueObject("TestVO")
			valueObject("SecondVO")
		}
		
		
		assertNotNull(project.valueObjectList)
		assertEquals(2, project.valueObjectList.size())
		
		//First element in DSL equals first element in the List
		assertEquals("TestVO", project.valueObjectList.get(0).name)
	}
	
	@Test
	public void testAttributes() {
		DomainModel project = new DMDStructureBuilder().Test {
			valueObject("TestVO") {
				id
				attributeWithType Text
				attributeWithProperties Text(min:1, max:20), Description: "description for attribute with properties"
			}
		}
		
		
		assertNotNull(project.valueObjectList)
		assertEquals(1, project.valueObjectList.size())
		
		def attributes = project.valueObjectList.get(0).attributeList
		assertEquals(2, attributes.size())
		
		DomainProperty attribute1 = attributes.get(0) as DomainProperty
		assertEquals("attributeWithType", attribute1.name)
		//	TODO: assertEquals(Text, attribute1.type)
		
		DomainProperty attribute2 = attributes.get(1) as DomainProperty
		assertEquals("attributeWithProperties", attribute2.name)
		//	TODO: assertEquals(Text, attribute2.type)

		assertEquals("description for attribute with properties", attribute2.attributes["Description"])
		
		//	TODO: test, there's some output that id of valueObject "TestVO" was ignored
	}

}
