# mobiquity-tests
This is a project that has three UI tests scripted using Selenium+Java for Mobiquity assessment for Senior QA engineer

## Instructions
1. Download the code on your machine
2. Install `gradle` on your machine
3. In the terminal, navigate to the folder that contains `build.gradle` in this project
4. Run the command `gradle cleanTest test`

## Reports
Reports can be viewed in the path `build/reports/tests/test/index.html`

## Some things to note
> Each test runs first on Chrome and then on Firefox

> The framework is built in a way so the tests run in parallel

> Seems like the delete does not immediately delete the record. Here is a video of the issue: https://screencast.com/t/ppeARWgQBqz
Hence the delete test sometimes failed

## Automated Use Cases
### Test 1 (Test_CreateEmployee)

1. Open the app
2. Login to the app
3. Create an employee and assert that the employee shows up on the list

### Test 2 (Test_DeleteEmployee)

1. Open the app
2. Login to the app
3. Create an employee and assert that the employee shows up on the list
4. Select the employee on the list and Delete
5. Assert that the deleted employee record is no more present in the list
 
 ### Test 3 (Test_EditEmployee)
 
1. Open the app
2. Login to the app
3. Create an employee and assert that the employee shows up on the list
4. Select the employee on the list and click Edit
5. Update the Last Name and save
6. Assert that the list contains the new last name as updated
 
 
