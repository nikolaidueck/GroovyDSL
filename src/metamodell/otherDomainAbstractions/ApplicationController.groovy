package metamodell.otherDomainAbstractions

import metamodell.DomainAbstraction

class ApplicationController extends DomainAbstraction {
	
	AppCtrlType appCtrlType
	
	enum AppCtrlType {
		Component,
		General,
		AutomaticClient
	}
}
