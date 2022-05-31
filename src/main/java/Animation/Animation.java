package Animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Animation {//класс для анимации
    private TranslateTransition translateTransition;
    public Animation(Node node){
        translateTransition=new TranslateTransition(Duration.millis(50),node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(20f);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);
    }
    public void start(){
        translateTransition.playFromStart();
    }
}
