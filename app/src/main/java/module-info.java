module gradle.skilldrill.App.main {
    requires javafx.controls;
    requires com.google.gson;
    exports edu.isu.cs.cs2263;
    opens edu.isu.cs.cs2263 to com.google.gson;
}