package com.transportgdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by USER on 29.07.2018.
 */
public class Car extends Actor {
    private Body body;
    private TextureRegion sprite;
    private float wheelRotate = 0;   //0 - колесо смотрит прямо. Угол между прямой колеса и прямой корпуса машины
    public static final float MAX_ROTATE = (float)(75 * Math.PI / 180);
    public static final float PI2 = (float)Math.PI * 2;
    public static final float FORCE = 2000f;
    private boolean isGas = false;
    private boolean isTormos = false;
    public static final float FRONT_SIZE = GameScreen.CAR_WIDTH / 2;
    private int drivingSpeed = 0;   //0 - нейтральная, -1 - назад, >0 - вперед

    public void setBodyData(Body body) {
        this.body = body;
        body.setLinearDamping(1f);
        body.setAngularDamping(1f);
    }

    public float getAngle() {
        return body.getAngle();
    }

    public void rotateTo(float x, float y) {
        Vector2 v = body.getPosition();
        //float angle = (float)Math.atan2(y - v.y, x - v.x);
        //angle = (angle + PI2) % PI2;
        float bodyAngle = body.getAngle();
        float ax = (float)Math.cos(bodyAngle);
        float ay = (float)Math.sin(bodyAngle);
        float bx = x - v.x;
        float by = y - v.y;
        float scalMult = ax * bx + ay * by;
        float anorm2 = ax * ax + ay * ay;
        float bnorm2 = bx * bx + by * by;
        if(anorm2 == 0 || bnorm2 == 0) return;
        float angle = (float)(Math.acos(scalMult / Math.sqrt(anorm2 * bnorm2)));
        if(ax * by - ay * bx < 0) {  //Проверка, с какой стороны рассматривается угол
            angle = -angle;
        }
        rotate(angle);
    }

    public float getWheelRotate() { return wheelRotate; }

    public float getSpeed() { return body.getLinearVelocity().len(); }

    @Override
    public void act(float delta) {
        if(isGas) {
            float force = drivingSpeed * FORCE;
            float angle = body.getAngle();
            float sin = (float) Math.sin(angle);
            float cos = (float) Math.cos(angle);
            Vector2 pos = body.getPosition();
//            body.applyForceToCenter(
//                    FORCE * cos,
//                    FORCE * sin,
//                    true);    //Сила тяги
            float frontX = cos * FRONT_SIZE;
            float frontY = sin * FRONT_SIZE;
            body.applyForce(force * cos, force * sin, pos.x - frontX, pos.y - frontY, true);
            float sinWheel = (float) Math.sin(angle + wheelRotate);
            float cosWheel = (float) Math.cos(angle + wheelRotate);
            body.applyForce(force * cosWheel / 2, force * sinWheel / 2, pos.x + frontX, pos.y + frontY, true);
        }
        if(isTormos) {
            body.setLinearDamping(5f);
            body.setAngularDamping(5f);
        } else {
            body.setLinearDamping(1f);
            body.setAngularDamping(1f);
        }
    }

    public Car(TextureRegion sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Vector2 pos = body.getPosition();
        float angle = body.getAngle();
        batch.draw(sprite, pos.x - GameScreen.CAR_WIDTH / 2, pos.y - (GameScreen.CAR_HEIGHT + 2) / 2,
                GameScreen.CAR_WIDTH / 2, (GameScreen.CAR_HEIGHT + 2) / 2,
                GameScreen.CAR_WIDTH, 2 + GameScreen.CAR_HEIGHT, 2.2f, 2.2f, 180 * angle / (float)Math.PI);
    }

    public void rotate(float angle) {
        if(angle < -MAX_ROTATE) angle = -MAX_ROTATE;
        if(angle > MAX_ROTATE) angle = MAX_ROTATE;
        //if(angle < -MAX_ROTATE || angle > MAX_ROTATE) return;
        wheelRotate = angle;
    }
    public void setDrivingSpeed(int drivingSpeed) {
        if(drivingSpeed <= 0) return;
        if(drivingSpeed - this.drivingSpeed > 1 && this.drivingSpeed != -1) return;   //Нельзя переключаться сразу на несколько скоростей вперед
        this.drivingSpeed = drivingSpeed;
    }
    public void setNeutralSpeed() {
        this.drivingSpeed = 0;
    }
    public void setR() {
        this.drivingSpeed = -1;
    }
    public void takeOutGas() {
        isGas = false;
    }
    public void takeOutTormos() { isTormos = false; }
    public void gas() {
        isGas = true;


//        body.applyForce(new Vector2(-2000, -5060), new Vector2(5,-2), true);
//
//        body.applyForceToCenter(new Vector2(1000,1000), true);
//        body.applyForce(new Vector2(-2000, -5060), new Vector2(5,-2), true);
//        body.applyForce(new Vector2(-2000, -5060), new Vector2(5,-2), true);
//        body.applyForce(new Vector2(2000, 1000), new Vector2(-5,2), true);
        //body.applyLinearImpulse(-10, -30, 5, -4, true);
        //body.setAngularDamping(100f);
        //body.applyAngularImpulse(1500f, true);
        //body.applyForce(new Vector2(-2000,2000), new Vector2(100,0), true);

    }
    public void tormos() {
        isTormos = true;
    }
}
