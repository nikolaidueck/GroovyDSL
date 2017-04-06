module("Tarif"){

	entity(Tarif){
		description "Tarif"
		
		attr	tarifname, 		type:Text,		description: "Tarifname"
		attr	product, 		type:Text,		description: "Produktname"
	}
	
	repository(TarifRepository){
		description """Das Repository f�r den Tarif"""
		has Tarif
	}
}