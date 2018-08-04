package com.transportgdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by USER on 04.08.2018.
 */
public class MenuScreen implements Screen {
    private Stage stage;
    private Viewport viewport = new FitViewport(1024, 768);
    private TransportGame game;

    private TextField textInput;

    public MenuScreen(TransportGame game) {
        this();
        this.game = game;
    }

    private Skin createTextButtonSkin() {
        Skin skin = new Skin();
        skin.add("default", new BitmapFont());
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        skin.add("none", new Texture(pixmap));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.over = skin.newDrawable("none", new Color(0, 0, 0, 1));
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        return skin;
    }
    private Skin createWindowSkin() {
        Skin skin = new Skin();
        skin.add("default", new BitmapFont());
        Window.WindowStyle style = new Window.WindowStyle();
        style.titleFont = skin.getFont("default");
        skin.add("default", style);
        return skin;
    }

    public MenuScreen() {
        this.stage = new Stage(viewport);
        stage.setDebugAll(true);

        Skin winSkin = createWindowSkin();
        Window window = new Window("online cars", winSkin);
        window.setPosition(200, 140);
        window.setSize(600, 560);

        Skin fieldSkin = new Skin();
        TextField.TextFieldStyle fieldStyle = new TextField.TextFieldStyle();
        fieldSkin.add("default", new BitmapFont());
        fieldStyle.font = fieldSkin.getFont("default");
        fieldStyle.fontColor = Color.WHITE;
        fieldSkin.add("default", fieldStyle);
        textInput = new TextField("localhost:4000", fieldSkin);

        Skin skin = createTextButtonSkin();
        TextButton button = new TextButton("Connect", skin);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                String addressName = textInput.getText();
                String[] strs = addressName.split(":");
                if(strs.length == 2) {
                    String address = strs[0];
                    String portString = strs[1];
                    int port = Integer.parseInt(portString);

                    //Подключение клиента
                    //---------------------------------------

                    //---------------------------------------

                    game.setScreen(new GameScreen(game));
                    dispose();
                }
                else {
                    //Ошибка
                }
            }
        });
        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                //game.dispose();
                System.exit(0);
            }
        });
        //window.add(button);

        Skin labelSkin = new Skin();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        //labelSkin.add("default", new BitmapFont());
        labelStyle.font = new BitmapFont();
        labelSkin.add("default", labelStyle);

        Table table = new Table();
        table.setFillParent(true);
        //table.align();
        table.add(new Label("host:port", labelSkin)).fillX().pad(10);
        table.add(textInput).pad(10).row();
        table.add(button).pad(10).colspan(2).fillX().row();
        table.add(exitButton).pad(10).colspan(2).fillX();
        //table.add(button).width(400).height(50).pad(10);
        window.addActor(table);

        this.stage.addActor(window);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        stage.dispose();
    }
}
