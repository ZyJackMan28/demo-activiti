package com.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

public class ZipAct {
        @Test
        public void testDeployActivitiByZip(){
            ProcessEngineConfiguration resource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
            ProcessEngine processEngine = resource.buildProcessEngine();
            RepositoryService repositoryService = processEngine.getRepositoryService();
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("bpmn/ad.zip");
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            repositoryService.createDeployment()
                    .addZipInputStream(zipInputStream)
                    .deploy();

        }

}
