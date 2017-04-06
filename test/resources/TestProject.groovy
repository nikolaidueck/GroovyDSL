module("CustomerContract"){

	entity(Customer){
		description """Das erste Entity das beschrieben wird: Customer -> ist trivial
						 2 Zeilen oder mehr Text, falls die Beschreibung mal l�nger sein sollte"""
		
		attr	firstname, 		type:Text,		description: "Vorname"
		attr	lastname, 		type:Text,		description: "Nachname"
		hasMany	Contract
	}
	
	
	entity(Contract) {
		description """ Normaler Vertrag"""
		
		attr	monthlyCosts,	type:Zahl
		attr	tarif,			type:Text
	}
	
	repository(CustomerRepository){
		description """Das Repository f�r den Customer"""
		has Customer
	}
}