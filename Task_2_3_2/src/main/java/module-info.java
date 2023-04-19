module r.masud.ccfit.nsu.ru.task_2_3_ {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens r.masud.ccfit.nsu.ru.task_2_3_2 to javafx.fxml;
    exports r.masud.ccfit.nsu.ru.task_2_3_2;
}