package com.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

public class TestAct {

    @Test
    public void testCreateDefaultActiTable(){
        /*read activiti.cfg.xml*/
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        //repositoryService.
        System.out.println(processEngine);
    }

    @Test
    public void testCreateGeneralsActiTable(){
        /*read activiti.cfg.xml*/
        ProcessEngineConfiguration resource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = resource.buildProcessEngine();

        //RepositoryService repositoryService = processEngine.getRepositoryService();
        //repositoryService.
        System.out.println(processEngine);
    }
}
