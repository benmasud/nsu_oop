package r.masud.ccfit.nsu.ru.task_2_3_1_.model.json;
import java.awt.Point;
import java.util.List;
public record JsonData(int width, int height, int columns, int rows, int squareSize,
                       int maxFoodForLevel1, int maxFoodForLevel2, int maxFoodForLevel3,
                       int speedFoodForLevel1, int speedFoodForLevel2, int speedFoodForLevel3,
                       List<Point> wallsFoodForLevel1, List<Point> wallsFoodForLevel2,
                       List<Point> wallsFoodForLevel3) {
}