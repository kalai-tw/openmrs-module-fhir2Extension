package org.openmrs.module.fhirExtension.web.mapper;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.EncounterService;
import org.openmrs.module.fhir2.model.FhirReference;
import org.openmrs.module.fhir2.model.FhirTask;
import org.openmrs.module.fhirExtension.model.FhirTaskRequestedPeriod;
import org.openmrs.module.fhirExtension.model.Task;
import org.openmrs.module.fhirExtension.web.contract.TaskRequest;
import org.openmrs.module.fhirExtension.web.contract.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
	
	@Autowired
	private EncounterService encounterService;
	
	public Task fromRequest(TaskRequest taskRequest) {
		
		Task task = new Task();
		FhirTask fhirTask = new FhirTask();
		fhirTask.setName(taskRequest.getName());
		
		FhirReference forReference = new FhirReference();
		forReference.setType(Patient.class.getTypeName());
		forReference.setReference(Patient.class.getTypeName() + "/" + taskRequest.getPatientUuid());
		forReference.setTargetUuid(taskRequest.getPatientUuid());
		fhirTask.setForReference(forReference);
		
		FhirReference encounterReference = new FhirReference();
		encounterReference.setType(Encounter.class.getTypeName());
		encounterReference.setReference(Encounter.class.getTypeName() + "/" + taskRequest.getEncounterUuid());
		encounterReference.setTargetUuid(taskRequest.getEncounterUuid());
		fhirTask.setEncounterReference(encounterReference);
		
		fhirTask.setStatus(taskRequest.getStatus());
		fhirTask.setIntent(taskRequest.getIntent());
		
		if (taskRequest.getRequestedStartTime() != null || taskRequest.getRequestedEndTime() != null) {
			FhirTaskRequestedPeriod fhirTaskRequestedPeriod = new FhirTaskRequestedPeriod();
			fhirTaskRequestedPeriod.setTask(fhirTask);
			fhirTaskRequestedPeriod.setRequestedStartTime(taskRequest.getRequestedStartTime());
			fhirTaskRequestedPeriod.setRequestedEndTime(taskRequest.getRequestedEndTime());
			task.setFhirTaskRequestedPeriod(fhirTaskRequestedPeriod);
		}
		task.setFhirTask(fhirTask);
		return task;
	}
	
	public TaskResponse constructResponse(Task task) {
		TaskResponse response = new TaskResponse();
		response.setName(task.getFhirTask().getName());
		response.setUuid(task.getFhirTask().getUuid());
		response.setStatus(task.getFhirTask().getStatus());
		response.setIntent(task.getFhirTask().getIntent());
		response.setPatientUuid(task.getFhirTask().getForReference().getTargetUuid());
		response.setRequestedStartTime(task.getFhirTaskRequestedPeriod().getRequestedStartTime());
		response.setRequestedEndTime(task.getFhirTaskRequestedPeriod().getRequestedEndTime());
		return response;
	}
	
}
