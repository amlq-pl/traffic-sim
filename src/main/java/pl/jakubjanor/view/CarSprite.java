package pl.jakubjanor.view;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class CarSprite {
    private final ImageView imageView;
    private final PathTransition pathTransition;

    public CarSprite(String imagePath, double height, double duration, boolean shouldPause) {
        imageView = new ImageView(new Image(imagePath));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(height);

        pathTransition = new PathTransition();
        pathTransition.setNode(imageView);
        pathTransition.setDuration(Duration.seconds(duration));
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        if (shouldPause) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5 * (duration / 5.0)));
            pause.setOnFinished(e -> {
                pathTransition.pause(); // pause
                // Resume after another delay
                PauseTransition resume = new PauseTransition(Duration.seconds(2 * (duration / 5.0)));
                resume.setOnFinished(ev -> pathTransition.play()); // resume
                resume.play();
            });
            pause.play();
        }
    }

    public void driveAlong(Path path, int cycleCount) {
        pathTransition.setPath(path);
        pathTransition.setCycleCount(cycleCount);
        pathTransition.play();
    }

    public ImageView getView() {
        return imageView;
    }
}
