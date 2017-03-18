/*
 * This code was generated by AWS Flow Framework Annotation Processor.
 * Refer to Amazon Simple Workflow Service documentation at http://aws.amazon.com/documentation/swf 
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
 package com.eucalyptus.portal.workflow;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowSelfClient;

/**
 * Generated from {@link com.eucalyptus.portal.workflow.InstanceLogWorkflow}. 
 * Used to continue a workflow execution as a new run.
 * Must be used from a worklfow scope. 
 */
public interface InstanceLogWorkflowSelfClient extends WorkflowSelfClient
{

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.InstanceLogWorkflow#logInstanceHourly}
     */
    void logInstanceHourly();

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.InstanceLogWorkflow#logInstanceHourly}
     */
    void logInstanceHourly(StartWorkflowOptions optionsOverride, Promise<?>... waitFor);
}