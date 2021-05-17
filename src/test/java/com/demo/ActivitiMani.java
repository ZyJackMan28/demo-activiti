package com.demo;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class ActivitiMani {

    @Test
    public void testDeployActiviti(){
        //create
        ProcessEngineConfiguration resource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = resource.buildProcessEngine();
        //get resource get managerment
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //service --> Deployment bpmn
        Deployment deploy = repositoryService.createDeployment()
                .name("evec")
                .addClasspathResource("bpmn/transition.bpmn")
                .addClasspathResource("bpmn/aaa.png")
                .deploy();
        System.out.println("process-deployment " + deploy.getId());
        System.out.println(deploy.getName());

    }

    @Test
    public void testSetupInstance(){
        //create
        ProcessEngineConfiguration resource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = resource.buildProcessEngine();
        //get resource get managerment
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //by the value id 0--->
        ProcessInstance myEvection = runtimeService.startProcessInstanceByKey("myTransition");
        System.out.println("id :" + myEvection.getProcessDefinitionId());
        System.out.println("id :" + myEvection.getId());
        System.out.println("id :" + myEvection.getActivityId());
    }

    /*
    *  个人任务查询
    * */
    @Test
    public void testIndividualTaskList() {
        //create
        ProcessEngineConfiguration resource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = resource.buildProcessEngine();
        //get resource get managerment
        TaskService service = processEngine.getTaskService();
        //by the value id 0--->
        //key
        List<Task> list = service.createTaskQuery()
                .processDefinitionKey("myTransition")
                .taskAssignee("marry")
                .list();
        System.out.println(list);
        for (Task task : list) {
            System.out.println("流程实例:" + task.getProcessInstanceId());
            System.out.println("任务id:" + task.getId());
            System.out.println("负责人:" + task.getAssignee());
            System.out.println("任务名词:" + task.getName());

        }
    }

    /*
    * completed
    * */
    @Test
    public void testCompleteTask(){
        //create 30005
        ProcessEngineConfiguration resource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = resource.buildProcessEngine();
        //get resource get managerment
        TaskService service = processEngine.getTaskService();
        /*service.complete("30005");*/
        Task task = service.createTaskQuery()
                .processDefinitionKey("myTransition")
                .taskAssignee("hanmeimei")
                .singleResult();
        service.complete(task.getId());
        System.out.println("流程实例:" + task.getProcessInstanceId());
        System.out.println("任务id:" + task.getId());
        System.out.println("负责人:" + task.getAssignee());
        System.out.println("任务名词:" + task.getName());
    }
}
