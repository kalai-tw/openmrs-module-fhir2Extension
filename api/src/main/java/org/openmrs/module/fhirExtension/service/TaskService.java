package org.openmrs.module.fhirExtension.service;

import org.openmrs.module.fhir2.model.FhirTask;
import org.openmrs.module.fhirExtension.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public interface TaskService {
	
	Task saveTask(Task task);
	
	List<Task> getTasksByVisitFilteredByTimeFrame(String visitUuid, Date startTime, Date endTime);
}
