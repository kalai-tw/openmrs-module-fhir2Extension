package org.openmrs.module.fhirExtension.web.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openmrs.module.fhir2.model.FhirTask;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponse {
	
	private String uuid;
	
	private String name;
	
	private String patientUuid;
	
	private Date requestedStartTime;
	
	private Date requestedEndTime;
	
	private FhirTask.TaskStatus status;
	
	private FhirTask.TaskIntent intent;
	
}
