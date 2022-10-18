package com.example.demo1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.sql.*;

public class MyDictionary extends Application {

    private Group tiles=new Group();

    int xLine=20;
    int yLine=20;

    DictionaryUsingHashmap dictionary;

    public void connectToDatabase()
    {
        final String url="jdbc:mysql://localhost:3306/my_dict";
        final String user="root";
        final String pass="Jogi@7711";

        System.out.println("Connetin to database");
        String newId="select * from wordlist";
        try(Connection conn = DriverManager.getConnection(url,user,pass);
            Statement smt=conn.createStatement();
            ResultSet rs=smt.executeQuery(newId);
        )
        {
            while(rs.next())
            {
                System.out.println(rs.getInt("id")+" "+rs.getString("word")+"-->"+rs.getString("meaning"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private Pane createContent()
    {
        Pane root=new Pane();
        root.setPrefSize(500,500);
        root.getChildren().addAll(tiles);
      dictionary=new DictionaryUsingHashmap();

        TextField word=new TextField("Enter Your word");
          word.setTranslateX(xLine);
          word.setTranslateY(yLine);

        Label text1=new Label("Here its your meaning");
        text1.setTranslateX(xLine);
        text1.setTranslateY(yLine+50);

        Button b1=new Button("Search");
        b1.setTranslateX(xLine+200);
        b1.setTranslateY(yLine);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String s= word.getText();
                if(s.isBlank())
                {
                    text1.setText("Please Enter a valid Word");
                }
                else
                {
                   text1.setText(dictionary.getMeaning(s));
                }
            }
        });

          tiles.getChildren().addAll(word,b1,text1);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        connectToDatabase();

        Scene scene = new Scene(createContent());
        stage.setTitle("My_Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}