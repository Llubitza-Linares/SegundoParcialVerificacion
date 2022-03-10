package testClean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CRUDProjectTest extends BaseTodoIst {
    MainPage mainPage= new MainPage();
    LoginPage loginPage = new LoginPage();
    LeftSection leftSection = new LeftSection();
    MenuSection menuSection = new MenuSection();
    AddEditProjectModal addEditProjectModal = new AddEditProjectModal();
    DeleteProjectConfirmationModal deleteProjectConfirmationModal = new DeleteProjectConfirmationModal();
    String email = "llubilinares@gmail.com";
    String password = "lapaz2000";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    public String projectName = "Project" + dtf.format(LocalDateTime.now());
    public String newProjectName ="LlubiProject"+ dtf.format(LocalDateTime.now());

    @Test
    public void verifyLoginTodoLy() throws InterruptedException {
        mainPage.loginButton.click();
        loginPage.login(email,password);
        Thread.sleep(3000);
        Assertions.assertTrue(menuSection.profileInfoButton.isControlDisplayed(), "ERROR, no se pudo hacer el login");

        // Create
        leftSection.createProjectButton.click();
        addEditProjectModal.projectNameTxtBox.setText(projectName);
        addEditProjectModal.submitButton.click();
        Thread.sleep(3000);
        Assertions.assertEquals(projectName, leftSection.getProjectName(projectName),"ERROR, no se pudo crear el proyecto.");
        Assertions.assertTrue(leftSection.isProjectNameDisplayed(projectName),"ERROR, no se pudo crear el proyecto.");

        // Update
        leftSection.clickNameProject(projectName);
        leftSection.displayMenuButton.click();
        leftSection.projectEditButton.click();
        addEditProjectModal.projectNameTxtBox.clearSetText(newProjectName);
        addEditProjectModal.submitButton.click();
        Thread.sleep(3000);
        Assertions.assertEquals(newProjectName, leftSection.getProjectName(newProjectName),"ERROR, no se pudo actualizar el nombre del proyecto.");
        Assertions.assertTrue(leftSection.isProjectNameDisplayed(newProjectName),"ERROR, no se pudo actualizar el nombre del proyecto.");

        //Delete
        Thread.sleep(3000);
        leftSection.clickNameProject(newProjectName);
        leftSection.displayMenuButton.click();
        leftSection.projectDeleteButton.click();
        Thread.sleep(2000);
        deleteProjectConfirmationModal.projectConfirmDeleteButton.click();
        Thread.sleep(2000);
        Assertions.assertFalse(leftSection.isProjectNameDisplayed(newProjectName),"ERROR, no se pudo eliminar el proyecto.");
    }
}