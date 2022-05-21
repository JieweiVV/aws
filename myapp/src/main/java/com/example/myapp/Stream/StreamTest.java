package com.example.myapp.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class StreamTest {
    public static void main( String[] args) {
        List<FileMeta> myList = new ArrayList<>();
        myList.add(new FileMeta("file1"));
        myList.add(new FileMeta("file2"));

        // List<FileMeta> result = StreamSupport.stream(myList.spliterator(), false).map(e -> setName(e)).collect(Collectors.toList());

        StreamSupport.stream(myList.spliterator(), false).forEach(e -> printName(e));

    }

    public static FileMeta setName(FileMeta name) {
        name.setName(name.getName() + "wjw");
        return name;
    }

    public static void printName(FileMeta fileMeta) {
        System.out.println(fileMeta.getName());
    }

    @Test
    public void testInitiateOutboundWithMigrationConfigDialedUp() {
        String taskId = "task-id";
        String beachConnectorId = "beachConnectorId";
        String lumosConnectorId = "lumosConnectorId";
        String weblabId = "weblabId";
        String fileId = "fileId";
        ConnectorConfiguration lumosConnector = createConnectorConfig(false, false, ConnectorCategory.PrivilegedOperationsFile, "Lumos");
        ConnectorConfiguration beachConnector = createConnectorConfig(false, false, ConnectorCategory.PrivilegedOperationsFile, "Beach");
        when(connectorConfigurationCache.getConnectorMetaData(beachConnectorId))
                .thenReturn(typeFactory.newEmptyConnectorMetaData()
                        .migrationConfig(typeFactory.newEmptyMigrationConfig().treatmentConnectorId(lumosConnectorId).weblabId(weblabId)));
        when(connectorConfigurationCache.getConnectorConfiguration(beachConnectorId, ConnectorConfigurationResource.ACTIVE_REVISION))
                .thenReturn(beachConnector);
        when(connectorConfigurationCache.getConnectorActiveRevision(lumosConnectorId))
                .thenReturn(lumosConnector);
        FileUploadTask inputTask = typeFactory.newEmptyFileUploadTask().id(taskId).connectorId(beachConnectorId).uploadStatus(FileUploadDownloadStatus.Pending.name())
                .file(typeFactory.newEmptyFile().fileId(fileId));
        when(fileUploadTaskDao.getFileUploadTask(taskId)).thenReturn(inputTask);
        when(payStationConnectorsWeblab.isWeblabDialedUp(fileId, weblabId, "BatchConnectorConfigurationMigration." + beachConnectorId)).thenReturn(true);
        fileConnectorController.initiateUpload(beachConnectorId, ConnectorConfigurationResource.ACTIVE_REVISION, inputTask);
        ArgumentCaptor<FileUploadTask> dialedUpFileUploadTask = ArgumentCaptor.forClass(FileUploadTask.class);
        verify(fileConnectorHandler).initiateUpload(eq(lumosConnector), dialedUpFileUploadTask.capture(), eq(false), eq(true));
        assertEquals(lumosConnectorId, dialedUpFileUploadTask.getValue().connectorId());
        assertTrue(dialedUpFileUploadTask.getValue().useMigrationConfig());
    }
    
}


// [testng] FAILED: testGetDownloadTask({keymaster_file_encryption_data_type:"TestKeyMasterType",alias:"test_alias",category:"PrivilegedOperationsFile",authorized_plugins:["PluginWithoutTokenizeSensitiveDataException"],id:"connectorId",privileged_operations_service:"Lumos"})
//    [testng] java.lang.NullPointerException
//    [testng] 	at com.amazon.paystationextensionsservice.controller.connectors.v3.FileConnectorController.getDownloadTask(FileConnectorController.java:267)
//    [testng] 	at com.amazon.paystationextensionsservice.controller.connectors.v3.v3FileConnectorControllerTest.testGetDownloadTask(v3FileConnectorControllerTest.java:237)
//    [testng] 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//    [testng] 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//    [testng] 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//    [testng] 	at java.lang.reflect.Method.invoke(Method.java:498)
//    [testng] 	at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:85)
//    [testng] 	at org.testng.internal.Invoker.invokeMethod(Invoker.java:639)
//    [testng] 	at org.testng.internal.Invoker.invokeTestMethod(Invoker.java:816)
//    [testng] 	at org.testng.internal.Invoker.invokeTestMethods(Invoker.java:1124)
//    [testng] 	at org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:125)
//    [testng] 	at org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:108)
//    [testng] 	at org.testng.TestRunner.privateRun(TestRunner.java:773)
//    [testng] 	at org.testng.TestRunner.run(TestRunner.java:623)
//    [testng] 	at org.testng.SuiteRunner.runTest(SuiteRunner.java:357)
//    [testng] 	at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:352)
//    [testng] 	at org.testng.SuiteRunner.privateRun(SuiteRunner.java:310)
//    [testng] 	at org.testng.SuiteRunner.run(SuiteRunner.java:259)
//    [testng] 	at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)
//    [testng] 	at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:86)
//    [testng] 	at org.testng.TestNG.runSuitesSequentially(TestNG.java:1185)
//    [testng] 	at org.testng.TestNG.runSuitesLocally(TestNG.java:1110)
//    [testng] 	at org.testng.TestNG.run(TestNG.java:1018)
//    [testng] 	at org.testng.TestNG.privateMain(TestNG.java:1325)
//    [testng] 	at org.testng.TestNG.main(TestNG.java:1294)bb