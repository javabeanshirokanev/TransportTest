package com.transportgdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.utils.Box2DBuild;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by USER on 29.07.2018.
 */
public class GameScreen implements Screen, InputProcessor {
    private SpriteBatch batch;
    private Texture img;
    private TiledMap map;
    private float unitScale = 1 / 16f;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport gameViewport = new FitViewport(1200, 840);
    private World world;
    private Array<Body> bodies = new Array<Body>();
    private TextureRegion carRegion;
    private BitmapFont font;

    private Car myCar;

    private Box2DDebugRenderer box2drenderer;

    private Stage stage;

    public static final float CAR_WIDTH = 10;
    public static final float CAR_HEIGHT = 4;


    @Override
    public void show() {

    }

    public GameScreen() {
        batch = new SpriteBatch();
        img = new Texture("C:\\TransportTest\\assets\\car.png");
        map = new TmxMapLoader().load("C:\\TransportTest\\assets\\test2.tmx");
        font = new BitmapFont();
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        //camera.setToOrtho(false, 1f, 1f);
        world = new World(new Vector2(0, 0), true);
        box2drenderer = new Box2DDebugRenderer();
        gameViewport.setCamera(camera);
        stage = new Stage(gameViewport);

        camera.setToOrtho(false, 200f, 100f);
        //camera.far = 1000;
        //renderer.setView(camera);

        //Gdx.input.setInputProcessor(this);	 //позволяет нам обрабатывать действия юзера

        TextureRegion[][] regSplitResult = TextureRegion.split(img, 640, 360);
        carRegion = regSplitResult[0][0];
        myCar = createCar(150, 80, 0);
        createCar(150, 20, 1.2f);
        //body.applyForce(new Vector2(3,0), new Vector2(0,0), true);
        //body.applyLinearImpulse(new Vector2(6,0), new Vector2(0,0), false);
        Gdx.input.setInputProcessor(this);
        //myCar.gas(1f);
    }

    private Car createCar(float x, float y, float angle) {
        Car car = new Car(carRegion);
        Body body = addCar(x, y, angle, 1f, car);
        car.setBodyData(body);
        return car;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Vector2 v = myCar.getPosition();
        camera.position.x = v.x;
        camera.position.y = v.y;
        //camera.translate(1, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        box2drenderer.render(world, camera.combined);   //Это временно
//        world.getBodies(bodies);
//        for(Body body : bodies) {
//            Car car = (Car)body.getUserData();
//        }
        stage.draw();

        stage.act(delta);
        world.step(delta, 4, 4);

        batch.begin();
        font.draw(batch, "angle: " + 180 * myCar.getAngle() / Math.PI, 10, 300);
        font.draw(batch, "x: " + tp.x, 10, 280);
        font.draw(batch, "y: " + tp.y, 10, 260);
        font.draw(batch, "wheel: " + myCar.getWheelRotate(), 10, 240);
        batch.end();

        camera.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        float x = tp.x;
        float y = tp.y;
        myCar.rotateTo(x, y);
    }

    private Body addCar(float x, float y, float angle, float density, Car car) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(CAR_WIDTH / 2, CAR_HEIGHT / 2);
        Body body = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(CAR_WIDTH, CAR_HEIGHT);
        body.createFixture(poly, density);
        body.setUserData(car);
        stage.addActor(car);
        body.setTransform(x, y, angle);
        poly.dispose();
        return body;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE) {
            //Выход
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    private Vector3 tp = new Vector3();

    private float testAngle;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        camera.unproject(tp.set(screenX, screenY, 0));
        float x = tp.x;
        float y = tp.y;
        myCar.gas();
        //myCar.rotateTo(x, y);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //Gdx.input.isTouched();
        myCar.takeOutGas();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        camera.unproject(tp.set(screenX, screenY, 0));
        float x = tp.x;
        float y = tp.y;
        //myCar.rotateTo(x, y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}