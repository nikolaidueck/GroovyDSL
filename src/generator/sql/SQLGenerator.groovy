package generator.sql

import groovy.text.SimpleTemplateEngine
import java.io.File

import metamodell.DomainModel
import metamodell.businessobjects.Entity

class SQLGenerator {

	public static void model2SQL(DomainModel model){
		addSnakeCaseMethodToStringClass()
		
		def engine = new SimpleTemplateEngine(true) // set to true for verbose mode
		
		def path = getSQLGenPath()
		
		model.domainObjects.each {	domainObj ->
			if(domainObj instanceof Entity){
				def entity = domainObj as Entity
				entity.dbName = entity.dbName ?: entity.name.toSnakeCase()
				// define binding
				def bind = [entity: entity, m2sqlMapper: Model2SQLMapper.instance]
				
				// generate business object
				def javaObjectFileName = "${entity.name}.sql"
				def javaObjectFile = new File(path, javaObjectFileName)
				def template = engine.createTemplate(new File("src/generator/sql/templates/SQL.tmpl")).make(bind)
				javaObjectFile.write template.toString()
			
			}
		}
		
	}
	
	private static File getSQLGenPath() {
		def path = new File("env/sql")
		path.mkdirs()
		return path
	}

	private static addSnakeCaseMethodToStringClass() {
		String.metaClass.toSnakeCase = { delegate.replaceAll( /([A-Z])/, /_$1/ ).toLowerCase().replaceAll( /^_/, '' ) }
	}
}
