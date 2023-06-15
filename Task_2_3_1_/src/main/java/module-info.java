module r.masud.ccfit.nsu.ru.task_2_3_1_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;

    opens r.masud.ccfit.nsu.ru.task_2_3_1_ to javafx.fxml;
    exports r.masud.ccfit.nsu.ru.task_2_3_1_;
    exports r.masud.ccfit.nsu.ru.task_2_3_1_.model.snakes;
    opens r.masud.ccfit.nsu.ru.task_2_3_1_.model.snakes to javafx.fxml;
    exports r.masud.ccfit.nsu.ru.task_2_3_1_.model;
    opens r.masud.ccfit.nsu.ru.task_2_3_1_.model to javafx.fxml;
    exports r.masud.ccfit.nsu.ru.task_2_3_1_.view;
    opens r.masud.ccfit.nsu.ru.task_2_3_1_.view to javafx.fxml;
    exports r.masud.ccfit.nsu.ru.task_2_3_1_.controller;
    opens r.masud.ccfit.nsu.ru.task_2_3_1_.controller to javafx.fxml;
    exports r.masud.ccfit.nsu.ru.task_2_3_1_.model.json;
    opens r.masud.ccfit.nsu.ru.task_2_3_1_.model.json to javafx.fxml;
    exports r.masud.ccfit.nsu.ru.task_2_3_1_.controller.json;
    opens r.masud.ccfit.nsu.ru.task_2_3_1_.controller.json to javafx.fxml;
}