/*
 * This Java source file was generated by the Gradle 'init' task.
 */
/*
    Brandon Watkins
    1/22/2021
 */
package edu.isu.cs.cs2263;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    private static final String file = "students.obj";
    private List<Student> students;
    private IOManager manager = new IOManager();

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("Student Course List");

        // Student List
        ObservableList<Student> obsList = FXCollections.observableArrayList();

        Label stuLab = new Label("Students");
        stuLab.setPadding(new javafx.geometry.Insets(5, 0, 5, 0));
        ListView stuList = new ListView(obsList);
        VBox studentList = new VBox(stuLab, stuList);
        studentList.setPrefSize(250, 200);

        // Middle Label
        Label middleText = new Label("");
        VBox middleLabel = new VBox(middleText);
        middleLabel.setPrefSize(100, 300);
        middleLabel.setPadding(new javafx.geometry.Insets(120, 10, 0, 10));

        // Course List
        Label couLab = new Label("Courses");
        couLab.setPadding(new javafx.geometry.Insets(5, 0, 5, 0));
        ListView couList = new ListView();
        VBox courseList = new VBox(couLab, couList);
        courseList.setPrefSize(350, 200);

        // Button
        Button button = new Button("Load Courses");
        HBox btnPane = new HBox(button);
        btnPane.setPrefSize(50, 20);
        btnPane.setPadding(new javafx.geometry.Insets(10, 0, 0, 585));

        BorderPane bp = new BorderPane();
        bp.setBottom(btnPane);
        bp.setLeft(studentList);
        bp.setCenter(middleLabel);
        bp.setRight(courseList);
        bp.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        // populate student list
        button.setOnAction(e -> {
            students = manager.readData(file);
            middleText.setText("");
            try {
                if (couList != null && couList.getItems() != null) couList.getItems().clear();
                if (stuList != null && stuList.getItems() != null) stuList.getItems().clear();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            if (students == null || students.size() < 1) students = studentsInit(manager);
            if (students == null || students.size() < 1)
                System.out.println("Couldn't read from file or create a new one");
            else {
                obsList.clear();
                for (Student s : students) {
                    obsList.add(s);
                }
            }
        });

        // Class Lookup Stuff
        stuList.getSelectionModel().selectedItemProperty().addListener(e -> {
            couList.getItems().clear();
            if (stuList != null && students != null && students.size() > 0) {
                MultipleSelectionModel msm = stuList.getSelectionModel();
                if (msm != null) {
                    Student stu = (Student)msm.getSelectedItem();
                    if (stu != null) {
                        ObservableList<Course> obsList2 = FXCollections.observableArrayList();
                        if (obsList2 != null) {
                            List<Course> courses = stu.getCourses();
                            if (courses != null && courses.size() > 0) {
                                for(Course c: stu.getCourses()) {
                                    obsList2.add(c);
                                }
                                couList.getItems().addAll(obsList2);
                                middleText.setText(" Is Taking ");
                                return;
                            }
                        }
                    }
                    // "load" was probably clicked with a student selected, don't want to leave the error message.
                    else return;
                }
            }
            middleText.setText("");
            System.out.println("Couldn't find courses for the selected student.");
        });

        Scene scene = new Scene(bp, 700, 300);
        stage.setScene(scene);
        stage.show();
    }

    private static List<Student> studentsInit(IOManager manager) {
        List<Student> s = new ArrayList<>();
        s.add(new Student("Isaac", "Griffith", new ArrayList<Course>(Arrays.asList(
                new Course("CS", 1181, "Computer Science and Programming I"),
                new Course("ME", 2220, "Engineering Dynamics"),
                new Course("CS", 2263, "Advanced Object-Oriented Programming"),
                new Course("CS", 1337, "Systems Programming and Assembly")
        ))));
        s.add(new Student("Brandon", "Watkins", new ArrayList<Course>(Arrays.asList(
                new Course("INFO", 1150, "Software and Systems Architecture"),
                new Course("CS", 2235, "Data Structures and Algorithms"),
                new Course("CS", 2263, "Advanced Object-Oriented Programming"),
                new Course("INFO", 2220, "Web Development"),
                new Course("CS", 3321, "Systems Analysis and Design")
        ))));
        s.add(new Student("Bob", "Sampson", new ArrayList<Course>(Arrays.asList(
                new Course("INFO", 2220, "Web Development"),
                new Course("INFO", 4430, "Web Application Development"),
                new Course("INFO", 4407, "Database Design Implementation")
        ))));
        s.add(new Student("Sarah", "James", new ArrayList<Course>(Arrays.asList(
                new Course("INFO", 4411, "Intermediate Information Assurance"),
                new Course("CS", 2263, "Advanced Object-Oriented Programming"),
                new Course("INFO", 2220, "Web Development"),
                new Course("CS", 1337, "Systems Programming and Assembly")
        ))));
        s.add(new Student("Chuck", "Norris", new ArrayList<Course>(Arrays.asList(
                new Course("ME", 2266, "Symbolic Programming"),
                new Course("PHYS", 2211, "Engineering Physics"),
                new Course("CE", 2210, "Engineering Statics")
        ))));
        s.add(new Student("One-armed", "Jim", new ArrayList<Course>(Arrays.asList(
                new Course("MATH", 2240, "Linear Algebra"),
                new Course("INFO", 3380, "Networking and Virtualization"),
                new Course("Math", 1187, "Applied Discrete Structures"),
                new Course("INFO", 4407, "Database Design Implementation")
        ))));
        manager.writeData(file, s);
        return manager.readData(file);
    }
}
